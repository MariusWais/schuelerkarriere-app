package com.example.bufo.f4tappv100;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

/**
 * Created by Bufo on 08.05.2017.
 */

public class ChangePW extends Activity {

    EditText ET_MAIL, ET_PASS, ET_NEW;
    String login_mail, login_pass, login_new;
    Button fontButton;
    static  Boolean log_bool = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {// Startfunktion
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_pw_layout);// Bestimmung des Layouts

        ET_MAIL = (EditText) findViewById(R.id.cp_log_mail);// Texteingabefelder
        ET_PASS = (EditText) findViewById(R.id.cp_log_pw);
        ET_NEW = (EditText) findViewById(R.id.cp_log_new);

        fontButton = (Button) findViewById(R.id.buttonCp);



        Button next = (Button) findViewById(R.id.buttonCp);
        final ProgressBar p = (ProgressBar) findViewById(R.id.progressBarCp);
        p.setVisibility(View.INVISIBLE);
        fontButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
        login_mail = ET_MAIL.getText().toString();// Inhalt der Texteingabefelder in String speichern
        login_pass = ET_PASS.getText().toString();
        login_new = ET_NEW.getText().toString();

        next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        login_mail = ET_MAIL.getText().toString();// Inhalt der Texteingabefelder in String speichern
                        login_pass = ET_PASS.getText().toString();
                        login_new = ET_NEW.getText().toString();

                        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(login_mail).matches()&& TextUtils.isEmpty(login_mail)||login_pass.length() < 7 &&TextUtils.isEmpty(login_pass) || login_new==login_pass) {
                            ET_MAIL.setError("Geben sie ihre Email Adresse ein");

                        }else if(login_pass.length() < 7 &&TextUtils.isEmpty(login_pass)) {
                            ET_PASS.setError("Das Passwort muss mindestens 7 Zeichen lang sein");

                        }else if(login_new==login_pass){
                            ET_NEW.setError("Bitte geben sie ein neues Passwort ein");

                        }else{//(android.util.Patterns.EMAIL_ADDRESS.matcher(login_mail).matches()&&!TextUtils.isEmpty(login_mail) && !TextUtils.isEmpty(login_pass) && login_pass.length() > 6) {
                            p.setVisibility(View.VISIBLE);
                            String method = "change_pw";
                            Log.d(method, "method");
                            ChangePassBackTask changePassBackTask = new ChangePassBackTask(getApplicationContext()); // Hintergrundaktivität ausführen
                            changePassBackTask.execute(method, login_mail, login_pass, login_new);


                            Intent myIntent = new Intent(getApplicationContext(), UserLogin.class);
                            startActivityForResult(myIntent, 50000);
                        }}
                }, 500);}
        });
    }


    public void getToUserReg(View view){
        Intent intent = new Intent(ChangePW.this, UserRegister.class);
        startActivity(intent);
    }

    public void facebook(View view){
        Uri uri = Uri.parse("https://www.facebook.com/schuelerkarriere/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void twitter(View view){
        Uri uri = Uri.parse("https://twitter.com/schulerkarriere");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void instagram(View view){
        Uri uri = Uri.parse("https://www.instagram.com/schuelerkarriere/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
