package com.example.luisflores.eva3_15_climasreales;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ClimaDetalle extends AppCompatActivity {
    ImageView imgVwClima;
    TextView lblCiudad;
    TextView lblTemperatura;
    TextView lblDescripcion;
    Intent inDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clima_detalle);
        imgVwClima = findViewById(R.id.imgClimadet);
        lblCiudad = findViewById(R.id.lblCiudaddet);
        lblDescripcion = findViewById(R.id.lblDescripciondet);
        lblTemperatura = findViewById(R.id.lblTemperaturadet);
        inDatos = getIntent();

        imgVwClima.setImageResource(inDatos.getIntExtra("IMAGEN",R.drawable.cloudy));
        lblCiudad.setText(inDatos.getStringExtra("CIUDAD"));
        lblDescripcion.setText(inDatos.getStringExtra("DESCRIPCION"));
        lblTemperatura.setText(inDatos.getIntExtra("TEMPERATURA",0)+"°");

    }
}
