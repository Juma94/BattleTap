package com.julab.battletap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class CreateAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }
}
