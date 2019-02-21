package com.mg.workoutintervalapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mg.database.DataBaseViewItems;
import com.mg.database.DatabaseHelper;
import com.mg.database.DatabaseItemAdapter;

import java.util.ArrayList;

public class LoadTimerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_timer);

        ArrayList<DataBaseViewItems> dataBaseViewItems = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        Cursor databaseContent = databaseHelper.getDatabaseContent();

        while(databaseContent.moveToNext()){
            dataBaseViewItems.add(new DataBaseViewItems(databaseContent.getString(1),databaseContent.getString(2)));
        }
       // dataBaseViewItems.add(new DataBaseViewItems("ex1", "ex2"));
       // dataBaseViewItems.add(new DataBaseViewItems("ex1", "ex2"));
       // dataBaseViewItems.add(new DataBaseViewItems("ex1", "ex2"));

        mRecyclerView = findViewById(R.id.DBRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new DatabaseItemAdapter(dataBaseViewItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
