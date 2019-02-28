package com.mg.workoutintervalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CustomTimerMenuActivity extends AppCompatActivity {

    private EditText workoutNameET;
    private TextView selectTitleTV;
    private Button okBtn, timeBtn, repBtn, repTimeBtn, restBtn;
    private boolean isFirst;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_timer_menu);

        isFirst = true;
        setActivityViews();

        if(isFirst){
            selectTitleTV.setVisibility(View.INVISIBLE);
            timeBtn.setVisibility(View.INVISIBLE);
            repBtn.setVisibility(View.INVISIBLE);
            repTimeBtn.setVisibility(View.INVISIBLE);
            restBtn.setVisibility(View.INVISIBLE);
        }


        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workoutNameET.setVisibility(View.GONE);
                okBtn.setVisibility(View.GONE);

                selectTitleTV.setVisibility(View.VISIBLE);
                timeBtn.setVisibility(View.VISIBLE);
                repBtn.setVisibility(View.VISIBLE);
                repTimeBtn.setVisibility(View.VISIBLE);
                restBtn.setVisibility(View.VISIBLE);
            }
        });

    }
    private void setActivityViews(){
        workoutNameET = findViewById(R.id.custom_workoutName_ET);
        selectTitleTV = findViewById(R.id.custom_selectTitleTV);
        okBtn = findViewById(R.id.custom_ok_Btn);
        timeBtn = findViewById(R.id.custom_timed_Btn);
        repBtn = findViewById(R.id.custom_reps_Btn);
        repTimeBtn = findViewById(R.id.custom_repTimed_Btn);
        restBtn = findViewById(R.id.custom_rest_Btn);
    }
}
