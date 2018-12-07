package com.example.luisflores.eva3_11_broadcastreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MiServicio extends Service {
    Thread tHilo;
    Intent inMensaje;

    public MiServicio() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        inMensaje = new Intent("MI_SERVICIO");
        inMensaje.putExtra("MENSAJE","onCreate");
        sendBroadcast(inMensaje);
    }

    @Override
    public void onStart(final Intent intent, int startId) {
        super.onStart(intent, startId);
        Runnable rnHilo = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {

                        Thread.sleep(1000);
                        Log.wtf("CICLO INFINITO PERRO", "run: ");
                    inMensaje = new Intent("MI_SERVICIO");
                    inMensaje.putExtra("MENSAJE","OnStart");
                    sendBroadcast(inMensaje);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        tHilo = new Thread(rnHilo);
        tHilo.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        inMensaje = new Intent("MI_SERVICIO");
        inMensaje.putExtra("MENSAJE","OnDestroy");
        sendBroadcast(inMensaje);

        tHilo.interrupt();
    }
}
