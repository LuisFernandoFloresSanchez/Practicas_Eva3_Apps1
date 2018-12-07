package com.example.luisflores.eva3_13_mediaplayer_servicios;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class miServicio extends Service {
    MediaPlayer mpReproducir = null;

    public miServicio() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "nueva prueba", Toast.LENGTH_SHORT).show();
        mpReproducir = MediaPlayer.create(getApplicationContext(), R.raw.galway);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mpReproducir != null) {
            mpReproducir.stop();
            mpReproducir.release();
        }
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.wtf("onStart: 2 ", "algo");
        if (mpReproducir != null) {
            mpReproducir.start();
            Log.wtf("onStart: ", "algo");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        throw new UnsupportedOperationException("Not yet implemented");

    }
}
