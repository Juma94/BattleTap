package com.julab.battletap;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;

/**
 * Created by user on 17-10-15.
 */
public class ScorePageAdaptor extends FragmentPagerAdapter{

    String[] scoreText;

    public ScorePageAdaptor(FragmentManager fm, Context context) {
        super(fm);

        Resources resources= context.getResources();
        scoreText = resources.getStringArray(R.array.menu_score);
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putString(ScoreFragment.DescriptionKey, scoreText[position]);

        ScoreFragment scoreFragment = new ScoreFragment();
        scoreFragment.setArguments(bundle);
        return scoreFragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return scoreText[position];
    }

    @Override
    public int getCount() {
        return scoreText.length;
    }
}
