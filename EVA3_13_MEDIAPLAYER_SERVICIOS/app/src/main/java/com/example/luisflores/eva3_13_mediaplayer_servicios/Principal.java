package com.example.luisflores.eva3_13_mediaplayer_servicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Principal extends AppCompatActivity {

    Intent inServicio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        inServicio = new Intent(this, miServicio.class);
        

    }

    public void click1(View v) {
        Toast.makeText(this, "prueba", Toast.LENGTH_SHORT).show();
        startService(inServicio);
        

    }

    public void click2(View v) {
        stopService(inServicio);

    }
}
