package com.julab.battletap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 25-10-15.
 */
public class MyFragment extends Fragment {
    //CONSTRUCTOR
    public MyFragment()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.activity_score_solo_fragment, container, false);


        return rootView;

    }
    //The newInstance() method needs to return the correct reference to the fragment
    public static MyFragment newInstance()
    {
        MyFragment fragment = new MyFragment();
        return fragment;
    }
}
