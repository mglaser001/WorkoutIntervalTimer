package com.mg.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }
    @Override
    public  void onCreate(SQLiteDatabase sqLiteDatabase){

    }

}
