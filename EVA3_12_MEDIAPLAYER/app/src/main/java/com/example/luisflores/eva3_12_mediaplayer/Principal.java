package com.example.luisflores.eva3_12_mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Principal extends AppCompatActivity {
    MediaPlayer mpReproducir = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        mpReproducir = MediaPlayer.create(this, R.raw.galway);

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mpReproducir != null) {
            mpReproducir.start();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mpReproducir != null) {
            mpReproducir.stop();
            mpReproducir.release();
        }

    }
}
