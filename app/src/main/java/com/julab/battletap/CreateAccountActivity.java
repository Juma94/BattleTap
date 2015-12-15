package com.julab.battletap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.microedition.khronos.egl.EGLDisplay;

import Model.Joueur;

public class CreateAccountActivity extends AppCompatActivity
{
    private Button btnNext;
    private EditText pseudo;
    private EditText email;
    private EditText motDePasse;
    private EditText motDePasseConfirmation;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        pseudo = (EditText)findViewById(R.id.activity_create_account_pseudo_id);
        email = (EditText)findViewById(R.id.activity_create_account_editText_email_id);
        motDePasse = (EditText)findViewById(R.id.activity_create_account_pwd_editText_id);
        motDePasseConfirmation = (EditText)findViewById(R.id.activity_create_account_pwdConfirm_editText_id);
        btnNext = (Button)findViewById(R.id.activity_create_account_btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(pseudo.length() <3 || pseudo.length()>30)
                    {
                        Toast.makeText(CreateAccountActivity.this,"Le pseudo doit contenir minimum 3 caractères et maximum 30 caractères ", Toast.LENGTH_SHORT).show();
                        // Tester si le pseudo existe deja
                    }
                    else
                    {
                        if(!emailValid(email.getEditableText().toString()))
                        {
                            Toast.makeText(CreateAccountActivity.this,"L'email entré est incorrect", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            if(motDePasse.length()<8 || motDePasse.length()>50)
                            {
                                Toast.makeText(CreateAccountActivity.this,"Le mot de passe doit une longueur de minimum 8 caractères et maximum 50 caractères", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                if(!motDePasseConfirmation.getText().toString().equals(motDePasse.getText().toString()))
                                {
                                    Toast.makeText(CreateAccountActivity.this,"Les mots de passe ne correspondent pas", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    Joueur nouveauJoeur = new Joueur();
                                    nouveauJoeur.setPseudo(pseudo.toString());
                                    nouveauJoeur.setMotDePasse(motDePasse.toString());
                                    //Mot de Passe a crypter
                                    nouveauJoeur.setEmail(email.toString());

                                    Intent intent = new Intent(CreateAccountActivity.this, FinalizeCreateAccountActivity.class);
                                    intent.putExtra("joueurEnCreation", nouveauJoeur);
                                    startActivity(intent);
                                }

                            }

                        }

                    }




            }
        });
    }

    public static boolean emailValid(String email) {
        boolean estValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            estValid = true;
        }
        return estValid;
    }
}
