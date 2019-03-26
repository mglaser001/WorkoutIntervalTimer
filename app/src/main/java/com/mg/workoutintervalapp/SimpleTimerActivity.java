package com.mg.workoutintervalapp;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimpleTimerActivity extends AppCompatActivity {
    private MediaPlayer bell;
    private TextView countdownText;
    private TextView intervalText;

    private Button backToMenuButton;
    private Button resetWorkoutButton;
    private CountDownTimer countDownTimer;
    private CountDownTimer countDownTimer2;
    private long timeLeftInMilliseconds;
    private long threeSecondPrepare = 3000;
    private long timeToDecrement_Rest;
    private long timeToDecrement;
    private int intervalsLeft;
    private int intervalsLeftforReset;
    ConstraintLayout timerLayout;
    private boolean stopTime = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }
        setContentView(R.layout.activity_simple_timer);

        timerLayout = findViewById(R.id.simpleTimerLayout);


        bell = MediaPlayer.create(this, R.raw.boxingbell);
        intervalText = findViewById(R.id.interval_Text);
        countdownText = findViewById(R.id.countdown_Text);

        backToMenuButton = findViewById(R.id.backSimpleMenu_Btn);
        resetWorkoutButton = findViewById(R.id.simpleTimerReset_Btn);

        if (getIntent().hasExtra("timeToDecrement")) {
            timeToDecrement = getIntent().getExtras().getLong("timeToDecrement");

        }
        if (getIntent().hasExtra("intervalsLeft")) {
            intervalsLeft = getIntent().getExtras().getInt("intervalsLeft");
        }
        if (getIntent().hasExtra("restTimeToDecrement")) {
            timeToDecrement_Rest = getIntent().getExtras().getLong("restTimeToDecrement");
        }
        intervalsLeftforReset = intervalsLeft;
        startInterval();

        backToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTime = true;
                resetTimers();
                finish();
            }
        });
        resetWorkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intervalsLeft = intervalsLeftforReset;
                resetTimers();
                startInterval();
            }
        });
    }

    private void startInterval() {
        updateTimerText("Get Ready");
        updateInterval(Integer.toString(intervalsLeft));
        stopTime = false;
        timerLayout.setBackgroundColor(Color.parseColor("#7D8E32"));

        countDownTimer2 = new CountDownTimer(threeSecondPrepare + 200, 1000) {
            @Override
            public void onTick(long l) {
                threeSecondPrepare = l;
            }

            @Override
            public void onFinish() {
                if (intervalsLeft > 0) {
                    bell.start();
                    startTimer(intervalsLeft);
                }
            }
        }.start();
    }

    public void startTimer(int intervals) {
        timeLeftInMilliseconds = timeToDecrement;
        updateInterval(Integer.toString(intervals));
        timerLayout.setBackgroundColor(Color.parseColor("#11340B"));

        countDownTimer = new CountDownTimer(timeLeftInMilliseconds + 200, 1000) {
            @Override
            public void onTick(long l) {
                if (!stopTime) {
                    updateTimer(l);
                }
            }

            @Override
            public void onFinish() {

                countdownText.setText("0:00");
                intervalsLeft--;
                if (intervalsLeft > 0) {
                    startRest();
                }
            }
        }.start();

    }

    private void startRest() {
        updateRestTitle();
        timeLeftInMilliseconds = timeToDecrement_Rest;
        timerLayout.setBackgroundColor(Color.parseColor("#791111"));

        countDownTimer2 = new CountDownTimer(timeLeftInMilliseconds + 200, 1000) {
            @Override
            public void onTick(long l) {
                if (!stopTime) {
                    updateTimer(l);
                }
            }

            @Override
            public void onFinish() {
                if (intervalsLeft > 0) {
                    startTimer(intervalsLeft);
                }
            }
        }.start();
    }

    public void updateTimer(long milliseconds) {
        int minutes = (int) milliseconds / 60000;
        int seconds = ((int) milliseconds + 500) % 60000 / 1000;

        String timeLeftText;

        timeLeftText = "" + Integer.toString(minutes);
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);
    }

    private void resetTimers() {
        if (countDownTimer2 != null) {
            countDownTimer2.cancel();
        }
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void updateTimerText(String text) {
        countdownText.setText(text);
    }

    private void updateRestTitle() {
        intervalText.setText("Rest");
    }

    private void updateInterval(String intervals) {
        intervalText.setText("Round " + intervals);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
