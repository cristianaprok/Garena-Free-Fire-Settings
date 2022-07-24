package com.jvmfrog.ffsettings.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jvmfrog.ffsettings.databinding.ActivityMainBinding;
import com.jvmfrog.ffsettings.utils.AdMobUtil;
import com.jvmfrog.ffsettings.utils.FragmentUtils;
import com.jvmfrog.ffsettings.R;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private FirebaseAnalytics mFirebaseAnalytics;

    private AdRequest adRequest;
    private InterstitialAd mInterstitialAd;
    private AdMobUtil adMobUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FragmentUtils.changeFragment(this, new ManufacturerFragment(), R.id.frame, null);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        adRequest = new AdRequest.Builder().build();

        adMobUtil = new AdMobUtil(this, adRequest, mInterstitialAd);
        adMobUtil.initAdMob(this, adRequest);
        adMobUtil.loadInterstitialAd(this, getString(R.string.admob_interstellar_test_ad_id), "ADMOB");
        //adMobUtil.setFullScreenContentCallback("ADMOB_CALLBACK");

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