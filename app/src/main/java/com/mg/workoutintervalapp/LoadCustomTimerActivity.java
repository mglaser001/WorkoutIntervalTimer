package com.mg.workoutintervalapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mg.Dialog.LoadCustomWorkoutDialog;
import com.mg.TransferObjects.CustomCircuitTO;
import com.mg.TransferObjects.IntervalTo;
import com.mg.database.DatabaseCustomItemAdapter;
import com.mg.database.DatabaseHelperCustomTimer;

import java.util.ArrayList;
import java.util.List;

public class LoadCustomTimerActivity extends AppCompatActivity implements LoadCustomWorkoutDialog.LoadWorkoutDialogListener {
    DatabaseHelperCustomTimer databaseHelper;
    ArrayList<CustomCircuitTO> customCircuitTOList;
    private RecyclerView mRecyclerView;
    private DatabaseCustomItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CustomCircuitTO selectedDBItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_timer);

        customCircuitTOList = new ArrayList<>();
        databaseHelper = new DatabaseHelperCustomTimer(this);
        Cursor databaseCircuitContent = databaseHelper.getDatabaseContentCircuitTable();

        mapDatabaseContentToCircuitObject(databaseCircuitContent);
        buildRecyclerView();

        mAdapter.setOnItemClickListener(new DatabaseCustomItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //show item details in dialog
                selectedDBItem = customCircuitTOList.get(position);
                openDialog();
            }

            @Override
            public void onDeleteClick(int position) {
                selectedDBItem = customCircuitTOList.get(position);
                databaseHelper.deleteDatabaseItem(selectedDBItem.getId(), selectedDBItem.getName());
                customCircuitTOList.remove(position);

                mAdapter.notifyItemRemoved(position);

            }
        });
        if (customCircuitTOList.isEmpty()) {
            Toast.makeText(this, "No Circuits Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.DBRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new DatabaseCustomItemAdapter(customCircuitTOList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void mapDatabaseContentToCircuitObject(Cursor databaseCircuitContent) {
        while (databaseCircuitContent.moveToNext()) {
            CustomCircuitTO customCircuitTO = new CustomCircuitTO();
            customCircuitTO.setId(databaseCircuitContent.getInt(0));
            customCircuitTO.setName(databaseCircuitContent.getString(1));
            customCircuitTO.setDate(databaseCircuitContent.getString(2));

            Cursor databaseWorkoutContent = databaseHelper.getDatabaseContentWorkoutTable(customCircuitTO.getId());
            List<IntervalTo> intervalList = new ArrayList<>();
            while (databaseWorkoutContent.moveToNext()) {
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

    private void openDialog() {
        LoadCustomWorkoutDialog loadWorkoutDialog = new LoadCustomWorkoutDialog();
        loadWorkoutDialog.show(getSupportFragmentManager(), "WorkoutDialog");
    }

    @Override
    public CustomCircuitTO dialogListener() {
        return selectedDBItem;
    }

    ;
}
