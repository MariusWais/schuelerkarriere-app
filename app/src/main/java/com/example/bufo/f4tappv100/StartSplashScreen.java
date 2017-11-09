package com.example.bufo.f4tappv100;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.example.bufo.f4tappv100.UserLogin.log_bool;


/**
 * Created by Bufo on 08.05.2017.
 */

public class StartSplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_layout);
        final String url = "https://www.schuelerkarriere.de/app/jsonRev.php";

        new Thread() {
            @Override
            public void run() {
                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpResponse response = httpclient.execute(new HttpGet(url));
                    StatusLine statusLine = response.getStatusLine();
                    if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        try {
                            response.getEntity().writeTo(out);
                            out.close();
                        } catch (IOException e) {
                        }
                        String responseString = out.toString();

                    } else {

                        try {
                            response.getEntity().getContent().close();
                            throw new IOException(statusLine.getReasonPhrase());
                        } catch (IOException e) {
                        }
                    }
                }
                catch (ClientProtocolException e)
                {

                }
                catch (IOException e)
                {

                }
            }
        }.start();


        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                android.content.SharedPreferences sharedPreferences = getSharedPreferences("login_data",MODE_PRIVATE);
                Boolean logged = sharedPreferences.getBoolean("logged_in",log_bool);


                if(logged==false){
                    Intent i = new Intent(StartSplashScreen.this, SwipeMainActivity.class);
                    startActivity(i);
                    finish();
                }else{
                    Intent i = new Intent(StartSplashScreen.this, SwipeMainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        }, 50);
    }
}
