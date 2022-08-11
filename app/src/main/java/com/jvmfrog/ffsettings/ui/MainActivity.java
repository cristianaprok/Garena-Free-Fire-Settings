package com.jvmfrog.ffsettings.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;

import android.app.Application;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.tasks.OnCompleteListener;
import com.google.android.play.core.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.jvmfrog.ffsettings.MyApplication;
import com.jvmfrog.ffsettings.databinding.ActivityMainBinding;
import com.jvmfrog.ffsettings.ui.fragment.AboutAppFragment;
import com.jvmfrog.ffsettings.ui.fragment.ManufacturerFragment;
import com.jvmfrog.ffsettings.utils.FragmentUtils;
import com.jvmfrog.ffsettings.R;
import com.jvmfrog.ffsettings.utils.OtherUtils;
import com.jvmfrog.ffsettings.utils.SharedPreferencesUtils;
import com.sanojpunchihewa.updatemanager.UpdateManager;
import com.sanojpunchihewa.updatemanager.UpdateManagerConstant;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private int MY_REQUEST_CODE = 100;

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseRemoteConfig mFirebaseRemoteConfig;

    private UpdateManager mUpdateManager;
    private String in_app_update_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bottomAppBar();

        FragmentUtils.changeFragment(this, new ManufacturerFragment(), R.id.frame, null);
        /*mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.default_remote_configs);
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        mFirebaseRemoteConfig.fetchAndActivate()
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        boolean updated = task.getResult();
                        Log.d("TAG", "Config params updated: " + updated);
                        Toast.makeText(MainActivity.this, "Fetch and activate succeeded",
                                Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, "Fetch failed",
                                Toast.LENGTH_SHORT).show();
                    }
                    in_app_update_type = FirebaseRemoteConfig.getInstance().getString("in_app_update_type");
                    //checkUpdate(in_app_update_type);
                });*/

        // Show the app open ad
        Application application = getApplication();
        ((MyApplication) application)
                .showAdIfAvailable(
                        MainActivity.this,
                        () -> {
                            //
                        });
    }

    private void bottomAppBar() {
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