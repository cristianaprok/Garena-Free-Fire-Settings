package com.jvmfrog.ffsettings.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvmfrog.ffsettings.R;
import com.jvmfrog.ffsettings.databinding.FragmentDevFunctionsBinding;

public class DevFunctionsFragment extends Fragment {

    private FragmentDevFunctionsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDevFunctionsBinding.inflate(inflater, container, false);
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}