package com.example.mymedreminder;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MedsFragment extends Fragment {
    public MedsFragment() {
        super(R.layout.fragment_meds);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.medsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Medication> medications = new ArrayList<>();
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        Cursor cursor = dbHelper.getAllMedications();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String dosage = cursor.getString(cursor.getColumnIndexOrThrow("dosage"));
            String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
            medications.add(new Medication(id, name, dosage, time));
        }
        cursor.close();

        MedicationAdapter adapter = new MedicationAdapter(medications);
        recyclerView.setAdapter(adapter);

        adapter.setOnDeleteClickListener((medication, position) -> {
            // Show confirmation dialog
            new AlertDialog.Builder(getContext())
                    .setMessage("Delete this medication?")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        dbHelper.deleteMedication(medication.id);
                        adapter.removeItem(position);
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });
    }
}
