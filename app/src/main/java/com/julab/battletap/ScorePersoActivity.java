package com.julab.battletap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

import DataAccess.Singleton;
import Model.Score_solo;

public class ScorePersoActivity extends AppCompatActivity {
    private TextView titreScoreSolo;
    private ListView scoreList;
    private ArrayList<Score_solo> lstScores = new ArrayList<Score_solo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_perso);

        titreScoreSolo = (TextView)findViewById(R.id.score_perso_id);

        scoreList = (ListView) findViewById(R.id.listView_score_solo);
        scoreList.setAdapter(new ScorePersoListAdapter(getApplicationContext(), R.layout.expand_list_perso_scores));


        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        JsonArrayRequest requestObj = new JsonArrayRequest(Request.Method.GET,"http://battletapapi.azurewebsites.net/api/score_solo", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                conversionJSONObject(response);

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(ScorePersoActivity.this, "Erreur Reponse : " + error.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        requestObj.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(requestObj);



    }
    private void conversionJSONObject(JSONArray reponse)
    {
        try {
            for(int i = 0; i < reponse.length(); i++)
            {
                JSONObject jsobj = reponse.getJSONObject(i);
                Score_solo score = new Score_solo();

                score.setIdScore(jsobj.getInt("idScore"));
                score.setDifferenceIncrementation(jsobj.getInt("differenceIncrementation"));
                score.setTemps(jsobj.getString("temps"));
                score.setIdJoueur(jsobj.getInt("idJoueur"));
                score.setNbNombresAtteints(jsobj.getInt("nbNombresAtteints"));
                lstScores.add(score);
            }

            ArrayAdapter<Score_solo> adapter = new ArrayAdapter<>(ScorePersoActivity.this, android.R.layout.simple_list_item_1, lstScores);
            scoreList.setAdapter(adapter);


            Toast.makeText(ScorePersoActivity.this, reponse.toString(), Toast.LENGTH_LONG).show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
