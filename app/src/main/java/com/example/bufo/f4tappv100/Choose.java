package com.example.bufo.f4tappv100;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/**
 * Created by Bufo on 08.05.2017.
 */

public class Choose extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_layout);

    }

    public void getToUserReg(View view){
        Intent intent = new Intent(Choose.this, UserRegister.class);
        startActivity(intent);
    }
    public void getToUserLogin(View view){
        Intent intent = new Intent(Choose.this, UserLogin.class);
                startActivity(intent);
    }


    }


