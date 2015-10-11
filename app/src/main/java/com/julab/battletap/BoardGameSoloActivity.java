package com.julab.battletap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BoardGameSoloActivity extends AppCompatActivity
{
    private int[] tabNumbers = new int[10];
    private TextView nbCaught, nbToCatch, nbTaps;
    private int current, nbCurrentTaps, nbCurrentCaught;
    private Button btnPush, btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_game_solo);
        ClickListener listenerObj = new ClickListener();
        nbCurrentTaps = 0;
        nbCurrentCaught = 0;
        updateNbTaps();
        fillTabNumbers();
        current = 0;
        int i = 0;
        while (i < tabNumbers.length)
        {
            System.out.println(tabNumbers[i]);
            i++;
        }
        updateNumbersCaught();
        updateNumbersToCatch();
        btnPush = (Button) findViewById(R.id.board_game_solo_btnPush);
        btnPush.setOnTouchListener(listenerObj);
        btnConfirm = (Button) findViewById(R.id.board_game_solo_btnConfirm);
        btnConfirm.setOnTouchListener(listenerObj);
    }

    public void fillTabNumbers()
    {
        int increment = (int) (Math.random() * 101);
        int multiplier = (int) (Math.random() * 101);
        int germ = (int) (Math.random() * 101);
        System.out.println("increment = " + increment);
        System.out.println("multiplier = " + multiplier);
        System.out.println("germ = " + germ);
        tabNumbers[0] = randomNumber(germ, multiplier, increment);
        int i = 1;
        while (i < tabNumbers.length)
        {
            tabNumbers[i] = randomNumber(tabNumbers[i - 1], multiplier, increment);
            i++;
        }
    }

    public int randomNumber(int xPrevious, int multiplier, int increment)
    {
        return (((multiplier * xPrevious) + increment) % 100);
    }

    public void updateNumbersToCatch()
    {
        nbToCatch = (TextView) findViewById(R.id.board_game_solo_nb_to_catch_current_id);
        nbToCatch.setText(tabNumbers[current] + "");
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
                    if (nbCurrentCaught < tabNumbers.length - 1)
                    {
                        current++;
                        updateNumbersToCatch();
                        nbCurrentCaught++;
                        updateNumbersCaught();
                        nbCurrentTaps = 0;
                        updateNbTaps();
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
                    nbCurrentTaps++;
                    nbTaps.setText(nbCurrentTaps + "");
                }
            }

            return true;
        }
    }
}
