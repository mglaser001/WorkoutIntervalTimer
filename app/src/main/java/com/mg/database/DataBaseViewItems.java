package com.mg.database;

public class DataBaseViewItems {
    private String mWorkoutName;
    private String mWorkoutTime;
    private String mWorkoutId;
    private String mWorkoutRest;
    private String mWorkoutIntervals;
    private String mWorkoutDate;


    public DataBaseViewItems( String workoutId, String workoutName, String workoutTime, String workoutRest,String workoutIntervals,String workoutDate){
        mWorkoutId = workoutId;
        mWorkoutName = workoutName;
        mWorkoutTime = workoutTime;
        mWorkoutRest = workoutRest;
        mWorkoutIntervals = workoutIntervals;
        mWorkoutDate = workoutDate;
    }

    public String getWorkoutName() {
        return mWorkoutName;
    }
    public String getWorkoutDate() {
        return mWorkoutDate;
    }

    public String getWorkoutRest() {
        return mWorkoutRest;
    }

    public String getWorkoutIntervals() {
        return mWorkoutIntervals;
    }
    public String getWorkoutTime() {
        return mWorkoutTime;
    }

    public String getmWorkoutId() {
        return mWorkoutId;
    }
}
