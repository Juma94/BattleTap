package com.julab.battletap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class FinalizeCreateAccountActivity extends AppCompatActivity {
    private Spinner secretQSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_create_account);
        secretQSpinner = (Spinner)findViewById(R.id.activity_finalize_create_account_secret_question_id);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Question, android.R.layout.simple_spinner_item);
        secretQSpinner.setAdapter(adapter);
    }
}
