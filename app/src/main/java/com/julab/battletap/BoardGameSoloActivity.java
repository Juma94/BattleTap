package com.julab.battletap;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BoardGameSoloActivity extends AppCompatActivity {
    private int[] tabNumbers = new int[10];
    private TextView nbCaught, nbAAtteindre, nbTaps;
    private int current, nbCurrentTaps,  nbCurrentCaught;
    private Button btnPush, btnConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_game_solo);
        ClickListener listenerObj = new ClickListener();
        nbCurrentTaps = 0;
        nbCurrentCaught = 0;
        updateNbTaps();
        fillTabNumbers();
        current = 0;
        int i = 0;
        while(i<tabNumbers.length)
        {
            System.out.println(tabNumbers[i]);
            i++;
        }
        updateNumbersCaught();
        updateNumbersToCatch();
        btnPush = (Button)findViewById(R.id.board_game_solo_btnPush);
        btnPush.setOnClickListener(listenerObj);
        btnConfirm =(Button)findViewById(R.id.board_game_solo_btnConfirm);
        btnConfirm.setOnClickListener(listenerObj);
    }
    public void fillTabNumbers()
    {
        int increment = (int)(Math.random()*101);
        int multiplier = (int)(Math.random()*101);
        int germ = (int)(Math.random()*101);
        System.out.println("increment = " + increment);
        System.out.println("multiplier = " + multiplier);
        System.out.println("germ = " + germ);
        tabNumbers[0]= randomNumber(germ, multiplier,increment);
        int i = 1;
        while(i<tabNumbers.length)
        {
            tabNumbers[i]= randomNumber(tabNumbers[i-1],multiplier,increment);
            i++;
        }
    }
    public int randomNumber(int xPrevious, int multiplier, int increment)
    {
        return (((multiplier * xPrevious) + increment) % 100);
    }
    public void updateNumbersToCatch()
    {
        nbAAtteindre = (TextView)findViewById(R.id.board_game_solo_nb_to_catch_current_id);
        nbAAtteindre.setText(tabNumbers[current]+"");
    }
    public void updateNumbersCaught()
    {
        nbCaught = (TextView)findViewById(R.id.board_game_solo_nb_caught_current_id);
        nbCaught.setText(nbCurrentCaught + "/10");
    }
    public void updateNbTaps()
    {
        nbTaps = (TextView) findViewById(R.id.board_game_solo_tap);
        nbTaps.setText(nbCurrentTaps+"");
    }
    private class ClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            if(v == btnPush)
            {
                nbCurrentTaps++;
                nbTaps.setText(nbCurrentTaps+"");
            }
            if(v == btnConfirm)
            {
                if(nbCurrentCaught<tabNumbers.length-1)
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

                    //ATTENTION GERER LE RETOUR EN ARRIERE = INTERDIT
                }
            }

        }
    }


}
