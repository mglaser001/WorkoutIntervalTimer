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
    private TextView workoutTitle, customWorkoutRepTV, customWorkoutColonTV;
    private String workoutType;
    private EditText minutesET, secondsET, nameET, customWorkoutRepET;
    private Button okBtn;
    private Intent customTimerSelectIntent;

    private IntervalTo intervalTo;
    private List<IntervalTo> intervalToList = new ArrayList<>();
    private CustomCircuitTO customCircuitTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_timer_set);

        customTimerSelectIntent = new Intent(this, CustomTimerSelectActivity.class);
        initializeActivity();

        intervalTo = new IntervalTo();

        if(getIntent().hasExtra("name")){
            workoutType =  getIntent().getExtras().getString("name");
        }
        if(getIntent().hasExtra("customCircuitTO")){
            customCircuitTO = (CustomCircuitTO) getIntent().getSerializableExtra("customCircuitTO");
            intervalToList = customCircuitTO.getintervalToList();
        }
        else if(getIntent().hasExtra("circuitName")){
            customCircuitTO = new CustomCircuitTO();
            customCircuitTO.setName(getIntent().getExtras().getString("circuitName"));
        }


        try{
            if(workoutType.equalsIgnoreCase("timed")){
                workoutTitle.setText("TIMED INTERVAL");
                customWorkoutRepTV.setVisibility(View.GONE);
                customWorkoutRepET.setVisibility(View.GONE);
            }else if(workoutType.equalsIgnoreCase("rep")){
                workoutTitle.setText("UNTIMED REPETITION INTERVAL");
                secondsET.setVisibility(View.GONE);
                minutesET.setVisibility(View.GONE);
                customWorkoutColonTV.setVisibility(View.GONE);
            }else if(workoutType.equalsIgnoreCase("repTime")){
                workoutTitle.setText("TIMED REPETITION INTERVAL");
            }else if(workoutType.equalsIgnoreCase("rest")){
                workoutTitle.setText("REST");
                nameET.setVisibility(View.GONE);
                customWorkoutRepTV.setVisibility(View.GONE);
                customWorkoutRepET.setVisibility(View.GONE);
            }
        }catch (Exception e){
            System.out.println("No Workout Type");
        }
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intervalTo.setIntervalType(workoutTitle.getText().toString());
                //Set Interval Names
                if(workoutTitle.getText().toString().equalsIgnoreCase("REST")){
                    intervalTo.setIntervalName("REST");
                }else{
                    intervalTo.setIntervalName(nameET.getText().toString());
                }

                //Set Interval Time or Tag for no time
                if(workoutTitle.getText().toString().equalsIgnoreCase("UNTIMED REPETITION INTERVAL")){
                    intervalTo.setIntervalTime("#NOTIME");
                }else{
                    intervalTo.setIntervalTime(minutesET.getText().toString() + ":"+ secondsET.getText().toString());
                }

                //Set Interval Reps or Tag for no reps
                if(workoutTitle.getText().toString().contains("REPETITION")){
                    intervalTo.setIntervalReps(customWorkoutRepET.getText().toString());
                }else{
                    intervalTo.setIntervalReps("#NOREPS");
                }
                intervalTo.setIntervalType(workoutType);
                intervalToList.add(intervalTo);
                customCircuitTO.setintervalToList(intervalToList);
                customTimerSelectIntent.putExtra("customCircuitTO", customCircuitTO);
                startActivity(customTimerSelectIntent);
            }
        });

    }
    private void initializeActivity(){
        customWorkoutColonTV = findViewById(R.id.CustomWorkoutColonTV);
        customWorkoutRepET = findViewById(R.id.CustomWorkoutRepET);
        customWorkoutRepTV = findViewById(R.id.CustomWorkoutRepTV);
        workoutTitle = findViewById(R.id.CustomWorkoutTypeTitleTV);
        nameET = findViewById(R.id.CustomWorkoutTypeNameET);
        minutesET = findViewById(R.id.CustomWorkoutTypeMinutesET);
        secondsET = findViewById(R.id.CustomWorkoutTypeSecondsET);
        okBtn = findViewById(R.id.CustomWorkoutEnterBtn);
    }
}
