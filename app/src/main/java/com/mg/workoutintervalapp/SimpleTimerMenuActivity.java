package com.mg.workoutintervalapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mg.Dialog.SaveSimpleWorkoutDialog;

public class SimpleTimerMenuActivity extends AppCompatActivity {

    private Button backBtn;
    private Button startSimpleTimerButton;
    private Button saveSimpleTimerButton;
    private EditText workoutTimeMinutes;
    private EditText workoutTimeSeconds;
    private EditText restTimeMinutes;
    private EditText restTimeSeconds;
    private EditText intervalInput;

    private long restTimeToDecrement;
    private long timeToDecrement;
    private int intervalsLeft;

    private Intent simpleTimerIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_simple_timer_menu);

        saveSimpleTimerButton = findViewById(R.id.simpleTimerSave_Btn);
        startSimpleTimerButton = findViewById(R.id.simpleTimerStart_Btn);
        backBtn = findViewById(R.id.simpleTimerBack_Btn);
        simpleTimerIntent = new Intent(this, SimpleTimerActivity.class);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        startSimpleTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutTimeMinutes = findViewById(R.id.setWorkoutTimeMinutes);
                workoutTimeSeconds = findViewById(R.id.setWorkoutTimeSeconds);
                intervalInput = findViewById(R.id.setIntervals);

                String intervalInputString = intervalInput.getText().toString();
                String workoutTimeMinutesString = workoutTimeMinutes.getText().toString();
                String workoutTimeSecondsString = workoutTimeSeconds.getText().toString();

                intervalsLeft = Integer.parseInt(intervalInputString);
                timeToDecrement = (Integer.parseInt(workoutTimeMinutesString) * 60000) + Integer.parseInt(workoutTimeSecondsString) * 1000;

                restTimeMinutes = findViewById(R.id.setRestTimeMinutes);
                restTimeSeconds = findViewById(R.id.setRestTimeSeconds);

                String restTimeMinutesString = restTimeMinutes.getText().toString();
                String restTimeSecondsString = restTimeSeconds.getText().toString();
                restTimeToDecrement = (Integer.parseInt(restTimeMinutesString) * 60000) + Integer.parseInt(restTimeSecondsString) * 1000;

                simpleTimerIntent.putExtra("restTimeToDecrement", restTimeToDecrement);
                simpleTimerIntent.putExtra("timeToDecrement", timeToDecrement);
                simpleTimerIntent.putExtra("intervalsLeft", intervalsLeft);

                startActivity(simpleTimerIntent);

            }
        });

        saveSimpleTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog(){
        SaveSimpleWorkoutDialog saveWorkoutDialog = new SaveSimpleWorkoutDialog();
        saveWorkoutDialog.show(getSupportFragmentManager(), "WorkoutDialog");

    }

}
