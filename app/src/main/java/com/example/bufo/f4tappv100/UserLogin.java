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
import android.widget.Toast;

/**
 * Created by Bufo on 08.05.2017.
 */

public class UserLogin extends Activity {

    EditText ET_MAIL, ET_PASS;
    String login_mail, login_pass;
    Button fontButton;
    static  Boolean log_bool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {// Startfunktion
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_log_layout); // Bestimmung des Layouts

        ET_MAIL = (EditText) findViewById(R.id.user_log_mail);// Texteingabefelder
        ET_PASS = (EditText) findViewById(R.id.user_log_pw);

       // fontButton = (Button) findViewById(R.id.button2);

        android.content.SharedPreferences sharedPreferences = getSharedPreferences("login_data",MODE_PRIVATE);
        String shared = sharedPreferences.getString("login_mail",login_mail);
        sharedPreferences.getString("login_pass",login_pass);

      /*  android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.d("SharedPref",sharedPreferences.toString());
        editor.putBoolean("logged_in",log_bool);
        editor.apply();
*/
        Log.d("prefs",shared);

        ET_MAIL.setText(shared);


        ET_MAIL.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (hasFocus)
                {
                        ET_MAIL.setText("");
                }
            }
        });

        Button next = (Button) findViewById(R.id.button2);
        final ProgressBar p = (ProgressBar) findViewById(R.id.progressBar2);
        p.setVisibility(View.INVISIBLE);
       // fontButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize));
        login_mail = ET_MAIL.getText().toString();// Inhalt der Texteingabefelder in String speichern
        login_pass = ET_PASS.getText().toString();

        next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                final Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        login_mail = ET_MAIL.getText().toString();
                        login_pass = ET_PASS.getText().toString();

                        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(login_mail).matches()&& TextUtils.isEmpty(login_mail) || login_pass.length() < 7 &&TextUtils.isEmpty(login_pass)) {
                            ET_MAIL.setError("Geben sie ihre Email Adresse ein");
                            ET_PASS.setError("Das Passwort muss mindestens 7 Zeichen lang sein");

                        } else if(android.util.Patterns.EMAIL_ADDRESS.matcher(login_mail).matches()&&!TextUtils.isEmpty(login_mail) && !TextUtils.isEmpty(login_pass) && login_pass.length() > 6) {
                            p.setVisibility(View.VISIBLE);
                            String method = "register";
                            Log.d(method, "method");
                            UserLoginBackTask userLoginBackTask = new UserLoginBackTask(getApplicationContext()); // Hintergrundaktivität ausführen
                            userLoginBackTask.execute(method, login_mail, login_pass);


                            Intent myIntent = new Intent(getApplicationContext(), SwipeMainActivity.class);
                            startActivityForResult(myIntent, 50000);
                        }}
                }, 500);}
        });
    }


    public void getToUserReg(View view){
        Intent intent = new Intent(UserLogin.this, UserRegister.class);
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

    public void getToVita(View view){
        Toast.makeText(getApplicationContext(),"getToVita",Toast.LENGTH_LONG).show();
        Intent intent = new Intent(UserLogin.this,EditVita.class);
        startActivity(intent);

    }
    }
