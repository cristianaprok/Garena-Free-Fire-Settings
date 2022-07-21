package com.jvmfrog.ffsettings.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

        binding.textViewReview.setText("Review: " + (int) finalBundle.getFloat("review"));
        binding.sliderReview.setValue(finalBundle.getFloat("review"));
        binding.textViewCollimator.setText("Collimator: " + (int) finalBundle.getFloat("collimator"));
        binding.sliderCollimator.setValue(finalBundle.getFloat("collimator"));
        binding.textViewX2Scope.setText("x2 Scope: " + (int) finalBundle.getFloat("x2_scope"));
        binding.sliderX2Scope.setValue(finalBundle.getFloat("x2_scope"));
        binding.textViewX4Scope.setText("x4 Scope: " + (int) finalBundle.getFloat("x4_scope"));
        binding.sliderX4Scope.setValue(finalBundle.getFloat("x4_scope"));
        binding.textViewSniperScope.setText("Sniper Scope: " + (int) finalBundle.getFloat("sniper_scope"));
        binding.sliderSniperScope.setValue(finalBundle.getFloat("sniper_scope"));
        binding.textViewFreeReview.setText("Free Review: " + (int) finalBundle.getFloat("free_review"));
        binding.sliderFreeReview.setValue(finalBundle.getFloat("free_review"));

        return binding.getRoot();
    }
}