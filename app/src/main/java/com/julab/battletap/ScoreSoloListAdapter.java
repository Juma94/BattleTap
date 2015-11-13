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

public class ScoreSoloListAdapter extends ArrayAdapter
{

    public ScoreSoloListAdapter(Context context, int resource)
    {
        super(context, resource);
    }

    public int getCount()
    {
        return 10;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expand_list_perso_scores, null);
        }

        ImageView imageNumRow = (ImageView) convertView.findViewById(R.id.score_solo_couronne_id);
        TextView txtNumRow = (TextView) convertView.findViewById(R.id.temps_score_solo);
        TextView txtInfo = (TextView) convertView.findViewById(R.id.ratio_score_solo);
        TextView txtDifference = (TextView) convertView.findViewById(R.id.di_score_solo);


        return convertView;
    }
}