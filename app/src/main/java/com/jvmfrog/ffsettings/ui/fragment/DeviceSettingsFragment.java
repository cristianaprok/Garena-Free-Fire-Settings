package com.jvmfrog.ffsettings.ui.fragment;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.jvmfrog.ffsettings.R;
import com.jvmfrog.ffsettings.databinding.FragmentDeviceSettingsBinding;

public class DeviceSettingsFragment extends Fragment {

    private FragmentDeviceSettingsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDeviceSettingsBinding.inflate(inflater, container, false);

        Bundle finalBundle = new Bundle();
        finalBundle.putAll(getArguments());

        if (finalBundle.getFloat("dpi") == 0) {
            binding.textViewDPI.setVisibility(View.GONE);
        } else {
            binding.textViewDPI.setText(getString(R.string.dpi) + ":" + " " + (int) finalBundle.getFloat("dpi"));
        }

        binding.textViewReview.setText(getString(R.string.review) + ":" + " " + (int) finalBundle.getFloat("review"));
        binding.sliderReview.setValue(finalBundle.getFloat("review"));
        binding.textViewCollimator.setText(getString(R.string.collimator) + ":" + " " + (int) finalBundle.getFloat("collimator"));
        binding.sliderCollimator.setValue(finalBundle.getFloat("collimator"));
        binding.textViewX2Scope.setText(getString(R.string.x2_scope) + ":" + " " + (int) finalBundle.getFloat("x2_scope"));
        binding.sliderX2Scope.setValue(finalBundle.getFloat("x2_scope"));
        binding.textViewX4Scope.setText(getString(R.string.x4_scope) + ":" + " " + (int) finalBundle.getFloat("x4_scope"));
        binding.sliderX4Scope.setValue(finalBundle.getFloat("x4_scope"));
        binding.textViewSniperScope.setText(getString(R.string.sniper_scope) + ":" + " " + (int) finalBundle.getFloat("sniper_scope"));
        binding.sliderSniperScope.setValue(finalBundle.getFloat("sniper_scope"));
        binding.textViewFreeReview.setText(getString(R.string.free_review) + ":" + " " + (int) finalBundle.getFloat("free_review"));
        binding.sliderFreeReview.setValue(finalBundle.getFloat("free_review"));
        binding.textViewFireButton.setText(getString(R.string.fire_button) + ":" + " " + (int) finalBundle.getFloat("fire_button"));
        binding.sliderFireButton.setValue(finalBundle.getFloat("fire_button"));

        binding.textViewDPI.setContentDescription(getString(R.string.dpi) + ":" + " " + (int) finalBundle.getFloat("dpi"));
        binding.textViewReview.setContentDescription(getString(R.string.review) + ":" + " " + (int) finalBundle.getFloat("review"));
        //binding.sliderReview.setValue(finalBundle.getFloat("review"));
        binding.textViewCollimator.setContentDescription(getString(R.string.collimator) + ":" + " " + (int) finalBundle.getFloat("collimator"));
        //binding.sliderCollimator.setValue(finalBundle.getFloat("collimator"));
        binding.textViewX2Scope.setContentDescription(getString(R.string.x2_scope) + ":" + " " + (int) finalBundle.getFloat("x2_scope"));
        //binding.sliderX2Scope.setValue(finalBundle.getFloat("x2_scope"));
        binding.textViewX4Scope.setContentDescription(getString(R.string.x4_scope) + ":" + " " + (int) finalBundle.getFloat("x4_scope"));
        binding.textViewSniperScope.setContentDescription(getString(R.string.sniper_scope) + ":" + " " + (int) finalBundle.getFloat("sniper_scope"));
        binding.textViewFreeReview.setContentDescription(getString(R.string.free_review) + ":" + " " + (int) finalBundle.getFloat("free_review"));
        binding.textViewFireButton.setContentDescription(getString(R.string.fire_button) + ":" + " " + (int) finalBundle.getFloat("fire_button"));

        binding.copyButton.setOnClickListener(view -> {
            try {
                ClipboardManager clipboard = (ClipboardManager)
                        getActivity().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("simple text",
                        getString(R.string.dpi) + ":" + " " + (int) finalBundle.getFloat("dpi") + "\n" +
                                getString(R.string.review) + ":" + " " + (int) finalBundle.getFloat("review") + "\n" +
                                getString(R.string.collimator) + ":" + " " + (int) finalBundle.getFloat("collimator") + "\n" +
                                getString(R.string.x2_scope) + ":" + " " + (int) finalBundle.getFloat("x2_scope") + "\n" +
                                getString(R.string.x4_scope) + ":" + " " + (int) finalBundle.getFloat("x4_scope") + "\n" +
                                getString(R.string.sniper_scope) + ":" + " " + (int) finalBundle.getFloat("sniper_scope") + "\n" +
                                getString(R.string.free_review) + ":" + " " + (int) finalBundle.getFloat("free_review") + "\n" +
                                getString(R.string.fire_button) + ":" + " " + (int) finalBundle.getFloat("fire_button") + "\n");
                clipboard.setPrimaryClip(clip);

                Toast.makeText(getActivity(), "Скопировано", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(getActivity(), "Ошибка: " + e, Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}