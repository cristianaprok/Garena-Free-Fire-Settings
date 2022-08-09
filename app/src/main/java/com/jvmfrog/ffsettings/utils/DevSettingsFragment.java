package com.jvmfrog.ffsettings.utils;

import android.app.Activity;
import android.app.UiModeManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.jvmfrog.ffsettings.R;

public class DevSettingsFragment extends PreferenceFragmentCompat
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    public static final Boolean isAdsShowing = false;
    public static final String darkMode = "system";

    private ListPreference setDarkMode;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        setDarkMode = findPreference(darkMode);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences name, String key) {
        Log.d("onSharedPreferenceChanged", key);
        UiModeManager uiManager = (UiModeManager) getActivity().getSystemService(Context.UI_MODE_SERVICE);

        if (key.equals(darkMode)) {
            String value = name.getString(key, "system");
            if (value.equals("system")) {
                uiManager.setNightMode(UiModeManager.MODE_NIGHT_AUTO);
            } else if (value.equals("dark")) {
                uiManager.setNightMode(UiModeManager.MODE_NIGHT_YES);
            } else if (value.equals("light")) {
                uiManager.setNightMode(UiModeManager.MODE_NIGHT_NO);
            }
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mPrefs = PreferenceManager.getDefaultSharedPreferences(activity);
        mEditor = mPrefs.edit();
        mPrefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPrefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences prefs = getPreferenceManager().getSharedPreferences();

        // CHANGE 1: load saved values to set the summaries
        onSharedPreferenceChanged(prefs, darkMode);

        // CHANGE 2: register shared prefs listener in onResume
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        SharedPreferences prefs = getPreferenceManager().getSharedPreferences();
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }
}