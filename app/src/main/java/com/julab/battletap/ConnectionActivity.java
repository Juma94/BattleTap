package com.julab.battletap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConnectionActivity extends AppCompatActivity
{
    private Button btnConnection;
    private TextView btnCreateAccount, btnPwdForgotten;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        btnConnection = (Button)findViewById(R.id.connectionButton);
        btnConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConnectionActivity.this, MenuActivity.class));
            }
        });
        btnCreateAccount = (TextView)findViewById(R.id.connectionActivityCreateAccountBtnId);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConnectionActivity.this,CreateAccountActivity.class));
            }
        });
        btnPwdForgotten = (TextView)findViewById(R.id.connection_activity_pwdForgottenId);
        btnPwdForgotten.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ConnectionActivity.this,PasswordForgottenActivity.class));
            }
        });
    }

}
