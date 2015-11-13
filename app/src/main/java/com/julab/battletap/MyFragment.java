package com.julab.battletap;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by user on 25-10-15.
 */
public class MyFragment extends Fragment {
    private ListView scoreLst;

    //CONSTRUCTOR

    public MyFragment()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.activity_score_solo_fragment, container, false);
        scoreLst = (ListView)rootView.findViewById(R.id.score_ranking_fragment_listView);
        scoreLst.setAdapter(new ScoreRankingListAdapter(this.getContext(), R.layout.expand_list_perso_scores));

        return rootView;

    }
    //The newInstance() method needs to return the correct reference to the fragment
    public static MyFragment newInstance()
    {
        MyFragment fragment = new MyFragment();
        return fragment;
    }
}
