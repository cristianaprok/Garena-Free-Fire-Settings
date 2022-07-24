package com.jvmfrog.ffsettings.utils;

import android.content.Context;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.jvmfrog.ffsettings.R;

public class DialogsUtil {

    public void showErrorDialog(Context context, String error) {
        new MaterialAlertDialogBuilder(context)
                .setIcon(R.drawable.ic_round_error_outline_24)
                .setTitle(R.string.error)
                .setMessage(error)
                .setNegativeButton(R.string.copy, (dialogInterface, i) -> {
                    new OtherUtils().copyTextToClipboard(context, error);
                })
                .setPositiveButton("OK", null)
                .show();
    }

    public void showLinkDialog(Context context, String url) {
        new MaterialAlertDialogBuilder(context)
                .setIcon(R.drawable.ic_round_error_outline_24)
                .setTitle(R.string.link)
                .setMessage(url)
                .setNegativeButton(R.string.copy, (dialogInterface, i) -> {
                    new OtherUtils().copyTextToClipboard(context, url);
                })
                .setPositiveButton(R.string.open_link, ((dialogInterface, i) -> {
                    new CustomTabUtil().OpenCustomTab(context, url, R.color.md_theme_light_onSecondary);
                }))
                .show();
    }
}
