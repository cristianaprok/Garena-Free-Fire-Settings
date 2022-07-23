package com.jvmfrog.ffsettings.utils;

import android.content.Context;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabColorSchemeParams;
import androidx.browser.customtabs.CustomTabsIntent;

public class CustomTabUtil {

    public void OpenCustomTab(Context context, String URL, int colorInt) {
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(context, Uri.parse(URL));

        //Set tab color
        CustomTabColorSchemeParams defaultColors = new CustomTabColorSchemeParams.Builder()
                .setToolbarColor(colorInt)
                .build();
        builder/*intentBuilder*/.setDefaultColorSchemeParams(defaultColors);

        //Set animation
        //builder.setStartAnimations(this, R.anim.slide_in_right, R.anim.slide_out_left);
        //builder.setExitAnimations(this, R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
