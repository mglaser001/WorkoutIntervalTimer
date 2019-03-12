package com.mg.TransferObjects;

import java.io.Serializable;

public class IntervalTo implements Serializable {
    private String intervalName;
    private String intervalTime;
    private String intervalType;
    private String intervalReps;

    public String getIntervalName() {
        return intervalName;
    }

    public void setIntervalName(String intervalName) {
        this.intervalName = intervalName;
    }

    public String getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(String intervalTime) {
        this.intervalTime = intervalTime;
    }

    public String getIntervalType() {
        return intervalType;
    }

    public void setIntervalType(String intervalType) {
        this.intervalType = intervalType;
    }

    public String getIntervalReps() {
        return intervalReps;
    }

    public void setIntervalReps(String intervalReps) {
        this.intervalReps = intervalReps;
    }
}
