package com.example.luisflores.eva3_10_servicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Principal extends AppCompatActivity {


    Intent inServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        inServicio = new Intent(this, MiServicio.class);

    }

    public void inicio(View v) {
        startService(inServicio);

    }

    public void detener(View v) {

        stopService(inServicio);
    }
}
