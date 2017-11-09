package com.example.bufo.f4tappv100;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import static com.example.bufo.f4tappv100.UserLogin.log_bool;

/**
 * Created by Bufo on 05.05.2017.
 */

// Hintergrundtasks für Login

public class ChangePassBackTask extends AsyncTask<String,Void,String> { // Hintergrungd Task für die Afrage von Daten

    // Deklarierung\\

    Context ctx;


    ChangePassBackTask(Context ctx){
        this.ctx = ctx; //Kontext
    }

    @Override
    protected void onPreExecute() { // Vor Ausführung starten

    }
    @Override
    protected String doInBackground(String... params) { // Hintergund Operationen

        //URL-Link zum Server(localhost)\\

        String login_url = "http://10.0.3.2/f4tapp/change_pw.php";

        //!!!!! Link zum Server(localhost) für echte Geräte (Hier muss die eigene IPv4 Adresse des Computers verwendet werden. Diese findet man in CMD unter ipconfig)!!!!!
        //String reg_url = "http://192.168.178.83/app/register.php";
        //String login_url = "http://192.168.X.XX/app/login.php";


        String method = params[0];
        if(method.equals("change_pw")){ // Wenn Login vorliegt
            String login_mail = params[1];  // Zuweisung von Parametern
            String login_pass = params[2];
            String login_new = params[3];

            try {
                URL url = new URL(login_url); //Url Objekt generieren
                //HttpUrl Verbindung herstellen\\
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream(); // Ausgabestream

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8")); // in datei schreiben

                // Datenbankinformationen werden kodiert und in String Variable gespeichert
                String data = URLEncoder.encode("login_mail","UTF-8")+"="+URLEncoder.encode(login_mail,"UTF-8")+"&"+URLEncoder.encode("login_pass","UTF-8")
                              +"="+URLEncoder.encode(login_pass,"UTF-8")+"&"+URLEncoder.encode("login_new","UTF-8")+"="+URLEncoder.encode(login_new,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream(); // Eingabestream

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1")); // aus datei lesen
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null){
                    response+= line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return response; // Rückmeldung

            } catch (MalformedURLException e){ // catch fehlermeldungen
                e.printStackTrace();

            }catch (IOException e){
                e.printStackTrace();
            }
            return login_url;
        }
        return null;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {   // Nach Ausführung starten

        Log.d("result",result);
        if (result != null && result.equals("worked")) {  //Wenn Registrierung erfolgreich ist gibt es eine Meldung

            Intent myIntent = new Intent(ctx, UserLogin.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            ctx.startActivity(myIntent);
            Toast toast = new Toast(ctx);
            toast.setDuration(Toast.LENGTH_LONG);
            log_bool = true;
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.toast_login, null);
            toast.setView(view);
            toast.show();

        }else{
            Toast toast = new Toast(ctx);
            toast.setDuration(Toast.LENGTH_LONG);

            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.toast_login_failed, null);
            toast.setView(view);
            toast.show();

            Intent myIntent = new Intent(ctx, ChangePW.class);
            myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            ctx.startActivity(myIntent);
        }

    }
}