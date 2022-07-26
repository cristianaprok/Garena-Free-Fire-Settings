package com.jvmfrog.ffsettings.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.app.Application;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.jvmfrog.ffsettings.MyApplication;
import com.jvmfrog.ffsettings.databinding.ActivityMainBinding;
import com.jvmfrog.ffsettings.ui.fragment.AboutAppFragment;
import com.jvmfrog.ffsettings.ui.fragment.ManufacturerFragment;
import com.jvmfrog.ffsettings.utils.FragmentUtils;
import com.jvmfrog.ffsettings.R;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentUtils.changeFragment(this, new ManufacturerFragment(), R.id.frame, null);
        Application application = getApplication();

        // Show the app open ad.
        ((MyApplication) application)
                .showAdIfAvailable(
                        MainActivity.this,
                        new MyApplication.OnShowAdCompleteListener() {
                            @Override
                            public void onShowAdComplete() {
                                //
                            }
                        });

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        binding.bottomAppBar.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    FragmentUtils.changeFragment(this, new ManufacturerFragment(), R.id.frame, null);
                    break;
                case R.id.about_app:
                    FragmentUtils.changeFragment(this, new AboutAppFragment(), R.id.frame, null);
                    break;
            }
            return true;
        });
    }
}