package com.example.bufo.f4tappv100;

/**
 * Created by schue on 04.10.2017.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Bufo on 05.05.2017.
 */

public class EditVita extends Activity{
    EditText ET_NAME,ET_AGE,ET_MAIL,ET_PHONE,ET_GRAD,ET_EXP,ET_INT,ET_OTH,ET_LANG,ET_IT;
    String name,age,phone,mail,graduation,experience,internship,others,language,it;
    static  Boolean vita_bool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){ // Beim Start ausführen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_vita_layout); // Bestimmung des Layouts

        //Initialiserung der Textfelder und Zuweisung per ID\\
        ET_NAME = (EditText)findViewById((R.id.add_name));
        ET_AGE = (EditText)findViewById((R.id.add_age));
        ET_PHONE = (EditText)findViewById((R.id.add_phone));
        ET_MAIL = (EditText)findViewById((R.id.add_mail));
        // ET_ORIG = (EditText)findViewById((R.id.add_orig));
        ET_LANG = (EditText)findViewById((R.id.add_lang));
        ET_GRAD = (EditText)findViewById((R.id.add_grad));
        ET_EXP = (EditText)findViewById((R.id.add_exp));
        ET_INT = (EditText)findViewById((R.id.add_int));
        ET_LANG = (EditText)findViewById((R.id.add_lang));
        ET_IT = (EditText)findViewById((R.id.add_it));
        ET_OTH = (EditText)findViewById((R.id.add_oth));

        android.content.SharedPreferences sharedPreferences = getSharedPreferences("vita_data",MODE_PRIVATE);
        String shared = sharedPreferences.getString("vita_name",name);
        sharedPreferences.getString("vita_age",age);
        sharedPreferences.getString("vita_phone",phone);
        sharedPreferences.getString("vita_mail",mail);
        sharedPreferences.getString("vita_graduation",graduation);
        sharedPreferences.getString("vita_experience",experience);
        sharedPreferences.getString("vita_internship",internship);
        sharedPreferences.getString("vita_language",language);
        sharedPreferences.getString("vita_it",it);
        sharedPreferences.getString("vita_others",others);



        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        Log.d("SharedPref",sharedPreferences.toString());
        editor.putBoolean("vita",vita_bool);
        editor.apply();
    }

    public void addVita(View view){
        // Inhalt der Textfelder in Variable übergeben
        name = ET_NAME.getText().toString();
        age = ET_AGE.getText().toString();
        //origin = ET_ORIG.getText().toString();
        phone = ET_PHONE.getText().toString();
        mail = ET_MAIL.getText().toString();
        graduation = ET_GRAD.getText().toString();
        experience = ET_EXP.getText().toString();
        internship = ET_INT.getText().toString();
        language = ET_LANG.getText().toString();
        it = ET_IT.getText().toString();
        others = ET_OTH.getText().toString();


        if(!name.isEmpty()&&!age.isEmpty()&&!internship.isEmpty()&&!language.isEmpty()&&!graduation.isEmpty()&&!experience.isEmpty()&&!phone.isEmpty()&&!mail.isEmpty()&&!it.isEmpty()&&!others.isEmpty()) {
            String method = "vita"; // Definierung des Prozesses
            Log.d("vita updated","vita");
            VitaBackgroundTask vitabackgroundTask = new VitaBackgroundTask((this)); //  Objektgenerierung des Tasks
            vitabackgroundTask.execute(method, name, age, phone, mail, graduation, experience,internship,language,it,others);// origin,language,graduation,experience,phone,mail);    // execute anweisung des Backgroundtasks
            android.content.SharedPreferences sharedPreferences = getSharedPreferences("login_data",MODE_PRIVATE);
            android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
            Log.d("SharedPref",sharedPreferences.toString());
            editor.putString("vita_name",name);
            editor.putString("vita_age",age);
            editor.putString("vita_phone",phone);
            editor.putString("vita_mail",mail);
            editor.putString("vita_graduation",graduation);
            editor.putString("vita_experience",experience);
            editor.putString("vita_internship",internship);
            editor.putString("vita_language",language);
            editor.putString("vita_it",it);
            editor.putString("vita_others",others);

            editor.apply();
            finish();   // anweisung zum abschluss/beedigung
            Intent intent = new Intent(EditVita.this, SwipeMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}