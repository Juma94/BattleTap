package com.julab.battletap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SummaryBoardGameSoloActivity extends AppCompatActivity {
    private Button btnBackToMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_board_game_solo);
        ClickListener listenerObj = new ClickListener();
        btnBackToMenu = (Button)findViewById(R.id.summary_board_game_solo_activity_btnBack_id);
        btnBackToMenu.setOnClickListener(listenerObj);
    }
    private class ClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            if(v == btnBackToMenu)
            {
                    Toast.makeText(getApplicationContext(), "Retour au menu", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SummaryBoardGameSoloActivity.this, MenuActivity.class);
                    startActivity(intent);

                //ATTENTION RETOUR EN ARRIERE = RETOUR MENU
            }
        }

    }
}
