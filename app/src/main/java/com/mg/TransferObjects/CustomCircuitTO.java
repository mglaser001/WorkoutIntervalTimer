package com.mg.TransferObjects;

import java.io.Serializable;
import java.util.List;

public class CustomCircuitTO implements Serializable {
    private int id;
    private String name;
    private String date;
    private List<IntervalTo> intervalToList;


    public void setintervalToList(List<IntervalTo> intervalToList) {
        this.intervalToList = intervalToList;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
