package com.yeyangshu.serviceorderdispatch.task;

import lombok.Data;

import java.util.List;

/**
 *
 */
@Data
public class TaskCondition {

    private int freeTimeBefore;

    private int freeTimeAfter;

    /**
     * 距离
     */
    private int distance;

    private int nextTime;

    private int driverNum;

    /**
     * 距离集合
     */
    private List<Integer> distanceList;

    private int compareType;

    public TaskCondition(int freeTimeBefore, int freeTimeAfter, int distance, int nextTime, int driverNum, List<Integer> distanceList, int compareType) {
        this.freeTimeBefore = freeTimeBefore;
        this.freeTimeAfter = freeTimeAfter;
        this.distance = distance;
        this.nextTime = nextTime;
        this.driverNum = driverNum;
        this.distanceList = distanceList;
        this.compareType = compareType;
    }
}