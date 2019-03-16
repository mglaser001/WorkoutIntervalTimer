package com.mg.workoutintervalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mg.TransferObjects.CustomCircuitTO;
import com.mg.TransferObjects.IntervalTo;

public class CustomTimerSelectActivity extends AppCompatActivity {

    private Button saveBtn, timeBtn, repBtn, repTimeBtn, restBtn;
    private Intent setWorkoutIntent, setTimerIntent;
    private IntervalTo intervalTo;
    private CustomCircuitTO customCircuitTO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_timer_select);

        setTimerIntent = new Intent(this, CustomTimerActivity.class);
        setWorkoutIntent = new Intent(this, CustomTimerSetActivity.class);
        setActivityViews();

        timeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWorkoutIntent.putExtra("customCircuitTO", getIntent().getSerializableExtra("customCircuitTO"));
                setWorkoutIntent.putExtra("name", "timed");
                startActivity(setWorkoutIntent);
            }
        });
        repBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWorkoutIntent.putExtra("customCircuitTO", getIntent().getSerializableExtra("customCircuitTO"));
                setWorkoutIntent.putExtra("name", "rep");
                startActivity(setWorkoutIntent);
            }
        });
        repTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWorkoutIntent.putExtra("customCircuitTO", getIntent().getSerializableExtra("customCircuitTO"));
                setWorkoutIntent.putExtra("name", "repTime");
                startActivity(setWorkoutIntent);
            }
        });
        restBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWorkoutIntent.putExtra("customCircuitTO", getIntent().getSerializableExtra("customCircuitTO"));
                setWorkoutIntent.putExtra("name", "rest");
                startActivity(setWorkoutIntent);
            }
        });
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTimerIntent.putExtra("customCircuitTO", getIntent().getSerializableExtra("customCircuitTO"));
                startActivity(setTimerIntent);
            }
        });
    }

    private void setActivityViews(){
        saveBtn = findViewById(R.id.custom_save_Btn);
        timeBtn = findViewById(R.id.custom_timed_Btn);
        repBtn = findViewById(R.id.custom_reps_Btn);
        repTimeBtn = findViewById(R.id.custom_repTimed_Btn);
        restBtn = findViewById(R.id.custom_rest_Btn);
    }
}
