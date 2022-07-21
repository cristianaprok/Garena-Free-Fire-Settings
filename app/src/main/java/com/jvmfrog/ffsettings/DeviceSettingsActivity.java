package com.jvmfrog.ffsettings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jvmfrog.ffsettings.databinding.ActivityDeviceSettingsBinding;

public class DeviceSettingsActivity extends AppCompatActivity {

    private ActivityDeviceSettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeviceSettingsBinding.inflate(getLayoutInflater());



        setContentView(binding.getRoot());
    }
}