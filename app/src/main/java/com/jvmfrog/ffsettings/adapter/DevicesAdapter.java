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

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.jvmfrog.ffsettings.R;
import com.jvmfrog.ffsettings.model.ParamsModel;
import com.jvmfrog.ffsettings.ui.fragment.DeviceSettingsFragment;
import com.jvmfrog.ffsettings.utils.FragmentUtils;

public class DevicesAdapter extends FirestoreRecyclerAdapter<ParamsModel, DevicesAdapter.holder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DevicesAdapter(@NonNull FirestoreRecyclerOptions<ParamsModel> options, Context context) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DevicesAdapter.holder holder, int position, @NonNull ParamsModel model) {
        holder.device_name.setText(model.getDevice_name());

        holder.itemView.setOnClickListener(v -> {
            Bundle finalBundle = new Bundle();
            finalBundle.putFloat("review", model.getReview());
            finalBundle.putFloat("collimator", model.getCollimator());
            finalBundle.putFloat("x2_scope", model.getX2_scope());
            finalBundle.putFloat("x4_scope", model.getX4_scope());
            finalBundle.putFloat("sniper_scope", model.getSniper_scope());
            finalBundle.putFloat("free_review", model.getFree_review());
            finalBundle.putFloat("dpi", model.getDpi());
            finalBundle.putFloat("fire_button", model.getFire_button());
            finalBundle.putString("settings_source_url", model.getSettings_source_url());
            FragmentUtils.changeFragmentWithBackStack((FragmentActivity) v.getContext(), new DeviceSettingsFragment(), R.id.frame, "back", finalBundle);
        });
    }

    @NonNull
    @Override
    public DevicesAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        return new holder(view);
    }

    class holder extends RecyclerView.ViewHolder {

        TextView device_name;

        public holder(@NonNull View itemView) {
            super(itemView);
            device_name = itemView.findViewById(R.id.categories);
        }
    }
}
