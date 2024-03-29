package com.jvmfrog.ffsettings.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jvmfrog.ffsettings.adapter.ManufacturersAdapter;
import com.jvmfrog.ffsettings.utils.CustomTabUtil;
import com.jvmfrog.ffsettings.R;
import com.jvmfrog.ffsettings.databinding.FragmentManufacturerBinding;
import java.util.ArrayList;

public class ManufacturerFragment extends Fragment {

    private FragmentManufacturerBinding binding;
    private ArrayList<String> arrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManufacturerBinding.inflate(inflater, container, false);

        arrayList = new ArrayList<>();
        arrayList.add("Samsung");
        arrayList.add("iPhone");
        arrayList.add("Xiaomi");
        arrayList.add("Redmi");
        arrayList.add("Oppo");
        arrayList.add("Huawei");

        LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        binding.recview.setLayoutManager(layoutManager);
        ManufacturersAdapter adapter = new ManufacturersAdapter(arrayList);
        binding.recview.setAdapter(adapter);

        binding.googleFormBtn.setOnClickListener(view -> new CustomTabUtil().OpenCustomTab(getActivity(), getString(R.string.google_form), R.color.md_theme_light_onSecondary));

        return binding.getRoot();
    }
}