package com.jvmfrog.ffsettings.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtils {

    // create function to save boolean value in shared preference
    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    // create function to get boolean value from shared preference
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    // create function to save integer value in shared preference
    public static void saveInteger(Context context, String key, int value) {
        SharedPreferences prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    // create function to get integer value from shared preference
    public static int getInteger(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences("app_config", Context.MODE_PRIVATE);
        return prefs.getInt(key, 0);
    }
}
