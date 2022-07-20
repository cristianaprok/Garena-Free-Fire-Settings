package com.jvmfrog.ffsettings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.jvmfrog.ffsettings.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private FirebaseAnalytics mFirebaseAnalytics;

    private DevicesAdapter devicesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        binding.recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        Query query = rootRef.collection("categories")
                .orderBy("deviceName", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<ParamsModel> options =
                new FirestoreRecyclerOptions.Builder<ParamsModel>()
                        .setQuery(query, ParamsModel.class)
                        .build();

        devicesAdapter = new DevicesAdapter(options);
        binding.recview.setAdapter(devicesAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        devicesAdapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(devicesAdapter != null)
        devicesAdapter.stopListening();
    }
}