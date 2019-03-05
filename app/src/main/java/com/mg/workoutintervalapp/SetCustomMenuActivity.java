package com.mg.workoutintervalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SetCustomMenuActivity extends AppCompatActivity {
    private TextView workoutTitle;
    private String workoutType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_custom_menu);

        workoutTitle = findViewById(R.id.CustomWorkoutTypeTitleTV);
        if(getIntent().hasExtra("name")){
            workoutType =  getIntent().getExtras().getString("name");
        }

        try{
            workoutTitle.setText(workoutType);
        }catch (Exception e){
            System.out.println("No Workout Type");
        }
        if(workoutType == "timed"){

        }else if(workoutType == "rep"){

        }else if(workoutType == "repTime"){

        }else if(workoutType == "rest"){

        }
    }
}
