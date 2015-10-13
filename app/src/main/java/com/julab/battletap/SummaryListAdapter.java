package com.julab.battletap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        this.globalData = globalData;
    }

    public int getCount()
    {
        return globalData.getTabNumbersCaught().size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.row_model_summary, null);
        }


        ImageView imageNumRow = (ImageView) convertView.findViewById(R.id.backgroundNumRow);
        TextView txtNumRow = (TextView) convertView.findViewById(R.id.txtNumRow);
        TextView txtInfo = (TextView) convertView.findViewById(R.id.txtInfo);
        TextView txtDifference = (TextView) convertView.findViewById(R.id.txtDifference);

        if(position == getCount()-1)
        {
            imageNumRow.setBackgroundColor(Color.parseColor("#ff8b00"));
            txtDifference.setText(globalData.getString(R.string.text_penality) + (globalData.getTabNumbersTaps().get(position) - globalData.getTabNumbersCaught().get(position)));
        }
        else
        {
            txtDifference.setText(globalData.getString(R.string.text_difference) + (globalData.getTabNumbersCaught().get(position) - globalData.getTabNumbersTaps().get(position)));
        }

        txtNumRow.setText((position+1)+"");
        txtInfo.setText("Taps : " + globalData.getTabNumbersTaps().get(position) + globalData.getString(R.string.text_caught) + globalData.getTabNumbersCaught().get(position));


        return convertView;
    }
}