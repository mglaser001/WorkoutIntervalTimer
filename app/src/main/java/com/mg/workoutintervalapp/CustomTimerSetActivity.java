package com.mg.workoutintervalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mg.TransferObjects.CustomCircuitTO;
import com.mg.TransferObjects.IntervalTo;

import java.util.ArrayList;
import java.util.List;

public class CustomTimerSetActivity extends AppCompatActivity {
    private TextView workoutTitle;
    private String workoutType;
    private EditText minutesET, secondsET, nameET;
    private Button okBtn;
    private Intent customTimerSelect;

    private IntervalTo intervalTo;
    private List<IntervalTo> intervalToList = new ArrayList<>();
    private CustomCircuitTO customCircuitTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_timer_set);

        customTimerSelect = new Intent(this, CustomTimerSelectActivity.class);
        initializeActivity();

        intervalTo = new IntervalTo();

        customCircuitTO = new CustomCircuitTO();

        if(getIntent().hasExtra("name")){
            workoutType =  getIntent().getExtras().getString("name");
        }else if(getIntent().hasExtra("circuitName")){
            customCircuitTO.setName(getIntent().getExtras().getString("circuitName"));
        }

        try{
            if(workoutType == "timed"){
                workoutTitle.setText("TIMED");
            }else if(workoutType == "rep"){
                workoutTitle.setText("REPETITION");
            }else if(workoutType == "repTime"){
                workoutTitle.setText("TIMED REPETITION");
            }else if(workoutType == "rest"){
                workoutTitle.setText("REST");
            }
        }catch (Exception e){
            System.out.println("No Workout Type");
        }
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intervalTo.setIntervalType(workoutTitle.getText().toString());
                intervalTo.setIntervalName(nameET.getText().toString());
                intervalTo.setIntervalName(minutesET.getText().toString() + ":"+ secondsET.getText().toString());
                customCircuitTO.setintervalToList(intervalToList);
                startActivity(customTimerSelect);
            }
        });

    }
    private void initializeActivity(){
        workoutTitle = findViewById(R.id.CustomWorkoutTypeTitleTV);
        nameET = findViewById(R.id.CustomWorkoutTypeNameET);
        secondsET = findViewById(R.id.CustomWorkoutTypeMinutesET);
        minutesET = findViewById(R.id.CustomWorkoutTypeSecondsET);
        okBtn = findViewById(R.id.CustomWorkoutEnterBtn);
    }
}
