package com.julab.battletap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BoardGameSoloActivity extends AppCompatActivity
{
    private ArrayList<Integer> tabNumbers;
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

        int increment = (int) (Math.random() * 101);
        int multiplier = (int) (Math.random() * 101);
        int germ = (int) (Math.random() * 101);

        tabNumbers = new ArrayList<Integer>();
        tabNumbers.add(randomNumber(germ, multiplier, increment));

        nbCurrentTaps = 0;
        nbCurrentCaught = 0;
        current = 0;

        updateNbTaps();
        updateNumbersCaught();
        updateNumbersToCatch();
        btnPush = (Button) findViewById(R.id.board_game_solo_btnPush);
        btnPush.setOnTouchListener(listenerObj);
        btnConfirm = (Button) findViewById(R.id.board_game_solo_btnConfirm);
        btnConfirm.setOnTouchListener(listenerObj);
        chronometer = (Chronometer) findViewById(R.id.board_game_solo_chrono);
        chronometer.start();
    }

    public void fillTabNumbers()
    {
        int increment = (int) (Math.random() * 101);
        int multiplier = (int) (Math.random() * 101);
        int germ = (int) (Math.random() * 101);

        System.out.println("increment = " + increment);
        System.out.println("multiplier = " + multiplier);
        System.out.println("germ = " + germ);

        tabNumbers.add(randomNumber(tabNumbers.get(current-1), multiplier, increment));
    }

    public int randomNumber(int xPrevious, int multiplier, int increment)
    {
        return (((multiplier * xPrevious) + increment) % 100);
    }

    public void updateNumbersToCatch()
    {
        nbToCatch = (TextView) findViewById(R.id.board_game_solo_nb_to_catch_current_id);
        nbToCatch.setText(tabNumbers.get(current) + "");
    }

    public void updateNumbersCaught()
    {
        nbCaught = (TextView) findViewById(R.id.board_game_solo_nb_caught_current_id);
        nbCaught.setText(nbCurrentCaught + "/10");
    }

    public void updateNbTaps()
    {
        nbTaps = (TextView) findViewById(R.id.board_game_solo_tap);
        nbTaps.setText(nbCurrentTaps + "");
    }

    private class ClickListener implements View.OnTouchListener
    {
        @Override
        public boolean onTouch(View v, MotionEvent event)
        {
            if(event.getAction() == event.ACTION_DOWN)
            {
                if(v.equals(btnConfirm))
                {
                    btnConfirm.setBackgroundResource(R.drawable.btn_valider_pushed);
                }
                else if(v.equals(btnPush))
                {
                    btnPush.setBackgroundResource(R.drawable.pushed_button);
                }
            }
            else if(event.getAction() == event.ACTION_UP)
            {
                // do action when button is up for not spam when button is down
                if(v.equals(btnConfirm))
                {
                    btnConfirm.setBackgroundResource(R.drawable.btn_valider);
                    if (tabNumbers.get(current) > nbCurrentCaught)
                    {
                        current++;
                        updateNumbersToCatch();
                        nbCurrentCaught++;
                        updateNumbersCaught();
                        nbCurrentTaps = 0;
                        updateNbTaps();
                        fillTabNumbers();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "plus de chiffre a atteindre", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(BoardGameSoloActivity.this, SummaryBoardGameSoloActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                else if(v.equals(btnPush))
                {
                    btnPush.setBackgroundResource(R.drawable.push_button);
                    nbCurrentTaps += (int) (1 + Math.random() * 5); // incrementation aleatoire
                    nbTaps.setText(nbCurrentTaps + "");
                }
            }

            return true;
        }
    }
}
