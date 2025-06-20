package com.example.mymedreminder;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {
    private TextView medNameView;
    private TextView medTimeView;

    public HomeFragment() {
        super(R.layout.fragment_home);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        medNameView = view.findViewById(R.id.nextMedName);
        medTimeView = view.findViewById(R.id.nextMedTime);

        refreshNextMedication();

        // Add Medication button logic
        Button addMedicationButton = view.findViewById(R.id.addMedicationButton);
        addMedicationButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddMedicationActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Refresh medication info every time the fragment is visible
        refreshNextMedication();
    }

    private void refreshNextMedication() {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        Cursor cursor = dbHelper.getNextMedication();

        if (cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            String dosage = cursor.getString(cursor.getColumnIndexOrThrow("dosage"));
            String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));

            medNameView.setText(name + " " + dosage);
            medTimeView.setText("Today at " + time);
        } else {
            medNameView.setText("No medication");
            medTimeView.setText("");
        }
        cursor.close();
    }
}
