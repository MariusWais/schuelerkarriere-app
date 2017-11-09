package com.example.bufo.f4tappv100;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
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

public class VitaBackgroundTask extends AsyncTask<String,Void,String> {//Hintergrungd Task für die Afrage von Daten
    //Deklarierung
    AlertDialog alertDialog;
    Context ctx;

    VitaBackgroundTask(Context ctx) {
        this.ctx = ctx;
    } // Kontext

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(ctx).create();
    } // Ausführen vor Beginn

    @Override
    protected String doInBackground(String... params) { // Hintergrundaktivität

        // Link zum Server(localhost) nur bei Virtual Devices
        String vita_url = "https://www.schuelerkarriere.de/app/add_vita.php";
        //String vita_url = "http://192.168.178.105/f4tapp/add_vita.php";

        //!!!!! Link zum Server(localhost) für echte Geräte (Hier muss die eigene IPv4 Adresse des Computers verwendet werden. Diese findet man in CMD unter ipconfig)!!!!!
        //String post_url = "http://192.168.X.XX/app/register.php";

        String method = params[0];

        if (method.equals("vita")) {    // Zuweisung der Parameter

            String name = params[1];
            String age = params[2];
            String language = params[3];
            String graduation = params[4];
            String phone = params[5];
            String mail = params[6];
            String internships = params[7];
            String itknowledge = params[8];
            String other = params[9];

            try {

                URL url = new URL(vita_url);    // URL-Objekt
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(); // URL Verbindung erstellen
                httpURLConnection.setRequestMethod("POST"); // POST Methode
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream(); // Ausgabestream
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8")); // in DB schreiben
                String data =
                                URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8")
                                + "&" +
                                URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8")
                                + "&" +
                                URLEncoder.encode("language", "UTF-8") + "=" + URLEncoder.encode(language, "UTF-8")
                                + "&" +
                                URLEncoder.encode("graduation", "UTF-8") + "=" + URLEncoder.encode(graduation, "UTF-8")
                                + "&" +
                                URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(phone, "UTF-8")
                                + "&" +
                                URLEncoder.encode("mail", "UTF-8") + "=" + URLEncoder.encode(mail, "UTF-8")
                                + "&" +
                                URLEncoder.encode("internships", "UTF-8") + "=" + URLEncoder.encode(internships, "UTF-8")
                                + "&" +
                                URLEncoder.encode("itknowledge", "UTF-8") + "=" + URLEncoder.encode(itknowledge, "UTF-8")
                                + "&" +
                                URLEncoder.encode("other", "UTF-8") + "=" + URLEncoder.encode(other, "UTF-8");



                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream(); // Eingabestream
                IS.close();
                return "Vita updated"; // Post erfolgreich

                //catch clauses/fehlermeldungen

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }
        return vita_url;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) { // Nach Ausführung
        if (result.equals("Vita updated")) {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show(); // Meldung
            Log.d("vita updated",result);

        } else {
            alertDialog.setMessage(result);
            alertDialog.show();
        }
    }
}