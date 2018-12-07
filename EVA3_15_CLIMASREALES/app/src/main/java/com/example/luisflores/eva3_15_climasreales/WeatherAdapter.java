package com.example.luisflores.eva3_15_climasreales;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherAdapter extends ArrayAdapter<Clima> {
    Context cContexto;
    int iLayout;
    Clima[] aCiudadesClima;

    public WeatherAdapter(@NonNull Context context, int resource, @NonNull Clima[] objects) {
        super(context, resource, objects);
        cContexto = context;
        iLayout = resource;
        aCiudadesClima = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ImageView imgVwClima;
        TextView lblCiudad;
        TextView lblTemperatura;
        TextView lblDescripcion;

        View fila = convertView;
        if(fila == null){ //Si es nulo no existe nuestra fila, hay que crearla
            LayoutInflater liVista = ((Activity)cContexto).getLayoutInflater();
            fila = liVista.inflate(iLayout,parent,false);

        }
        // [[[[[[[[[[[[[[[[[[[[[[Vincular los widgets ]]]]]]]]]]]]]]]]]]]]]]]]]]]]
        imgVwClima = fila.findViewById(R.id.imvImagen);
        lblCiudad = fila.findViewById(R.id.lblCiudad);
        lblDescripcion = fila.findViewById(R.id.lblDescripcion);
        lblTemperatura = fila.findViewById(R.id.lblTemperatura);

        //{{{{{{{{{{{{{{{{{{{{{{{{{{{{ Llenar datos }}}}}}}}}}}}}}}}}}}}}}}}}}}}}}
        Clima cActual = aCiudadesClima[position];
        imgVwClima.setImageResource(cActual.iImagen);
        lblCiudad.setText(cActual.sNombreCiudad);
        lblDescripcion.setText(cActual.sDescripcion);
        lblTemperatura.setText(cActual.iTemperatura + "°");
        return fila;
    }


}