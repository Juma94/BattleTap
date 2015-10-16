package com.julab.battletap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

public class SummaryBoardGameSoloActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_board_game_solo);

        GlobalData globalData = (GlobalData) getApplicationContext();
        ListView summaryList = (ListView) findViewById(R.id.summaryList);
        summaryList.setAdapter(new SummaryListAdapter(getApplicationContext(), R.layout.row_model_summary, globalData));

        TextView txtTotalTime = (TextView) findViewById(R.id.txtTotalTime);
        TextView txtTotalDifference = (TextView) findViewById(R.id.txtTotalDifference);
        TextView txtTotalNumbersCaught = (TextView) findViewById(R.id.txtTotalNumbersCaught);
        TextView txtRatio = (TextView) findViewById(R.id.txtRatio);

        txtTotalTime.setText("Total time : " + globalData.getChrono().getText());
        int totalDiff = 0;
        int nbElems = globalData.getTabNumbersTaps().size() - 1;
        for (int i = 0; i < nbElems; i++)
        {
            totalDiff += globalData.getTabNumbersCaught().get(i) - globalData.getTabNumbersTaps().get(i);
        }
        // add penality of the last catch number
        totalDiff -= globalData.getTabNumbersCaught().get(nbElems) - globalData.getTabNumbersTaps().get(nbElems);
        txtTotalDifference.setText("Total difference : " + totalDiff);
        // -1 because the last one are saved (the last are bigger than the numbers caught)
        txtTotalNumbersCaught.setText("Total caught : " + (globalData.getTabNumbersCaught().size() - 1));

        double ratio = totalDiff > 0 ? (double) (globalData.getTabNumbersCaught().size() - 1) / (double) totalDiff : 0;
        double ratioTwoDecimal = (double) ((int) (ratio * 100)) / 100;
        txtRatio.setText("Ratio : " + ratioTwoDecimal);
    }

    @Override
    public void onBackPressed()
    {
        // Dialog to say to user the game are finished
        AlertDialog.Builder dialog = new AlertDialog.Builder(SummaryBoardGameSoloActivity.this);
        dialog.setMessage(getString(R.string.want_to_retry));
        dialog.setCancelable(false);
        dialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                Intent intent = new Intent(SummaryBoardGameSoloActivity.this, BoardGameSoloActivity.class);
                SummaryBoardGameSoloActivity.this.startActivity(intent);
                SummaryBoardGameSoloActivity.this.finish();
            }
        });
        dialog.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                SummaryBoardGameSoloActivity.this.finish();
            }
        });
        dialog.show();
    }
}
