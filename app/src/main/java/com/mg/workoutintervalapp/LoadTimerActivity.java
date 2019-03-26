package com.mg.workoutintervalapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mg.Dialog.LoadWorkoutDialog;
import com.mg.database.DataBaseViewItems;
import com.mg.database.DatabaseHelper;
import com.mg.database.DatabaseItemAdapter;

import java.util.ArrayList;

public class LoadTimerActivity extends AppCompatActivity  implements LoadWorkoutDialog.LoadWorkoutDialogListener{
    private ArrayList<DataBaseViewItems> dataBaseViewItems;
    private RecyclerView mRecyclerView;
    private DatabaseItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    DatabaseHelper databaseHelper;
    DataBaseViewItems selectedDBItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (NullPointerException e) {
        }

        setContentView(R.layout.activity_load_timer);

        dataBaseViewItems = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        Cursor databaseContent = databaseHelper.getDatabaseContent();

        createList(databaseContent);
        buildRecyclerView();


        mAdapter.setOnItemClickListener(new DatabaseItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //show item details in dialog
                selectedDBItem = dataBaseViewItems.get(position);
                openDialog();
            }

            @Override
            public void onDeleteClick(int position) {
                selectedDBItem = dataBaseViewItems.get(position);
                databaseHelper.deleteDatabaseItem(Integer.parseInt(selectedDBItem.getmWorkoutId()),selectedDBItem.getWorkoutName());
                dataBaseViewItems.remove(position);

                mAdapter.notifyItemRemoved(position);

            }
        });
        if(dataBaseViewItems.isEmpty()){
            Toast.makeText(this, "No Circuits Saved!", Toast.LENGTH_SHORT).show();
        }
    }
    public void createList(Cursor databaseContent){
        while(databaseContent.moveToNext()){
            dataBaseViewItems.add(new DataBaseViewItems(databaseContent.getString(0),databaseContent.getString(1),databaseContent.getString(2),
                    databaseContent.getString(3),databaseContent.getString(4),databaseContent.getString(5)));
        }
    }
    private void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.DBRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new DatabaseItemAdapter(dataBaseViewItems);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
    private void openDialog(){
        LoadWorkoutDialog loadWorkoutDialog = new LoadWorkoutDialog();
        loadWorkoutDialog.show(getSupportFragmentManager(), "WorkoutDialog");
    }
    @Override
    public DataBaseViewItems dialogListener(){
        return selectedDBItem;
    };

}
