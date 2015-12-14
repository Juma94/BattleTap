package com.julab.battletap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Abdelhamid on 12/10/2015.
 */
public class SummaryMultiFTListAdapter extends ArrayAdapter
{
    private GlobalData globalData;

    public SummaryMultiFTListAdapter(Context context, int resource, GlobalData globalData)
    {
        super(context, resource);
        this.globalData = globalData;
    }

    public int getCount()
    {
        return globalData.getTabNumbersTaps().size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_model_summary, null);
        }

        TextView txtNumRow = (TextView) convertView.findViewById(R.id.txtNumRow);
        TextView txtInfo = (TextView) convertView.findViewById(R.id.txtInfo);
        TextView txtDifference = (TextView) convertView.findViewById(R.id.txtDifference);

        // passe d'office ici lorsqu'il y a au moins un élément dans le tableau
        int diff = (globalData.getTabNumbersToCatch().get(position) - globalData.getTabNumbersTaps().get(position));
        txtDifference.setText(globalData.getString(R.string.text_difference) + (diff > 0 ? diff : diff*-1));
        txtNumRow.setText((position + 1) + "");
        txtInfo.setText("Taps : " + globalData.getTabNumbersTaps().get(position) + globalData.getString(R.string.text_caught) + globalData.getTabNumbersToCatch().get(position));

        return convertView;
    }
}