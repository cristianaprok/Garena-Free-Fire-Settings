package com.jvmfrog.ffsettings.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jvmfrog.ffsettings.adapter.ManufacturersAdapter;
import com.jvmfrog.ffsettings.utils.FragmentUtils;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentManufacturerBinding.inflate(inflater, container, false);

        Bundle finalBundle = new Bundle();

        arrayList = new ArrayList<String>();
        arrayList.add("Samsung");
        arrayList.add("iPhone");
        arrayList.add("Xiaomi");
        arrayList.add("Redmi");
        arrayList.add("PocoPhone");
        arrayList.add("OnePlus");
        arrayList.add("Oppo");
        arrayList.add("Nokia");

        LinearLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        binding.recview.setLayoutManager(layoutManager);
        ManufacturersAdapter adapter = new ManufacturersAdapter(arrayList, getActivity());
        binding.recview.setAdapter(adapter);

        return binding.getRoot();
    }
}