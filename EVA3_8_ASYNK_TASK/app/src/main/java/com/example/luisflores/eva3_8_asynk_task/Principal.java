package com.example.luisflores.eva3_8_asynk_task;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    TextView txtDatos;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        txtDatos = findViewById(R.id.txtDatos);

        ClaseAsincrona cTareaAsincrona = new ClaseAsincrona();
        cTareaAsincrona.execute(100);


    }


    class ClaseAsincrona extends AsyncTask<Integer, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtDatos.setText(" Iniciando la tarea asincrona: ");
        }

        // UNICO MÉTODO QUE NO SE COMUNICA CON EL
        //INTERFAZ GRÁFICA
        @Override
        protected Void doInBackground(Integer... integers) {
             i = integers[0];

            while (true) {
                try {
                    Thread.sleep(1000);
                    publishProgress(i++);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            txtDatos.append(i + " - ");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
