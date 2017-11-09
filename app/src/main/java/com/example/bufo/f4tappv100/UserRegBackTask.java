package com.example.bufo.f4tappv100;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Bufo on 05.05.2017.
 */

// Hintergrundtasks für Login und Registration

public class UserRegBackTask extends AsyncTask<String,Void,String> { // Hintergrungd Task für die Afrage von Daten

    // Deklarierung\\

    Context ctx;


    UserRegBackTask(Context ctx){
        this.ctx = ctx; //Kontext
    }

    @Override
    protected void onPreExecute() { // Vor Ausführung starten

    }
    @Override
    protected String doInBackground(String... params) { // Hintergund Operationen

        //URL-Link zum Server(localhost)\\
        String reg_url = "https://www.schuelerkarriere.de/app/user_register.php";

        //!!!!! Link zum Server(localhost) für echte Geräte (Hier muss die eigene IPv4 Adresse des Computers verwendet werden. Diese findet man in CMD unter ipconfig)!!!!!
        //String reg_url = "http://192.168.178.83/app/register.php";
        //String login_url = "http://192.168.X.XX/app/login.php";



        String method = params[0];
        if(method.equals("register")) { // Wenn Registrierung vorliegt
            String user_mail = params[1];    //Zuordnung von Parametern
            String user_name = params[2];
            String user_pass = params[3];
            try {
                URL url = new URL(reg_url); // Url Objekt generieren
                //Toast.makeText(ctx,"test2",Toast.LENGTH_LONG).show();
                //Log.d(test,"test");

                // HttpUrl Verbindung erstellen\\
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                OutputStream OS = httpURLConnection.getOutputStream();// Ausgabestream
                // In Datei schreiben mithilfe von bufferedwriter
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
                String data = URLEncoder.encode("user_mail","UTF-8") + "="+URLEncoder.encode(user_mail,"UTF-8")+"&"+
                        URLEncoder.encode("user_name","UTF-8") + "="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8") + "="+URLEncoder.encode(user_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();//Eingabestream
                IS.close();

                return "Registration success"; // Rückmeldung wenn Registrierung erfolgreich war


                // catch clauses /  fehlermeldungen \\
            }catch(MalformedURLException e){
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }

            return reg_url;

        }
        return null;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onPostExecute(String result) {   // Nach Ausführung starten

        if (result != null && result.equals("Registration success")) {  //Wenn Registrierung erfolgreich ist gibt es eine Meldung

            Toast toast = new Toast(ctx);
            toast.setDuration(Toast.LENGTH_LONG);

            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.toast_regist, null);
            toast.setView(view);
            toast.show();

        }
    }
}