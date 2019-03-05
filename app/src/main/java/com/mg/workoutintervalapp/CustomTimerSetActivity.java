package com.mg.workoutintervalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomTimerSetActivity extends AppCompatActivity {
    private TextView workoutTitle;
    private String workoutType;
    private EditText minutesET, secondsET, nameET;
    private Button okBtn;
    private Intent customTimerSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_timer_set);

        customTimerSelect = new Intent(this, CustomTimerSelectActivity.class);
        initializeActivity();
        if(getIntent().hasExtra("name")){
            workoutType =  getIntent().getExtras().getString("name");
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
