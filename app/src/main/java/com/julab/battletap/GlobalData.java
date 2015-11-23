package com.julab.battletap;

import android.widget.Chronometer;

import java.util.ArrayList;

/**
 * Created by Abdelhamid on 12/10/2015.
 */
public class GlobalData extends android.app.Application
{
    private ArrayList<Integer> tabNumbersCaught;
    private ArrayList<Integer> tabNumbersTaps;
    private Chronometer chrono;

    private SummaryInfo summaryInfo;

    public ArrayList<Integer> getTabNumbersCaught()
    {
        return tabNumbersCaught;
    }

    public void setTabNumbersCaught(ArrayList<Integer> tabNumbersCaught)
    {
        this.tabNumbersCaught = tabNumbersCaught;
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
}
