package com.jvmfrog.ffsettings.ui.fragment;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.jvmfrog.ffsettings.R;
import com.jvmfrog.ffsettings.adapter.DevicesAdapter;
import com.jvmfrog.ffsettings.model.ParamsModel;
import com.jvmfrog.ffsettings.databinding.FragmentDevicesBinding;
import com.jvmfrog.ffsettings.utils.AdMobUtil;

public class DevicesFragment extends Fragment {

    private FragmentDevicesBinding binding;
    private DevicesAdapter devicesAdapter;

    public DevicesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDevicesBinding.inflate(inflater, container, false);

        Bundle finalBundle = new Bundle();
        finalBundle.putAll(getArguments());

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        Query query = rootRef.collection(finalBundle.getString("device"))
                .orderBy("device_name", Query.Direction.DESCENDING);

        FirestoreRecyclerOptions<ParamsModel> options =
                new FirestoreRecyclerOptions.Builder<ParamsModel>()
                        .setQuery(query, ParamsModel.class)
                        .build();

        binding.recview.setLayoutManager(new GridLayoutManager(getContext(), 1));
        devicesAdapter = new DevicesAdapter(options, getActivity());
        binding.recview.setAdapter(devicesAdapter);

        return binding.getRoot();
    }

    @Override
    public void onStart() {
        super.onStart();
        devicesAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        if(devicesAdapter != null)
            devicesAdapter.stopListening();
    }
}