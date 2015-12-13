package com.julab.battletap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity
{
    private Button btnNext;
    private TextView pseudo;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        pseudo = (TextView)findViewById(R.id.activity_create_account_pseudo_id);
        btnNext = (Button)findViewById(R.id.activity_create_account_btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pseudo.equals("Pseudo"))
                {
                    Toast.makeText(CreateAccountActivity.this,"Veullez remplir le pseudo", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(CreateAccountActivity.this, FinalizeCreateAccountActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
