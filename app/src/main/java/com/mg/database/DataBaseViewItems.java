package com.mg.database;

public class DataBaseViewItems {
    private String mWorkoutName;
    private String mWorkoutTime;

    public DataBaseViewItems(String workoutName, String workoutTime){
        mWorkoutName = workoutName;
        mWorkoutTime = workoutTime;
    }

    public String getWorkoutName() {
        return mWorkoutName;
    }

    public String getWorkoutTime() {
        return mWorkoutTime;
    }
}
