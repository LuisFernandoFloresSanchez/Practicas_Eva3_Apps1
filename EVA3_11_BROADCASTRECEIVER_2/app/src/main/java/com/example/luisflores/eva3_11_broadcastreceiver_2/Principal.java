package com.example.luisflores.eva3_11_broadcastreceiver_2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Principal extends AppCompatActivity {

    TextView lblEtiqueta;
    BroadcastReceiver miBroadCast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        lblEtiqueta = findViewById(R.id.lblEtiqueta);

        miBroadCast = new MiBroadCast();
        IntentFilter ifFiltrarServicios = new IntentFilter("MI_SERVICIO");
        registerReceiver(miBroadCast,ifFiltrarServicios);

    }


    class MiBroadCast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //AQUI VAMOS A LEER EL MENSAJE
            String datos = intent.getStringExtra("MENSAJE");
            lblEtiqueta.append(datos);
        }
    }

}
