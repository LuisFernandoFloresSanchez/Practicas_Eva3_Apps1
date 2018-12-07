package com.example.luisflores.eva3_4_banner;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class Principal extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {
    Handler hManejador = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);




                // Recibir una imagen y cambiar la imagen
                switch (iImagen) {
                    case 1:
                        imvBanner.setImageResource(R.drawable.cloudy);
                        iImagen++;
                        break;
                    case 2:
                        imvBanner.setImageResource(R.drawable.rainy);
                        iImagen++;
                        break;
                    case 3:
                        imvBanner.setImageResource(R.drawable.sunny);
                        iImagen++;
                        break;
                    default:
                        imvBanner.setImageResource(R.drawable.light_rain);
                        iImagen = 1;
                        break;


                }

        }
    };
    ImageView imvBanner;
    SeekBar seekBar;
    int iProgreso;
    int iBarra;
    int iImagen = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        imvBanner = findViewById(R.id.imvBaaner);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        iProgreso = 1900;

        Banner b = new Banner();
        b.start();

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        iBarra = progress;
        switch (iBarra) {
            case 1:
                iProgreso = 1900;
                break;
            case 2:
                iProgreso = 1600;
                break;
            case 3:
                iProgreso = 1500;
                break;
            case 4:
                iProgreso = 1300;
                break;
            case 5:
                iProgreso = 1100;
                break;
            case 6:
                iProgreso = 900;
                break;
            case 7:
                iProgreso = 700;
                break;
            case 8:
                iProgreso = 500;
                break;
            case 9:
                iProgreso = 300;
                break;
            case 10:
                iProgreso = 1000;
                break;
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    class Banner extends Thread {
        @Override
        public void run() {
            super.run();


            while (true) {

                try {
                    Thread.sleep(iProgreso);

                    int i = 0;
                    Message msg = hManejador.obtainMessage(1, i);
                    hManejador.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
