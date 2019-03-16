package com.mg.workoutintervalapp;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mg.TransferObjects.CustomCircuitTO;
import com.mg.TransferObjects.IntervalTo;

public class CustomTimerActivity extends AppCompatActivity {
    private MediaPlayer bell;
    private TextView countdownText, intervalNameText, titleText;
    private Button backToMenuButton, nextWorkoutButton;
    private CountDownTimer countDownTimer, countDownTimer2;
    private long timeLeftInMilliseconds;
    private long threeSecondPrepare = 3000;
    private long timeToDecrement_Rest;
    private long timeToDecrement;
    private int intervalsLeft, pos;
    private int intervalsLeftforReset;
    RelativeLayout timerLayout;
    private boolean stopTime = false;
    private CustomCircuitTO customCircuitTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_timer);

        timerLayout = findViewById(R.id.customTimerLayout);

        bell = MediaPlayer.create(this,R.raw.boxingbell);
        titleText = findViewById(R.id.CustomCircuitNameTV);
        intervalNameText = findViewById(R.id.CustomCircuitIntervalNameTV);
        countdownText = findViewById(R.id.CustomCircuitTimerTV);

        backToMenuButton = findViewById(R.id.CustomCircuitDoneBTN);
        nextWorkoutButton = findViewById(R.id.CustomCircuitNextBTN);
        nextWorkoutButton.setVisibility(View.GONE);

        if(getIntent().hasExtra("timeToDecrement")){
            timeToDecrement = getIntent().getExtras().getLong("timeToDecrement");
        }
        if(getIntent().hasExtra("customCircuitTO")){
            customCircuitTO = (CustomCircuitTO) getIntent().getSerializableExtra("customCircuitTO");
        }
        if(getIntent().hasExtra("intervalsLeft")){
            intervalsLeft = getIntent().getExtras().getInt("intervalsLeft");
        }
        if(getIntent().hasExtra("restTimeToDecrement")){
            timeToDecrement_Rest = getIntent().getExtras().getLong("restTimeToDecrement");
        }
        //Custom Timer Stuff
        titleText.setText(customCircuitTO.getName());

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
//        resetWorkoutButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intervalsLeft = intervalsLeftforReset;
//                resetTimers();
//                startInterval();
//            }
//        });
    }
    private void startInterval() {
        updateTimerText("Get Ready");
        updateIntervalTitle("");
        stopTime = false;
        timerLayout.setBackgroundColor(Color.parseColor("#7D8E32"));

        countDownTimer2 = new CountDownTimer(threeSecondPrepare+200, 1000) {
            @Override
            public void onTick(long l) {
                threeSecondPrepare = l;
            }

            @Override
            public void onFinish() {
//                bell.start();
                IntervalTo firstInterval = customCircuitTO.getintervalToList().get(0);
                startTimer(firstInterval , 0);
            }
        }.start();
    }

    public void startTimer(IntervalTo intervalTo, int position) {
        timerLayout.setBackgroundColor(Color.parseColor("#11340B"));
        this.pos = position;
        updateIntervalTitle(intervalTo.getIntervalName());

        if(intervalTo.getIntervalTime().equalsIgnoreCase("#NOTIME")){
            updateTimerText(" Do " + intervalTo.getIntervalReps() + " Reps");
            nextWorkoutButton.setVisibility(View.VISIBLE);
            nextWorkoutButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos++;
                    startTimer(customCircuitTO.getintervalToList().get(pos), pos);
                }
            });

        }else{
            nextWorkoutButton.setVisibility(View.GONE);
            timeLeftInMilliseconds = convertToMilliseconds(intervalTo.getIntervalTime());

            countDownTimer = new CountDownTimer(timeLeftInMilliseconds + 200, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer(l);
                }

                @Override
                public void onFinish() {
                    countdownText.setText("0:00");
                    pos++;
                    if(pos < customCircuitTO.getintervalToList().size()){
                        startTimer(customCircuitTO.getintervalToList().get(pos), pos);
                    }else{
                        updateTimerText("DONE!");
                    }
                }
            }.start();
        }

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
    private long convertToMilliseconds(String timeString){
        String minuteString = timeString.split(":")[0];
        String secondString = timeString.split(":")[1];

        Integer minutes = Integer.parseInt(minuteString);
        Integer seconds = Integer.parseInt(secondString);

        long milliseconds = (minutes*60000) + (seconds*1000);
        return milliseconds;
    }
    private void resetTimers(){
        if(countDownTimer2 != null){
            countDownTimer2.cancel();
        }
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }
    public void updateTimerText(String text) {
        countdownText.setText(text);
    }

    private void updateIntervalTitle(String intervalName) {
        intervalNameText.setText(intervalName);
    }
}
