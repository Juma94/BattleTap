package com.julab.battletap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

public class SummaryBoardGameSoloActivity extends AppCompatActivity
{
    private GlobalData globalData;
    private ListView summaryList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_board_game_solo);

        GlobalData globalData = (GlobalData) getApplicationContext();;
        ListView summaryList = (ListView) findViewById(R.id.summaryList);
        summaryList.setAdapter(new SummaryListAdapter(getApplicationContext(), R.layout.row_model_summary, globalData));

        TextView txtTotalTime = (TextView) findViewById(R.id.txtTotalTime);
        TextView txtTotalDifference = (TextView) findViewById(R.id.txtTotalDifference);
        TextView txtTotalNumbersCaught = (TextView) findViewById(R.id.txtTotalNumbersCaught);

        txtTotalTime.setText("Total time : " + globalData.getChrono().getText());
        int totalDiff = 0;
        for(int i = 0; i < globalData.getTabNumbersTaps().size()-1; i++)
        {
            totalDiff += globalData.getTabNumbersCaught().get(i) - globalData.getTabNumbersTaps().get(i);
        }
        txtTotalDifference.setText("Total difference : " + totalDiff);
        // -1 because the last one are saved (the last are bigger than the numbers caught)
        txtTotalNumbersCaught.setText("Total caught : " + (globalData.getTabNumbersCaught().size()-1));
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }
}
