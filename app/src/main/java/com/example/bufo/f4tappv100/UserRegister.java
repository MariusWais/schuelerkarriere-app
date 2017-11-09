package com.example.bufo.f4tappv100;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by Bufo on 08.05.2017.
 */

public class UserRegister extends Activity {

    //Deklarierung
    EditText ET_USER_MAIL, ET_USER_NAME, ET_USER_PASS;
    String user_mail, user_name, user_pass;
    CharSequence test;
    static String strMail;
    static String strUserName;
    static String strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_reg_layout);
        ET_USER_MAIL = (EditText) findViewById((R.id.user_reg_mail));
        ET_USER_NAME = (EditText) findViewById((R.id.user_reg_username));
        ET_USER_PASS = (EditText) findViewById((R.id.user_reg_pw));




        final Button next = (Button) findViewById(R.id.button);
        final ProgressBar p = (ProgressBar) findViewById(R.id.pb);
        p.setVisibility(View.INVISIBLE);

      /*if(!ET_USER_MAIL.getText().toString().isEmpty()){
            next.setClickable(false);
            return;
        }else{
            next.setClickable(true);
            //return;
        }*/

        next.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            strMail = ET_USER_MAIL.getText().toString();
                            strUserName = ET_USER_NAME.getText().toString();
                            strPass = ET_USER_PASS.getText().toString();

                            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(strMail).matches()&& TextUtils.isEmpty(strPass) || strPass.length() < 7 &&TextUtils.isEmpty(strUserName)) {
                                ET_USER_MAIL.setError("Geben sie ihre Email Adresse ein");
                                ET_USER_PASS.setError("Das Passwort muss mindestens 7 Zeichen lang sein");
                                ET_USER_NAME.setError("Geben Sie ihren Benutzernamen ein!");

                            } else if(android.util.Patterns.EMAIL_ADDRESS.matcher(strMail).matches()&&!TextUtils.isEmpty(strUserName) && !TextUtils.isEmpty(strMail) && !TextUtils.isEmpty(strPass)) {
                                p.setVisibility(View.VISIBLE);
                                String method = "register";
                                Log.d(method, "method");
                                UserRegBackTask userRegBackTask = new UserRegBackTask(getApplicationContext()); // Hintergrundaktivität ausführen
                                userRegBackTask.execute(method, strMail, strUserName, strPass);

                                android.content.SharedPreferences sharedPreferences = getSharedPreferences("login_data",MODE_PRIVATE);
                                android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
                                Log.d("SharedPref",sharedPreferences.toString());
                                editor.putString("login_mail",strMail);
                                editor.putString("login_pass",strPass);
                                editor.apply();


                                Intent myIntent = new Intent(getApplicationContext(), UserLogin.class);
                                startActivityForResult(myIntent, 50000);
                            }}
                    }, 500);}
            });
        }

    public void getToUserLogin(View view){
        Intent intent = new Intent(UserRegister.this, UserLogin.class);
        startActivity(intent);
    }

    }




