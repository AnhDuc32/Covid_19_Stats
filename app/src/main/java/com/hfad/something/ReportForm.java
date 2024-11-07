package com.hfad.something;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ReportForm extends AppCompatActivity {

    private RadioGroup radioGroupOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_form);

        // Enable the Up button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Find views by ID
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        Button submitButton = findViewById(R.id.submitButton);

        // Handle submit button click
        submitButton.setOnClickListener(v -> {
            // Get the selected radio button ID
            int selectedOptionId = radioGroupOptions.getCheckedRadioButtonId();

            // If no option is selected, show a toast message
            if (selectedOptionId == -1) {
                Toast.makeText(ReportForm.this, "Please select an option!", Toast.LENGTH_SHORT).show();
            } else {
                // Find the selected RadioButton
                RadioButton selectedRadioButton = findViewById(selectedOptionId);
                String selectedAnswer = selectedRadioButton.getText().toString();

                // Show the selected answer in a Toast message
                Toast.makeText(ReportForm.this, "You selected: " + selectedAnswer, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Navigate back when the Up button is pressed
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
