package com.example.mymedreminder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedViewHolder> {

    private List<Medication> medicationList;

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
    }

    @Override
    public int getItemCount() {
        return medicationList.size();
    }

    public static class MedViewHolder extends RecyclerView.ViewHolder {
        TextView name, dosage, time;
        public MedViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.medName);
            dosage = itemView.findViewById(R.id.medDosage);
            time = itemView.findViewById(R.id.medTime);
        }
    }
}
