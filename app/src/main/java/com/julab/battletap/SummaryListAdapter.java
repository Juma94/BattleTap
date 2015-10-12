package com.julab.battletap;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Abdelhamid on 12/10/2015.
 */
public class SummaryListAdapter extends ArrayAdapter
{
    private GlobalData globalData;

    public SummaryListAdapter(Context context, int resource, GlobalData globalData)
    {
        super(context, resource);
        Log.i("Summary", "Constructor");
        this.globalData = globalData;
    }

    public int getCount()
    {
        return globalData.getTabNumbersCaught().size()-1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Log.i("Summary", "Enter in getView()");
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
        {
            Log.i("Summary", "convertView is null");
            convertView = inflater.inflate(R.layout.row_model_summary, null);
            Log.i("Summary", convertView == null ? "convertView is still null" : convertView.toString());
        }

        Log.i("Summary", "Init textViews");
        TextView txtNumRow = (TextView) convertView.findViewById(R.id.txtNumRow);
        TextView txtInfo = (TextView) convertView.findViewById(R.id.txtInfo);
        TextView txtDifference = (TextView) convertView.findViewById(R.id.txtDifference);
        Log.i("Summary", "End init textViews");

        Log.i("Summary", "set Text of textViews");
        txtNumRow.setText((position+1)+"");
        txtDifference.setText("Difference : " + (globalData.getTabNumbersCaught().get(position) - globalData.getTabNumbersTaps().get(position)));
        txtInfo.setText("Taps : " + globalData.getTabNumbersTaps().get(position) + " Caught : " + globalData.getTabNumbersCaught().get(position));

        return convertView;
    }
}
