package com.example.mymedreminder;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddMedicationActivity extends AppCompatActivity {

    private EditText medNameInput, medDosageInput, medTimeInput;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medication);

        medNameInput = findViewById(R.id.medNameInput);
        medDosageInput = findViewById(R.id.medDosageInput);
        medTimeInput = findViewById(R.id.medTimeInput);
        Button saveMedButton = findViewById(R.id.saveMedButton);

        dbHelper = new DatabaseHelper(this);

        saveMedButton.setOnClickListener(v -> {
            String name = medNameInput.getText().toString().trim();
            String dosage = medDosageInput.getText().toString().trim();
            String time = medTimeInput.getText().toString().trim();

            if (name.isEmpty() || dosage.isEmpty() || time.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean inserted = dbHelper.insertMedication(name, dosage, time);
            if (inserted) {
                Toast.makeText(this, "Medication saved!", Toast.LENGTH_SHORT).show();
                finish(); // Go back to home
            } else {
                Toast.makeText(this, "Error saving medication", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
