package com.julab.battletap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity
{
    private Button btnNext;
    private EditText pseudo;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        pseudo = (EditText)findViewById(R.id.activity_create_account_pseudo_id);
        btnNext = (Button)findViewById(R.id.activity_create_account_btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(pseudo.getEditableText().equals(""))
                    {
                        Toast.makeText(CreateAccountActivity.this,"Veullez remplir le pseudo : " + pseudo.getEditableText(), Toast.LENGTH_LONG).show();

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
