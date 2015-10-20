package com.julab.battletap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

public class FinalizeCreateAccountActivity extends AppCompatActivity {
    private Spinner secretQSpinner;
    private Button btnFinalize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_create_account);
        secretQSpinner = (Spinner)findViewById(R.id.activity_finalize_create_account_secret_question_id);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Question, android.R.layout.simple_spinner_item);
        secretQSpinner.setAdapter(adapter);
        btnFinalize = (Button)findViewById(R.id.activity_finalize_create_account_btnFinalize_id);
        btnFinalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FinalizeCreateAccountActivity.this, ConnectionActivity.class);
                startActivity(intent);
            }
        });
    }
}
