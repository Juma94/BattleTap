package com.julab.battletap;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

public class SummaryBoardGameMultiFTActivity extends AppCompatActivity
{

    private TextView txtTotalTime;
    private TextView txtTotalDiff;
    private TextView txtWinOrLoose;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_board_game_ft_multi);

        GlobalData globalData = (GlobalData) getApplicationContext();
        ListView summaryList = (ListView) findViewById(R.id.listView);
        summaryList.setAdapter(new SummaryMultiFTListAdapter(getApplicationContext(), R.layout.row_model_summary, globalData));

        txtWinOrLoose = (TextView) findViewById(R.id.txtWinOrLoose);
        if(globalData.isWinOrLoose())
        {
            txtWinOrLoose.setText(R.string.win);
        }
        else
        {
            txtWinOrLoose.setText(R.string.loose);
            txtWinOrLoose.setTextColor(Color.RED);
        }
        txtTotalTime = (TextView) findViewById(R.id.txtTotalTime);
        txtTotalTime.setText("Total time : " + ((GlobalData) getApplicationContext()).getSummaryInfo().getTotalTime().getText());
        txtTotalDiff = (TextView) findViewById(R.id.txtTotalDifference);
        txtTotalDiff.setText("Total difference : " + ((GlobalData) getApplicationContext()).getTotalDifference());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_summary_board_game_multi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
