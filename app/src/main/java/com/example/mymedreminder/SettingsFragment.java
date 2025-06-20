package com.example.mymedreminder;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {
    public SettingsFragment() {
        super(R.layout.fragment_settings);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Profile section
        ImageView profileImage = view.findViewById(R.id.profileImage);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvLastName = view.findViewById(R.id.tvLastName);
        TextView tvAge = view.findViewById(R.id.tvAge);

        // Set user info as requested
        tvName.setText("Kevin");
        tvLastName.setText("Malnar");
        tvAge.setText("Age: 20");
        // profileImage.setImageResource(...) if you want a custom image

        // Settings actions
        Switch switchNotifications = view.findViewById(R.id.switch_notifications);
        Button btnExport = view.findViewById(R.id.btn_export);
        Button btnDeleteAll = view.findViewById(R.id.btn_delete_all);
        Button btnContact = view.findViewById(R.id.btn_contact);

        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Toast.makeText(getContext(), "Medication reminders " + (isChecked ? "enabled" : "disabled"), Toast.LENGTH_SHORT).show();
            // Save preference logic here
        });

        btnExport.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Exporting medication data...", Toast.LENGTH_SHORT).show();
            // Export logic here
        });

        btnDeleteAll.setOnClickListener(v -> {
            Toast.makeText(getContext(), "All data deleted.", Toast.LENGTH_SHORT).show();
            // Delete logic here
        });

        btnContact.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Contact: support@example.com", Toast.LENGTH_LONG).show();
            // Or open email intent
        });
        btnDeleteAll.setOnClickListener(v -> {
            // Show a confirmation dialog
            new android.app.AlertDialog.Builder(getContext())
                    .setTitle("Delete All Data")
                    .setMessage("Are you sure you want to delete all medications? This cannot be undone.")
                    .setPositiveButton("Delete", (dialog, which) -> {
                        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
                        dbHelper.deleteAllMedications();
                        Toast.makeText(getContext(), "All medication data deleted.", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });

    }
}
