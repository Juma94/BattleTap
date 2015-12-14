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

import java.util.ArrayList;

import Model.Score_solo;

public class ScorePersoListAdapter extends ArrayAdapter<Score_solo>
{
    private  ArrayList<Score_solo> lstScores;
    public ScorePersoListAdapter(Context context, ArrayList<Score_solo> scores) {
        super(context, 0, scores);
        lstScores = scores;
    }
    public int getCount()
    {
        return lstScores.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        if (convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expand_list_perso_scores, null);
        }

        Score_solo sc = getItem(position);
        ImageView imageNumRow = (ImageView) convertView.findViewById(R.id.score_solo_couronne_id);
        TextView txtTemps = (TextView) convertView.findViewById(R.id.temps_score_solo);
        TextView txtRatio = (TextView) convertView.findViewById(R.id.ratio_score_solo);
        TextView txtDifference = (TextView) convertView.findViewById(R.id.di_score_solo);

        txtTemps.setText(sc.getTemps());
        double ratio = sc.getDifferenceIncrementation()/sc.getNbNombresAtteints();
        txtRatio.setText((double)sc.getDifferenceIncrementation()/sc.getNbNombresAtteints()+"");
        txtDifference.setText(sc.getDifferenceIncrementation()+"");
        return convertView;
    }
}