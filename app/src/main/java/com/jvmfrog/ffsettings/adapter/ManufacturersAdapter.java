package com.jvmfrog.ffsettings.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.jvmfrog.ffsettings.R;
import com.jvmfrog.ffsettings.ui.DeviceSettingsFragment;
import com.jvmfrog.ffsettings.ui.DevicesFragment;
import com.jvmfrog.ffsettings.utils.FragmentUtils;

import java.util.ArrayList;

public class ManufacturersAdapter extends RecyclerView.Adapter<ManufacturersAdapter.holder> {

    private ArrayList<String> arrayList;
    private Context context;

    public ManufacturersAdapter(ArrayList<String> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ManufacturersAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, parent, false);

        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManufacturersAdapter.holder holder, int position) {
        holder.device_name.setText(arrayList.get(position).toString());

        Bundle finalBundle = new Bundle();

        holder.itemView.setOnClickListener(view -> {
            switch (position) {
                case 0:
                    finalBundle.putString("device", "samsung");
                    FragmentUtils.changeFragmentWithBackStack((FragmentActivity) view.getContext(), new DevicesFragment(), R.id.frame, "back", finalBundle);
                    break;
                case 1:
                    finalBundle.putString("device", "iphone");
                    FragmentUtils.changeFragmentWithBackStack((FragmentActivity) view.getContext(), new DevicesFragment(), R.id.frame, "back", finalBundle);
                    break;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.toArray().length;
    }

    public class holder extends RecyclerView.ViewHolder {

        TextView device_name;

        public holder(@NonNull View itemView) {
            super(itemView);
            device_name = itemView.findViewById(R.id.categories);
        }
    }
}
