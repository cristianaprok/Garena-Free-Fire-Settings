package com.jvmfrog.ffsettings;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class DevicesAdapter extends FirestoreRecyclerAdapter<ParamsModel, DevicesAdapter.holder> {

    private View view;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public DevicesAdapter(@NonNull FirestoreRecyclerOptions<ParamsModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull DevicesAdapter.holder holder, int position, @NonNull ParamsModel model) {
        holder.device_name.setText(model.getDeviceName());

        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(view.getContext(), model.getDeviceName(), Toast.LENGTH_SHORT).show();
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
