package com.mg.database;

public class DataBaseViewItems {
    private String mWorkoutName;
    private String mWorkoutTime;
    private String mWorkoutId;

    public DataBaseViewItems( String workoutId, String workoutName, String workoutTime){
        mWorkoutId = workoutId;
        mWorkoutName = workoutName;
        mWorkoutTime = workoutTime;
    }

    public String getWorkoutName() {
        return mWorkoutName;
    }

    public String getWorkoutTime() {
        return mWorkoutTime;
    }

    public String getmWorkoutId() {
        return mWorkoutId;
    }
}
