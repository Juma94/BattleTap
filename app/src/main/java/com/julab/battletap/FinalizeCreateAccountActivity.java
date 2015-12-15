package com.julab.battletap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import Model.Continent;
import Model.Joueur;
import Model.QuestionSecrete;
import Model.Score_solo;

public class FinalizeCreateAccountActivity extends AppCompatActivity {
    private Spinner secretQSpinner;
    private Button btnFinalize;
    private Spinner continentSpinner;
    private EditText reponseSecrete;
    private RadioButton isHomme, isFemme;
    private ArrayList<Continent> lstContinents;
    private ArrayList<QuestionSecrete> lstQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_create_account);
        Bundle obj = this.getIntent().getExtras();
        final Joueur nouveauJoueur = (Joueur)obj.get("joueurEnCreation");
        lstContinents = new ArrayList<Continent>();
        lstQuestions = new ArrayList<QuestionSecrete>();
        continentSpinner = (Spinner)findViewById(R.id.activity_finalize_create_account_country_id);
        secretQSpinner = (Spinner)findViewById(R.id.activity_finalize_create_account_secret_question_id);
        reponseSecrete = (EditText)findViewById(R.id.activity_finalize_create_account_secret_response_id);
        isHomme = (RadioButton) findViewById(R.id.activity_finalize_create_account_radioButtonMale_id);
        isFemme = (RadioButton) findViewById(R.id.activity_finalize_create_account_radioButtoFemale_id);

        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        JsonArrayRequest requestObjContinent = new JsonArrayRequest(Request.Method.GET,"http://battletapapi.azurewebsites.net/api/continents", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                conversionJSONObjectContinent(response);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(FinalizeCreateAccountActivity.this, "Erreur : " + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        requestObjContinent.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(requestObjContinent);

        JsonArrayRequest requestObjQuestions = new JsonArrayRequest(Request.Method.GET,"http://battletapapi.azurewebsites.net/api/question_secrete", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                conversionJSONObjectQuestions(response);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(FinalizeCreateAccountActivity.this, "Erreur : " + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        requestObjQuestions.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(requestObjQuestions);

        btnFinalize = (Button)findViewById(R.id.activity_finalize_create_account_btnFinalize_id);
        btnFinalize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(reponseSecrete.length() < 1 || reponseSecrete.length()>100)
                {
                    Toast.makeText(FinalizeCreateAccountActivity.this,"La reponse ne peut dépasser 100 caractère et doit comprendre au moins 1 caractère", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(isHomme.isChecked())
                    {
                        nouveauJoueur.setSexe('M');
                        nouveauJoueur.setIdQuestion(lstQuestions.get(secretQSpinner.getSelectedItemPosition()).getIdQuestion());
                        nouveauJoueur.setReference(lstContinents.get(continentSpinner.getSelectedItemPosition()).getReference());
                        nouveauJoueur.setReponseSecrete(reponseSecrete.toString());
                        Toast.makeText(FinalizeCreateAccountActivity.this,nouveauJoueur.toString()+"55555555555555555555555555555",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(FinalizeCreateAccountActivity.this, ConnectionActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        nouveauJoueur.setSexe('F');
                        nouveauJoueur.setReponseSecrete(reponseSecrete.toString());
                        nouveauJoueur.setIdQuestion(lstQuestions.get(secretQSpinner.getSelectedItemPosition()).getIdQuestion());
                        nouveauJoueur.setReference(lstContinents.get(continentSpinner.getSelectedItemPosition()).getReference());
                        Toast.makeText(FinalizeCreateAccountActivity.this,"0000000000000000000000000000000000000",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(FinalizeCreateAccountActivity.this, ConnectionActivity.class);
                        startActivity(intent);
                    }
                }

            }
        });
    }

    private void conversionJSONObjectContinent(JSONArray reponse)
    {
        try {
            for(int i = 0; i < reponse.length(); i++)
            {
                JSONObject jsobj = reponse.getJSONObject(i);
                Continent cont = new Continent();
                cont.setReference(jsobj.getString("reference"));
                cont.setLibelle(jsobj.getString("libelle"));
                lstContinents.add(cont);
            }

            ArrayAdapter<Continent> adapter = new ArrayAdapter<>(FinalizeCreateAccountActivity.this, android.R.layout.simple_list_item_1, lstContinents);

            continentSpinner.setAdapter(adapter);


            Toast.makeText(FinalizeCreateAccountActivity.this, reponse.toString(), Toast.LENGTH_LONG).show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void conversionJSONObjectQuestions(JSONArray reponse)
    {
        try {
            for(int i = 0; i < reponse.length(); i++)
            {
                JSONObject jsobj = reponse.getJSONObject(i);
                QuestionSecrete question = new QuestionSecrete();
                question.setIdQuestion(jsobj.getInt("idQuestion"));
                question.setLibelle(jsobj.getString("libelle"));
                lstQuestions.add(question);
            }

            ArrayAdapter<QuestionSecrete> adapter = new ArrayAdapter<>(FinalizeCreateAccountActivity.this, android.R.layout.simple_list_item_1, lstQuestions);

            secretQSpinner.setAdapter(adapter);


            Toast.makeText(FinalizeCreateAccountActivity.this, reponse.toString(), Toast.LENGTH_LONG).show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
