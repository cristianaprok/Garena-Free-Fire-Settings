package com.jvmfrog.ffsettings.ui;

import static com.google.android.play.core.install.model.ActivityResult.RESULT_IN_APP_UPDATE_FAILED;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import android.app.Application;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.InstallState;
import com.google.android.play.core.install.InstallStateUpdatedListener;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.InstallStatus;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.Task;
import com.google.android.ump.ConsentDebugSettings;
import com.google.android.ump.ConsentForm;
import com.google.android.ump.ConsentInformation;
import com.google.android.ump.ConsentRequestParameters;
import com.google.android.ump.UserMessagingPlatform;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;
import com.jvmfrog.ffsettings.MyApplication;
import com.jvmfrog.ffsettings.databinding.ActivityMainBinding;
import com.jvmfrog.ffsettings.ui.fragment.AboutAppFragment;
import com.jvmfrog.ffsettings.ui.fragment.ManufacturerFragment;
import com.jvmfrog.ffsettings.utils.FragmentUtils;
import com.jvmfrog.ffsettings.R;
import com.jvmfrog.ffsettings.utils.SharedPreferencesUtils;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private FirebaseAnalytics mFirebaseAnalytics;
    private ConsentInformation consentInformation;
    private ConsentForm consentForm;

    private Boolean isFirstOpen;
    private String inAppUpdateType = "flexible";
    public static int UPDATE_CODE = 100;
    AppUpdateManager appUpdateManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirstOpen = SharedPreferencesUtils.getBoolean(this, "isFirstOpen");
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        bottomAppBar();
        firstOpenDialog();
        initConsent();

        FragmentUtils.changeFragment(this, new ManufacturerFragment(), R.id.frame, null);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Application application = getApplication();
        if (isFirstOpen == true) {
            // Show the app open ad
            ((MyApplication) application)
                    .showAdIfAvailable(
                            MainActivity.this,
                            () -> {
                                //
                            });
        }
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

    private void firstOpenDialog() {
        if (isFirstOpen == false) {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(this);
            builder.setIcon(R.drawable.ic_round_insert_emoticon_24);
            builder.setTitle(R.string.welcome);
            builder.setMessage(R.string.welcome_message);
            builder.setPositiveButton("OK", (dialog, which) -> {
                isFirstOpen = true;
                SharedPreferencesUtils.saveBoolean(this, "isFirstOpen", true);
            });
            builder.show();
        }
    }

    private void initConsent() {
        // Set tag for underage of consent. false means users are not underage.
        ConsentRequestParameters params = new ConsentRequestParameters
                .Builder()
                .setAdMobAppId(getString(R.string.admob_app_id))
                .setTagForUnderAgeOfConsent(false)
                .build();

        // Debug settings for Form
        ConsentDebugSettings debugSettings = new ConsentDebugSettings.Builder(this)
                .setDebugGeography(ConsentDebugSettings
                        .DebugGeography
                        .DEBUG_GEOGRAPHY_EEA)
                .addTestDeviceHashedId("AF0F2B6E3BCDC6ACBFD315C64B00")
                .build();

        ConsentRequestParameters debugParams = new ConsentRequestParameters
                .Builder()
                .setConsentDebugSettings(debugSettings)
                .build();

        consentInformation = UserMessagingPlatform.getConsentInformation(this);
        consentInformation.requestConsentInfoUpdate(
                this,
                params,
                () -> {
                    // The consent information state was updated.
                    // You are now ready to check if a form is available.
                    if (consentInformation.isConsentFormAvailable()) {
                        loadForm();
                    }
                },
                formError -> {
                    // Handle the error.
                }
        );
    }
    private void loadForm() {
        UserMessagingPlatform.loadConsentForm(
                this,
                consentForm -> {
                    MainActivity.this.consentForm = consentForm;
                    if (consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.UNKNOWN) {
                        consentForm.show(
                                this,
                                formError -> {
                                    // Handle dismissal by reloading form.
                                    loadForm();
                                }
                        );
                    }
                    if (consentInformation.getConsentStatus() == ConsentInformation.ConsentStatus.REQUIRED) {
                        consentForm.show(
                                this,
                                formError -> {
                                    // Handle dismissal by reloading form.
                                    loadForm();
                                }
                        );
                    }
                },
                formError -> {
                    // Handle the error
                }
        );
    }

    @Override
    protected void onStart() {
        super.onStart();
        appUpdateManager = AppUpdateManagerFactory.create(this);
        appUpdateManager.registerListener(installStateUpdatedListener);
        appUpdateManager.getAppUpdateInfo().addOnSuccessListener(appUpdateInfo -> {
            if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.FLEXIBLE /*AppUpdateType.IMMEDIATE*/)){
                try {
                    appUpdateManager.startUpdateFlowForResult(
                            appUpdateInfo, AppUpdateType.FLEXIBLE /*AppUpdateType.IMMEDIATE*/,
                            MainActivity.this,
                            UPDATE_CODE);
                } catch (IntentSender.SendIntentException e) {
                    e.printStackTrace();
                }
            } else if (appUpdateInfo.installStatus() == InstallStatus.DOWNLOADED){
                popupSnackbarForCompleteUpdate();
            } else {
                Log.e("TAG", "checkForAppUpdateAvailability: something else");
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (appUpdateManager != null) {
            appUpdateManager.unregisterListener(installStateUpdatedListener);
        }
    }

    InstallStateUpdatedListener installStateUpdatedListener = new
            InstallStateUpdatedListener() {
                @Override
                public void onStateUpdate(InstallState state) {
                    if (state.installStatus() == InstallStatus.DOWNLOADED){
                        //CHECK THIS if AppUpdateType.FLEXIBLE, otherwise you can skip
                        popupSnackbarForCompleteUpdate();
                    } else if (state.installStatus() == InstallStatus.INSTALLED){
                        if (appUpdateManager != null){
                            appUpdateManager.unregisterListener(installStateUpdatedListener);
                        }
                    } else {
                        Log.i("TAG", "InstallStateUpdatedListener: state: " + state.installStatus());
                    }
                }
            };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == UPDATE_CODE) {
            if (resultCode != RESULT_OK) {
                Log.e("TAG", "onActivityResult: app download failed");
            }
        }
    }

    private void popupSnackbarForCompleteUpdate() {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "New app is ready!", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Install", view -> {
            if (appUpdateManager != null){
                appUpdateManager.completeUpdate();
            }
        });
        //snackbar.setActionTextColor(getResources().getColor(R.color.install_color));
        snackbar.show();
    }
}