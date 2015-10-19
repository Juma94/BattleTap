package com.julab.battletap;

import android.widget.Chronometer;

/**
 * Created by Abdelhamid on 16/10/2015.
 */
public class SummaryInfo
{
    private String pseudo;
    private double ratio;
    private int totalNumberCaught;
    private Chronometer totalTime;
    private int totalDifference;

    public SummaryInfo(String pseudo, double ratio, int totalNumberCaught, Chronometer totalTime, int totalDifference)
    {
        this.pseudo = pseudo;
        this.ratio = ratio;
        this.totalNumberCaught = totalNumberCaught;
        this.totalTime = totalTime;
        this.totalDifference = totalDifference;
    }

    public String getPseudo()
    {
        return pseudo;
    }

    public void setPseudo(String pseudo)
    {
        this.pseudo = pseudo;
    }

    public double getRatio()
    {
        return ratio;
    }

    public void setRatio(double ratio)
    {
        this.ratio = ratio;
    }

    public int getTotalNumberCaught()
    {
        return totalNumberCaught;
    }

    public void setTotalNumberCaught(int totalNumberCaught)
    {
        this.totalNumberCaught = totalNumberCaught;
    }

    public Chronometer getTotalTime()
    {
        return totalTime;
    }

    public void setTotalTime(Chronometer totalTime)
    {
        this.totalTime = totalTime;
    }

    public int getTotalDifference()
    {
        return totalDifference;
    }

    public void setTotalDifference(int totalDifference)
    {
        this.totalDifference = totalDifference;
    }
}
