package com.example.saviour;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AmbulanceTypeActivity extends AppCompatActivity {
    private RadioGroup ambulanceTypeRadioGroup;
    private Button selectButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_type);

        ambulanceTypeRadioGroup = findViewById(R.id.ambulanceTypeRadioGroup);
        selectButton = findViewById(R.id.selectButton);

        selectButton.setOnClickListener(v -> {
            int selectedId = ambulanceTypeRadioGroup.getCheckedRadioButtonId();

            if (selectedId != -1) {
                RadioButton selectedRadioButton = findViewById(selectedId);
                String selectedAmbulanceType = selectedRadioButton.getText().toString();


                showToast("Selected Ambulance Type: " + selectedAmbulanceType);
                Intent intent = new Intent(this, DriverMapsActivity.class);
                this.startActivity(intent);
            } else {
                showToast("Please select an ambulance type.");
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

