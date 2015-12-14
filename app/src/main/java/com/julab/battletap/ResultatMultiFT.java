package com.julab.battletap;

import android.util.Log;

/**
 * Created by Abdelhamid on 26/11/2015.
 */
public class ResultatMultiFT
{
    private int totalDiff;
    private long totalTime;

    public ResultatMultiFT(int totalDiff, long totalTime)
    {
        this.totalDiff = totalDiff;
        this.totalTime = totalTime;
    }

    public int getTotalDiff()
    {
        Log.i("DebugMulti", "Total diff result = " + totalDiff);
        return totalDiff;
    }

    public void setTotalDiff(int totalDiff)
    {
        this.totalDiff = totalDiff;
    }

    public long getTotalTime()
    {
        Log.i("DebugMulti", "Total time = " + totalTime);
        return totalTime;
    }

    public void setTotalTime(long totalTime)
    {
        this.totalTime = totalTime;
    }
}
