package com.mg.workoutintervalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mg.TransferObjects.CustomCircuitTO;

public class CustomTimerMenuActivity extends AppCompatActivity {

    private EditText workoutNameET;
    private TextView selectTitleTV;
    private Button okBtn, timeBtn, repBtn, repTimeBtn, restBtn;
    private Intent setWorkoutIntent;
    private CustomCircuitTO customCircuitTO;

    private String circuitName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_timer_menu);

        setWorkoutIntent = new Intent(this, CustomTimerSetActivity.class);
        setActivityViews();


        selectTitleTV.setVisibility(View.INVISIBLE);
        timeBtn.setVisibility(View.INVISIBLE);
        repBtn.setVisibility(View.INVISIBLE);
        repTimeBtn.setVisibility(View.INVISIBLE);
        restBtn.setVisibility(View.INVISIBLE);

        customCircuitTO = new CustomCircuitTO();

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean continueToSelect = true;
                if (workoutNameET.getText().toString().equals("")) {
                    continueToSelect = false;
                    Toast.makeText(CustomTimerMenuActivity.this, "Please Enter Circuit Name!", Toast.LENGTH_LONG).show();
                }

                if (continueToSelect) {
                    circuitName = workoutNameET.getText().toString();
                    customCircuitTO.setName(circuitName);

                    workoutNameET.setVisibility(View.GONE);
                    okBtn.setVisibility(View.GONE);

                    selectTitleTV.setVisibility(View.VISIBLE);
                    timeBtn.setVisibility(View.VISIBLE);
                    repBtn.setVisibility(View.VISIBLE);
                    repTimeBtn.setVisibility(View.VISIBLE);
                    restBtn.setVisibility(View.VISIBLE);
                }
            }
        });
        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWorkoutIntent.putExtra("name", "timed");
                setWorkoutIntent.putExtra("circuitName", customCircuitTO.getName());
                startActivity(setWorkoutIntent);
            }
        });
        repBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWorkoutIntent.putExtra("name", "rep");
                setWorkoutIntent.putExtra("circuitName", customCircuitTO.getName());
                startActivity(setWorkoutIntent);
            }
        });
        repTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWorkoutIntent.putExtra("name", "repTime");
                setWorkoutIntent.putExtra("circuitName", customCircuitTO.getName());
                startActivity(setWorkoutIntent);
            }
        });
        restBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWorkoutIntent.putExtra("name", "rest");
                setWorkoutIntent.putExtra("circuitName", customCircuitTO.getName());
                startActivity(setWorkoutIntent);
            }
        });
    }

    private void setActivityViews() {
        workoutNameET = findViewById(R.id.custom_workoutName_ET);
        selectTitleTV = findViewById(R.id.custom_selectTitleTV);
        okBtn = findViewById(R.id.custom_ok_Btn);
        timeBtn = findViewById(R.id.custom_timed_Btn);
        repBtn = findViewById(R.id.custom_reps_Btn);
        repTimeBtn = findViewById(R.id.custom_repTimed_Btn);
        restBtn = findViewById(R.id.custom_rest_Btn);
    }
}
