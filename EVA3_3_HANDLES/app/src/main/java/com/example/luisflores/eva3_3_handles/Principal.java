package com.example.luisflores.eva3_3_handles;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Principal extends AppCompatActivity {
TextView lblHola;
    Thread tHilo;
    Handler hManejador = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        // AQUI SI SE PUEDE TRABAJAR CON LA INTERFAZ GRAFICA
            if(msg.what == 1){
                int i = (int)msg.obj;
                lblHola.append(i+" - ");

            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        lblHola = findViewById(R.id.lblHola);
        Hilo mhHilo = new Hilo();
        tHilo = new Thread(mhHilo);
        tHilo.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tHilo.interrupt();

    }

    class Hilo implements  Runnable{
        @Override
        public void run() {
            int i = 0;
            while(true){
                i++;
                try {
                   Message msg = hManejador.obtainMessage(1,i);
                  hManejador.sendMessage(msg);
                    Thread.sleep(1000);
                 //   lblHola.setText(i+"");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }


            }
        }
    }

}
