package com.mg.TransferObjects;

import java.util.List;

public class CustomCircuitTO {

    private String name;
    private List<IntervalTo> intervalToList;


    public void setintervalToList(List<IntervalTo> intervalToList) {
        this.intervalToList = intervalToList;
    }

    public List<IntervalTo> getintervalToList() {
        return intervalToList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
