package com.julab.battletap;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
                //ConnectionActivity.this.finish();
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
    @Override
    public void onBackPressed() {
        // Dialog to say to user the game are finished
        AlertDialog.Builder dialog = new AlertDialog.Builder(ConnectionActivity.this);
        dialog.setMessage(getString(R.string.want_to_quit));
        dialog.setCancelable(false);
        dialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                ConnectionActivity.this.finish();
            }
        });
        dialog.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                Toast.makeText(ConnectionActivity.this, R.string.we_miss_you_toast, Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

}
