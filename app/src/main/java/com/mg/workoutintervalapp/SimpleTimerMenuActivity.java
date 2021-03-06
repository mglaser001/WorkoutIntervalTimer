package com.mg.workoutintervalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mg.Dialog.SaveSimpleWorkoutDialog;
import com.mg.database.DatabaseHelper;

public class SimpleTimerMenuActivity extends AppCompatActivity implements SaveSimpleWorkoutDialog.SimpleWorkoutDialogListener {

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
    DatabaseHelper mDatabaseHelper;

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

        simpleTimerIntent = new Intent(this, SimpleTimerActivity.class);
        mDatabaseHelper = new DatabaseHelper(this);

        startSimpleTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutTimeMinutes = findViewById(R.id.setWorkoutTimeMinutes);
                workoutTimeSeconds = findViewById(R.id.setWorkoutTimeSeconds);
                intervalInput = findViewById(R.id.setIntervals);

                String intervalInputString = intervalInput.getText().toString();
                if (intervalInputString.isEmpty()) {
                    intervalInputString = "1";
                }
                String workoutTimeMinutesString = workoutTimeMinutes.getText().toString();
                if (workoutTimeMinutesString.isEmpty()) {
                    workoutTimeMinutesString = "00";
                }
                String workoutTimeSecondsString = workoutTimeSeconds.getText().toString();
                if (workoutTimeSecondsString.isEmpty()) {
                    workoutTimeSecondsString = "00";
                }

                intervalsLeft = Integer.parseInt(intervalInputString);
                timeToDecrement = (Integer.parseInt(workoutTimeMinutesString) * 60000) + Integer.parseInt(workoutTimeSecondsString) * 1000;

                restTimeMinutes = findViewById(R.id.setRestTimeMinutes);
                restTimeSeconds = findViewById(R.id.setRestTimeSeconds);

                String restTimeMinutesString = restTimeMinutes.getText().toString();
                String restTimeSecondsString = restTimeSeconds.getText().toString();
                if (restTimeMinutesString.isEmpty()) {
                    restTimeMinutesString = "00";
                }
                if (restTimeSecondsString.isEmpty()) {
                    restTimeSecondsString = "00";
                }
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

    private void openDialog() {
        SaveSimpleWorkoutDialog saveWorkoutDialog = new SaveSimpleWorkoutDialog();
        saveWorkoutDialog.show(getSupportFragmentManager(), "WorkoutDialog");
    }

    @Override
    public void applyToDatabase(String workoutName) {
        workoutTimeMinutes = findViewById(R.id.setWorkoutTimeMinutes);
        workoutTimeSeconds = findViewById(R.id.setWorkoutTimeSeconds);
        intervalInput = findViewById(R.id.setIntervals);

        String dbIntervals = intervalInput.getText().toString();

        String workoutTimeMinutesString = workoutTimeMinutes.getText().toString();
        String workoutTimeSecondsString = workoutTimeSeconds.getText().toString();
        String dbWorkoutTime = workoutTimeMinutesString + ":" + workoutTimeSecondsString;

        restTimeMinutes = findViewById(R.id.setRestTimeMinutes);
        restTimeSeconds = findViewById(R.id.setRestTimeSeconds);

        String restTimeMinutesString = restTimeMinutes.getText().toString();
        String restTimeSecondsString = restTimeSeconds.getText().toString();
        String dbRestTime = restTimeMinutesString + ":" + restTimeSecondsString;

        //workout name, workout time, rest time, intervals
        boolean insertData = mDatabaseHelper.addData(workoutName, dbWorkoutTime, dbRestTime, dbIntervals);

        if (insertData) {
            Toast.makeText(SimpleTimerMenuActivity.this, "Workout Successfully Saved!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(SimpleTimerMenuActivity.this, "An Error Occurred!", Toast.LENGTH_LONG).show();
        }
    }

}
