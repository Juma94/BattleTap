package com.julab.battletap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HostClientActivity extends AppCompatActivity
{
    private Button btnHost, btnJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_client);
        btnHost = (Button) findViewById(R.id.btnHost_id);
        btnHost.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HostClientActivity.this, BoardGameMultiFTActivity.class);
                intent.putExtra("IsClient", false);
                intent.putExtra("IsHostSelected", false);
                startActivity(intent);
            }
        });
        btnJoin = (Button) findViewById(R.id.btnJoin_id);
        btnJoin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(HostClientActivity.this, BoardGameMultiFTActivity.class);
                intent.putExtra("IsClient", true);
                startActivity(intent);
            }
        });
    }
}
