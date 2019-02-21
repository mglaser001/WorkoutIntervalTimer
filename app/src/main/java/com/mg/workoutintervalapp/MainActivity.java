package com.mg.workoutintervalapp;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mg.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    private Intent simpleTimerIntent, loadTimerIntent;
    private Button startButton, loadButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_main);

        startButton = findViewById(R.id.simpleWorkout_Btn);
        simpleTimerIntent = new Intent(this, SimpleTimerMenuActivity.class);

        loadButton = findViewById(R.id.MainLoad_Btn);
        loadTimerIntent = new Intent(this, LoadTimerActivity.class);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(simpleTimerIntent);
            }
        });
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(loadTimerIntent);
            }
        });
    }

}
