package com.mg.TransferObjects;

import java.util.List;

public class CustomCircuitTO {

    private String name;
    private List<IntervalTo> intervalToLisr;


    public void setintervalToLisr(List<IntervalTo> intervalToLisr) {
        this.intervalToLisr = intervalToLisr;
    }

    public List<IntervalTo> getintervalToLisr() {
        return intervalToLisr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
