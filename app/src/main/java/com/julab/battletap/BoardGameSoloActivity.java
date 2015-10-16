package com.julab.battletap;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;

public class BoardGameSoloActivity extends AppCompatActivity
{
    private ArrayList<Integer> tabNumbersCaught, tabNumbersTaps;
    private TextView nbCaught, nbToCatch, nbTaps;
    private int current, nbCurrentTaps, nbCurrentCaught;
    private Button btnPush, btnConfirm;
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_game_solo);
        ClickListener listenerObj = new ClickListener();

        // init var to find the first random number
        int increment = (int) (Math.random() * 100);
        int multiplier = (int) (Math.random() * 100);
        int germ = (int) (Math.random() * 100);

        // init a list to save random numbers
        tabNumbersCaught = new ArrayList<>();
        tabNumbersCaught.add(randomNumber(germ, multiplier, increment));

        tabNumbersTaps = new ArrayList<>();

        nbCurrentTaps = 0;
        nbCurrentCaught = 0;
        current = 0;

        updateNumbers();

        btnPush = (Button) findViewById(R.id.board_game_solo_btnPush);
        btnPush.setOnTouchListener(listenerObj);

        btnConfirm = (Button) findViewById(R.id.board_game_solo_btnConfirm);
        btnConfirm.setOnTouchListener(listenerObj);

        chronometer = (Chronometer) findViewById(R.id.board_game_solo_chrono);
        chronometer.start();
    }

    public void fillTabNumbers()
    {
        int increment = (int) (Math.random() * 100);
        int multiplier = (int) (Math.random() * 100);

        tabNumbersCaught.add(randomNumber(tabNumbersCaught.get(current - 1), multiplier, increment));
    }

    public int randomNumber(int xPrevious, int multiplier, int increment)
    {
        return (((multiplier * xPrevious) + increment) % 100);
    }

    public void updateNumbers()
    {
        nbTaps = (TextView) findViewById(R.id.board_game_solo_tap);
        nbTaps.setText(nbCurrentTaps + "");

        nbCaught = (TextView) findViewById(R.id.board_game_solo_nb_caught_current_id);
        nbCaught.setText(nbCurrentCaught + getString(R.string.caught));

        nbToCatch = (TextView) findViewById(R.id.board_game_solo_nb_to_catch_current_id);
        nbToCatch.setText(tabNumbersCaught.get(current) + "");
    }

    @Override
    public void onBackPressed()
    {
        // Dialog to say to user the game are finished
        AlertDialog.Builder dialog = new AlertDialog.Builder(BoardGameSoloActivity.this);
        dialog.setMessage(getString(R.string.want_to_quit));
        dialog.setCancelable(false);
        dialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                BoardGameSoloActivity.super.onBackPressed();
            }
        });
        dialog.setNegativeButton(getString(R.string.no), null);
        dialog.show();
    }

    private class ClickListener implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                if (v.equals(btnConfirm))
                {
                    btnConfirm.setBackgroundResource(R.drawable.btn_valider_pushed);
                }
                else if (v.equals(btnPush))
                {
                    btnPush.setBackgroundResource(R.drawable.pushed_button);
                }
            }
            else if (event.getAction() == MotionEvent.ACTION_UP)
            {
                // do action when button is up for not spam when button is down
                if (v.equals(btnConfirm))
                {
                    btnConfirm.setBackgroundResource(R.drawable.btn_valider);
                    tabNumbersTaps.add(nbCurrentTaps);
                    if (tabNumbersCaught.get(current) >= nbCurrentTaps)
                    {
                        current++;
                        nbCurrentCaught++;
                        nbCurrentTaps = 0;
                        fillTabNumbers();
                        updateNumbers();
                    }
                    else
                    {
                        chronometer.stop();

                        // Dialog to say to user the game are finished
                        AlertDialog.Builder dialog = new AlertDialog.Builder(BoardGameSoloActivity.this);
                        dialog.setMessage(R.string.game_over);
                        dialog.setCancelable(false);
                        dialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int id)
                            {
                                // save datas
                                GlobalData globalData = (GlobalData) getApplicationContext();
                                globalData.setTabNumbersCaught(tabNumbersCaught);
                                globalData.setTabNumbersTaps(tabNumbersTaps);
                                globalData.setChrono(chronometer);

                                // call summary activity
                                Intent intent = new Intent(BoardGameSoloActivity.this, SummaryBoardGameSoloActivity.class);
                                startActivity(intent);
                                BoardGameSoloActivity.this.finish();
                            }
                        });
                        dialog.show();
                    }
                }
                else if (v.equals(btnPush))
                {
                    int randIncrement = (int) (1 + Math.random() * 5); // random increment
                    if (nbCurrentTaps+randIncrement > tabNumbersCaught.get(current))
                    {
                        MediaPlayer mp = MediaPlayer.create(BoardGameSoloActivity.this, R.raw.sound_btn_exceeded);
                        mp.start();
                    }

                    btnPush.setBackgroundResource(R.drawable.push_button);
                    nbCurrentTaps += randIncrement;
                    nbTaps.setText(nbCurrentTaps + "");
                }
            }

            return true;
        }
    }
}
