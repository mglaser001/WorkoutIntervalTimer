package com.mg.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "workout_table";
    private static final String COL1 = "ID";
    private static final String COL2 = "NAME";
    private static final String COL3 = "TIME";
    private static final String COL4 = "REST";
    private static final String COL5 = "INTERVAL";
    private static final String COL6 = "DATE";


    public DatabaseHelper(Context context){
        super(context, TABLE_NAME,null, 1);
    }

    @Override
    public  void onCreate(SQLiteDatabase db){
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " + "NAME TEXT, TIME TEXT, REST TEXT, INTERVAL TEXT, DATE TEXT)";
        db.execSQL(createTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public boolean addData(String workoutName, String workoutTime, String workoutRest, String workoutInterval) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String dateCreated = sdf.format(date);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, workoutName);
        contentValues.put(COL3, workoutTime);
        contentValues.put(COL4, workoutRest);
        contentValues.put(COL5, workoutInterval);
        contentValues.put(COL6, dateCreated);


        Log.d(TAG,"addData: Adding " + workoutName + " to " +  TABLE_NAME);

        long result = db.insert(TABLE_NAME,null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor showWorkoutDataBase(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME,null);
        return data;
    }
}
