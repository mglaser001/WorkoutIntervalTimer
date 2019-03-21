package com.mg.workoutintervalapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.mg.TransferObjects.CustomCircuitTO;
import com.mg.TransferObjects.IntervalTo;
import com.mg.database.DatabaseHelperCustomTimer;

import java.util.ArrayList;
import java.util.List;

public class LoadCustomTimerActivity extends AppCompatActivity {
    DatabaseHelperCustomTimer databaseHelper;
    List<CustomCircuitTO> customCircuitTOList;
    TextView test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_custom_timer);

        test = findViewById(R.id.Test);
        test.setMovementMethod(new ScrollingMovementMethod());
        customCircuitTOList = new ArrayList<>();
        databaseHelper = new DatabaseHelperCustomTimer(this);
        Cursor databaseCircuitContent = databaseHelper.getDatabaseContentCircuitTable();
        mapDatabaseContentToCircuitObject(databaseCircuitContent);
        StringBuilder sb = new StringBuilder();
        for(CustomCircuitTO c : customCircuitTOList){
            sb.append(c.getId() + " , ");
            sb.append(c.getName() + "\n");
            sb.append("        INTERVAL INFO: \n");
            for(IntervalTo i : c.getintervalToList()){
                sb.append(i.getIntervalName() + " , " +  i.getIntervalTime() + " , " + i.getIntervalReps() + " , " + i.getIntervalType() + "\n");
            }

            sb.append("\n");
        }
        test.setText(sb.toString());
        //Cursor databaseWorkoutContent = databaseHelper.getDatabaseContentWorkoutTable();
    }

    private void mapDatabaseContentToCircuitObject(Cursor databaseCircuitContent){
        while(databaseCircuitContent.moveToNext()){
            CustomCircuitTO customCircuitTO = new CustomCircuitTO();
            customCircuitTO.setId(databaseCircuitContent.getInt(0));
            customCircuitTO.setName(databaseCircuitContent.getString(1));

            Cursor databaseWorkoutContent = databaseHelper.getDatabaseContentWorkoutTable(customCircuitTO.getId());
            List<IntervalTo> intervalList = new ArrayList<>();
            while(databaseWorkoutContent.moveToNext()){
                IntervalTo intervalTo = new IntervalTo();
                intervalTo.setIntervalName(databaseWorkoutContent.getString(1));
                intervalTo.setIntervalTime(databaseWorkoutContent.getString(2));
                intervalTo.setIntervalReps(databaseWorkoutContent.getString(3));
                intervalTo.setIntervalType(databaseWorkoutContent.getString(4));
                intervalList.add(intervalTo);
            }
            customCircuitTO.setintervalToList(intervalList);
            customCircuitTOList.add(customCircuitTO);
        }
    }
}
