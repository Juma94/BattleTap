package com.julab.battletap;

import android.util.Log;
import android.widget.Chronometer;

import java.util.ArrayList;

/**
 * Created by Abdelhamid on 12/10/2015.
 */
public class GlobalData extends android.app.Application
{
    private ArrayList<Integer> tabNumbersToCatch;
    private ArrayList<Integer> tabNumbersTaps;
    private Chronometer chrono;
    private boolean winOrLoose;
    private SummaryInfo summaryInfo;

    public ArrayList<Integer> getTabNumbersToCatch()
    {
        return tabNumbersToCatch;
    }

    public void setTabNumbersToCatch(ArrayList<Integer> tabNumbersToCatch)
    {
        this.tabNumbersToCatch = tabNumbersToCatch;
    }

    public ArrayList<Integer> getTabNumbersTaps()
    {
        return tabNumbersTaps;
    }

    public void setTabNumbersTaps(ArrayList<Integer> tabNumbersTaps)
    {
        this.tabNumbersTaps = tabNumbersTaps;
    }

    public Chronometer getChrono()
    {
        return chrono;
    }

    public void setChrono(Chronometer chrono)
    {
        this.chrono = chrono;
    }

    public boolean isLastItem(int position)
    {
        return position == tabNumbersTaps.size();
    }

    public SummaryInfo getSummaryInfo()
    {
        return summaryInfo;
    }

    public void setSummaryInfo(SummaryInfo summaryInfo)
    {
        this.summaryInfo = summaryInfo;
    }

    public int getTotalDifference()
    {
        int diffTotal = 0;
        for(int i = 0; i < tabNumbersTaps.size(); i++)
        {
            int diff = tabNumbersToCatch.get(i) - tabNumbersTaps.get(i);
            diffTotal += diff > 0 ? diff : diff*-1;
        }
        Log.i("DebugMulti", "Total diff = " + diffTotal);
        return diffTotal;
    }

    public boolean isWinOrLoose()
    {
        return winOrLoose;
    }

    public void setWinOrLoose(boolean winOrLoose)
    {
        this.winOrLoose = winOrLoose;
    }
}
