package com.ybxcc.testinysx.ybxdemoset.aop_aspectj.pointcuts;

import java.util.concurrent.TimeUnit;

public class StopWacth {
    private long startTime;
    private long stopTime;
    private long totalTime;
    private long time;

    public StopWacth() {
        this.time = 10;
    }

    public long getTome() {
        return time;
    }

    public void setTome(int tome) {
        this.time = tome;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getStopTime() {
        return stopTime;
    }

    public void setStopTime(long stopTime) {
        this.stopTime = stopTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    private void reset() {
        startTime = 0;
        stopTime = 0;
        totalTime = 0;
    }

    public void start() {
        reset();
        startTime = System.nanoTime();
    }

    public void stop() {
        if (startTime != 0) {
            stopTime = System.nanoTime();
            totalTime = stopTime - startTime;
        } else {
            reset();
        }
    }

    public long getTotalTime() {
        return (totalTime != 0) ? TimeUnit.NANOSECONDS.toMillis(totalTime) : 0;
    }

}
