package com.example.luisflores.eva3_6_handler_post;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    TextView lblNumeros;
    Handler hManejador = new Handler();
    int i;
    //RUNNABLE 1 --> TRABAJO PESADO EN SEGUNDO PLANO
    Runnable rSegundoPlano = new Runnable() {
        @Override
        public void run() {
            while (true) {
                hManejador.post(rRunnableUI);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    //RUNNABLE 2 --> TRABAJO LIGERO Y TRABAJO EN LA UI
    Runnable rRunnableUI = new Runnable() {
        @Override
        public void run() {
            lblNumeros.append(i + " - ");
            i++;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        lblNumeros = findViewById(R.id.lblNumeros);

        Thread tHilo = new Thread(rSegundoPlano);
        tHilo.start();
    }
}
