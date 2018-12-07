package com.example.luisflores.eva3_14_clima;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.AppLaunchChecker;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Principal extends AppCompatActivity {
    TextView lblInfo;
    final int iInternet = 100;
    boolean bBande = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        lblInfo = findViewById(R.id.lblInfo);
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            // SOLICITAR PERMISO
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.INTERNET},
                    iInternet);
        } else {
            bBande = true;
        }
    }

    public void botonConectar(View v) {
        //inciar clase asincrona
        if (bBande == true) {
            new Conexion().execute();
        } else {
            Toast.makeText(this, "NO TIENES PERMISO PA LLAMAR PRRO", Toast.LENGTH_SHORT).show();

        }


    }

    class Conexion extends AsyncTask<Void, Void, String> {


        final String sLonk = "http://api.openweathermap.org/data/2.5/find?lat=28.6353&lon=-106.089&cnt=20&appid=3134acf616d86a4a30f6d88722edadc6";

        @Override
        protected String doInBackground(Void... voids) {
            String sResu = "";
            try {
                URL url = new URL(sLonk);
                HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
               // Toast.makeText(getApplicationContext(), "hi ", Toast.LENGTH_SHORT).show();
                Log.wtf("NO ENTRA", "NO ESTA ENTRANDO" );
                if (httpCon.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader brDatos = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                    sResu = brDatos.readLine();
                }
            } catch (Exception e) {

            }


            return sResu;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //Lectura de los datos
            JSONArray jCiudades = null;
            if (!s.equals("")) {
                try {
                    JSONObject jsDatos = new JSONObject(s);
                    jCiudades = jsDatos.getJSONArray("list");
            for (int i = 0; i < jCiudades.length(); i++){

                JSONObject jCiudad = jCiudades.getJSONObject(i);
                lblInfo.append("Ciudad: " + jCiudad.getString("name"));
            }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "no jala", Toast.LENGTH_SHORT).show();


            }

        }
    }
}
