package com.julab.battletap;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by user on 17-10-15.
 */
public class ScoreFragment extends Fragment{
    public static final String DescriptionKey ="descriptionkey";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_score, container, false);
        Bundle bundle = getArguments();
        if(bundle!= null)
        {
            String descr = bundle.getString(DescriptionKey);
            setValues(view, descr);
        }
        return view;
    }

    private void setValues(View view, String descr) {
        TextView textV = (TextView)view.findViewById(R.id.textView2frScore);
        textV.setText(descr);
    }
}
