package com.example.luisflores.eva3_15_climasreales;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Principal extends AppCompatActivity implements ListView.OnItemClickListener {


    ListView listLista;
    Intent inDetalle;
    Clima[] cClim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Log.wtf("ON CRATE ANTES", "" );

        listLista = findViewById(R.id.listLista);
        inDetalle = new Intent(Principal.this, ClimaDetalle.class);
        listLista.setOnItemClickListener(this);
        new Conexion().execute();
        Log.wtf("ON CRATE", "" );
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int iImagen = cClim[position].iImagen;
        inDetalle.putExtra("IMAGEN", iImagen);

        String sCiudad = cClim[position].sNombreCiudad;
        inDetalle.putExtra("CIUDAD", sCiudad);
        int iTemp = cClim[position].iTemperatura;
        inDetalle.putExtra("TEMPERATURA", iTemp);
        String sDesc = cClim[position].sDescripcion;
        inDetalle.putExtra("DESCRIPCION", sDesc);
        startActivity(inDetalle);
    }

    public int convTempACentigrados(String sVal) {

        double tempRecivida = Double.parseDouble(sVal);
        double centi = (tempRecivida - 32) / 1.8000;
        centi = Math.round(centi);
        int i = (int) centi;

        return i;

    }

    public void meterAdapter() {
        //asignamos el adaptador
        listLista.setAdapter(new WeatherAdapter(this, R.layout.layout_clima, cClim));

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
                if (httpCon.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    BufferedReader brDatos = new BufferedReader(new InputStreamReader(httpCon.getInputStream()));
                    sResu = brDatos.readLine();
                }
            } catch (Exception e) {

            }


            return sResu;
        }




        public int buscadorDeImagen(int idWeather) {

            int iVal = R.drawable.ic_launcher_background;

            if (idWeather == 800) {
                iVal = R.drawable.sunny;
            } else {

                if (idWeather >= 200 && idWeather < 300) {
                    iVal = R.drawable.thunderstorm;

                } else if (idWeather >= 300 && idWeather < 500) {
                    iVal = R.drawable.light_rain;

                } else if (idWeather >= 500 && idWeather < 600) {
                    iVal = R.drawable.rainy;

                } else if (idWeather >= 600 && idWeather < 700) {
                    iVal = R.drawable.snow;

                } else if (idWeather >= 700 && idWeather < 800) {
                    iVal = R.drawable.atmospher;

                } else if (idWeather > 800) {
                    iVal = R.drawable.cloudy;

                }

            }

            return iVal;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.wtf("ENTRANDO", "" );

            JSONArray jsonCiudades = null;
            if (!s.equals("")) {
                try {

                    JSONObject jsDatos = new JSONObject(s);
                    jsonCiudades = jsDatos.getJSONArray("list");

                    cClim = new Clima[jsonCiudades.length() - 1];

                    for (int i = 0; i < jsonCiudades.length(); i++) {

                        JSONObject jsonCiudad = jsonCiudades.getJSONObject(i);
                        JSONObject jsonMain = jsonCiudad.getJSONObject("jsonMain");
                        JSONArray jsonArray = jsonCiudad.getJSONArray("weather");
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        String temp = String.valueOf(jsonMain.getDouble("temp"));
                        String descripcion = jsonObject.getString("description");
                        String ciudad = jsonCiudad.getString("name");

                        int idWeather = jsonObject.getInt("id");
                        Log.wtf("idWether = " + idWeather, "" );

                        int imagen = buscadorDeImagen(idWeather);
                        Log.wtf("imagen = " + idWeather, "" );
                        Toast.makeText(Principal.this, "temp  = " + temp, Toast.LENGTH_SHORT).show();

                        int tempCentigrados = convTempACentigrados(temp);
                        Log.wtf("tempCentigrados = " + idWeather, "" );

                        //guardamos jsonObjects de tipo Clima en el jsonObject llamado clima
                        cClim[i] = new Clima(ciudad, tempCentigrados, descripcion, imagen);



                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    Log.wtf("ora prro", "" );

                }
            } else {
                Log.wtf("Nel", "no jala");


            }
            meterAdapter();
        }

    }
}

