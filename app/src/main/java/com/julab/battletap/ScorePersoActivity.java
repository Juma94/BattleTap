package com.julab.battletap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ScorePersoActivity extends AppCompatActivity {
    private TextView titreScoreSolo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_perso);

        titreScoreSolo = (TextView)findViewById(R.id.score_perso_id);

        ListView scoreList = (ListView) findViewById(R.id.summaryList);
        scoreList.setAdapter(new ScorePersoListAdapter(getApplicationContext(), R.layout.activity_score_perso));
    }
}
