package com.jvmfrog.ffsettings.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;
import com.jvmfrog.ffsettings.R;

public class InAppReviewHelper {

    // call it to display in-app review, you should not have a call-to-action option (such as a button) to trigger the API,
    // as a user might have already hit their quota and the flow won’t be shown. In this case call reviewAppOnGooglePlay method
    public void displayInAppReview(Activity activity) {

        ReviewManager manager = ReviewManagerFactory.create(activity);
        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // We can get the ReviewInfo object
                ReviewInfo reviewInfo = task.getResult();

                Task<Void> flow = manager.launchReviewFlow(activity, reviewInfo);
                flow.addOnCompleteListener(task2 -> {
                    Toast.makeText(activity, R.string.thank_for_review, Toast.LENGTH_LONG).show();
                });

            } else {
                // There was some problem, continue regardless of the result.
            }
        });
    }

    // call this method to redirect user to the application's page on google play
    public void reviewAppOnGooglePlay(Activity activity) {
        try {
            Uri uri = Uri.parse("market://details?id=" + activity.getPackageName());
            Intent appLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
            try {
                appLinkToMarket.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.startActivity(appLinkToMarket);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(activity, R.string.thank_for_review, Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            //e.toString();
        }
    }
}
