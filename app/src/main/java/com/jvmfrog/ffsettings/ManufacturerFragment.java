package com.jvmfrog.ffsettings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvmfrog.ffsettings.databinding.FragmentManufacturerBinding;

public class ManufacturerFragment extends Fragment {

    private FragmentManufacturerBinding binding;
    private View.OnClickListener onClickListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManufacturerBinding.inflate(inflater, container, false);

        Bundle finalBundle = new Bundle();

        binding.samsung.setOnClickListener(view -> {
            finalBundle.putString("device", "samsung");
            FragmentUtils.changeFragmentWithBackStack(getActivity(), new DevicesFragment(), R.id.frame, "back", finalBundle);
        });

        binding.iphone.setOnClickListener(view -> {
            finalBundle.putString("device", "iphone");
            FragmentUtils.changeFragmentWithBackStack(getActivity(), new DevicesFragment(), R.id.frame, "back", finalBundle);
        });

        return binding.getRoot();
    }
}