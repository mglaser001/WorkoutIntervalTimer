package com.mg.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mg.TransferObjects.CustomCircuitTO;
import com.mg.TransferObjects.IntervalTo;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class DatabaseHelperCustomTimer extends SQLiteOpenHelper{
    private static final String TAG = "DatabaseHelperCustom";
    //columns of the circuit table
    private static final String CIRCUIT_TABLE_NAME = "circuit_table_names";
    private static final String COLUMN_CIRCUIT_ID = "ID";
    private static final String COLUMN_CIRCUIT_NAME = "NAME";
    private static final String COLUMN_CIRCUIT_DATE_CREATED = "DATE";

    //columns of the workout table
    private static final String WORKOUT_TABLE_NAME = "workout_table_names";
    private static final String COLUMN_WORKOUT_ID = "ID";
    private static final String COLUMN_WORKOUT_NAME = "NAME";
    private static final String COLUMN_WORKOUT_TIME = "TIME";
    private static final String COLUMN_WORKOUT_REPETITIONS = "REPS";
    private static final String COLUMN_WORKOUT_TYPE = "TYPE";
    private static final String COLUMN_WORKOUT_CIRCUIT_ID = "CIRCUIT_ID";

    private static final String DATABASE_NAME = "customtimer.db";
    private static final int DATABASE_VERSION = 1;
    //create circuit table statement
    private static final String SQL_CREATE_TABLE_CIRCUIT = "CREATE TABLE " + CIRCUIT_TABLE_NAME + "("
            + COLUMN_CIRCUIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CIRCUIT_NAME + "  TEXT NOT NULL, "
            + COLUMN_CIRCUIT_DATE_CREATED + " TEXT NOT NULL"
            + ");";
    //create workout table statement
    private static final String SQL_CREATE_TABLE_WORKOUT = "CREATE TABLE " + WORKOUT_TABLE_NAME + "("
            + COLUMN_WORKOUT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_WORKOUT_NAME + "  TEXT NOT NULL, "
            + COLUMN_WORKOUT_TIME + " TEXT NOT NULL, "
            + COLUMN_WORKOUT_REPETITIONS + " TEXT NOT NULL, "
            + COLUMN_WORKOUT_TYPE + " TEXT, "
            + COLUMN_WORKOUT_CIRCUIT_ID + " INTEGER NOT NULL"
            + ");";

    public DatabaseHelperCustomTimer(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public  void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_TABLE_WORKOUT);
        db.execSQL(SQL_CREATE_TABLE_CIRCUIT);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.w(TAG, "Upgrading the database from version " + oldVersion + " to " + newVersion);
       //clears data
        db.execSQL("DROP TABLE IF EXISTS " + CIRCUIT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + WORKOUT_TABLE_NAME);

        //recreates tables
        onCreate(db);
    }
    public boolean addDataToCircuitTable(CustomCircuitTO customCircuitTO) {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String dateCreated = sdf.format(date);

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CIRCUIT_NAME, customCircuitTO.getName());
        contentValues.put(COLUMN_CIRCUIT_DATE_CREATED, dateCreated);


        Log.d(TAG,"addData: Adding " + customCircuitTO.getName() + " to " + CIRCUIT_TABLE_NAME);

        long result = db.insert(CIRCUIT_TABLE_NAME,null, contentValues);

        if(result == -1){
            return false;
        }else{
            return true;
        }

    }
    public boolean addDataToWorkoutTable(CustomCircuitTO customCircuitTO){
        List<IntervalTo> intervalList = customCircuitTO.getintervalToList();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT " + COLUMN_CIRCUIT_ID + " FROM " + CIRCUIT_TABLE_NAME + " WHERE " + COLUMN_CIRCUIT_NAME + " = '" + customCircuitTO.getName() + "'",null);
        int circuitId = 0;
        if(data.moveToNext()){
            circuitId = data.getInt(0);
        }
        long result = 0;
        for(IntervalTo intervalTo: intervalList){
            ContentValues contentValuesWorkout = new ContentValues();
            contentValuesWorkout.put(COLUMN_WORKOUT_NAME, intervalTo.getIntervalName());
            contentValuesWorkout.put(COLUMN_WORKOUT_TIME, intervalTo.getIntervalTime());
            contentValuesWorkout.put(COLUMN_WORKOUT_REPETITIONS, intervalTo.getIntervalReps());
            contentValuesWorkout.put(COLUMN_WORKOUT_TYPE, intervalTo.getIntervalType());
            contentValuesWorkout.put(COLUMN_WORKOUT_CIRCUIT_ID, circuitId);
            if(result != -1){
                Log.d(TAG,"addData: Adding " + intervalTo.getIntervalName() + " to " + WORKOUT_TABLE_NAME);
                result = db.insert(WORKOUT_TABLE_NAME,null, contentValuesWorkout);
            }
        }
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getDatabaseContentCircuitTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + CIRCUIT_TABLE_NAME,null);
        return data;
    }
    public Cursor getDatabaseContentWorkoutTable(int circuitId){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + WORKOUT_TABLE_NAME + " WHERE " + COLUMN_WORKOUT_CIRCUIT_ID + " = " + circuitId,null);
        return data;
    }
    public void deleteDatabaseItem(int id, String name){
        SQLiteDatabase db = this.getWritableDatabase();
//ended here
        String query = "DELETE FROM " + CIRCUIT_TABLE_NAME + " WHERE " + COLUMN_CIRCUIT_ID + " = '" + id + "'";
        Log.d(TAG, "delete query: " + query);
        Log.d(TAG, "deleting from: " + name);
        db.execSQL(query);
    }
}
