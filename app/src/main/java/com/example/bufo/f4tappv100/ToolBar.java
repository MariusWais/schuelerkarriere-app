package com.example.bufo.f4tappv100;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by schue on 09.10.2017.
 */

public class ToolBar extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_layout);

        findViewById(R.id.vitaButton).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToolBar.this,EditVita.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"getToVita",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getToVita(View view){
        Intent intent = new Intent(ToolBar.this,EditVita.class);
        startActivity(intent);
        Toast.makeText(getApplicationContext(),"getToVita",Toast.LENGTH_LONG).show();
    }
    public void getToInfo(View view){
      //  Intent intent = new Intent(ToolBar.this,)
    }
    public void getToProfile(View view){
        Intent intent = new Intent(ToolBar.this, UserRegister.class);
        startActivity(intent);
    }
}
