package com.example.mymedreminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedViewHolder> {

    private List<Medication> medicationList;
    private OnDeleteClickListener onDeleteClickListener;

    public interface OnDeleteClickListener {
        void onDeleteClick(Medication medication, int position);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.onDeleteClickListener = listener;
    }

    public MedicationAdapter(List<Medication> medicationList) {
        this.medicationList = medicationList;
    }

    @NonNull
    @Override
    public MedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_medication, parent, false);
        return new MedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MedViewHolder holder, int position) {
        Medication med = medicationList.get(position);
        holder.name.setText(med.name);
        holder.dosage.setText(med.dosage);
        holder.time.setText(med.time);

        holder.deleteButton.setOnClickListener(v -> {
            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClick(med, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return medicationList.size();
    }

    public void removeItem(int position) {
        medicationList.remove(position);
        notifyItemRemoved(position);
    }

    public static class MedViewHolder extends RecyclerView.ViewHolder {
        TextView name, dosage, time;
        ImageButton deleteButton;

        public MedViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.medName);
            dosage = itemView.findViewById(R.id.medDosage);
            time = itemView.findViewById(R.id.medTime);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
