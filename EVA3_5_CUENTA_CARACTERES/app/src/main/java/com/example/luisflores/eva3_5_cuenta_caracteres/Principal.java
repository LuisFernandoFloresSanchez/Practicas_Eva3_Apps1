package com.example.luisflores.eva3_5_cuenta_caracteres;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Principal extends AppCompatActivity {
    Handler hManejador = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 1){
            lblLector.setText(msg.obj.toString());
            }
            if(msg.what == 2){
                lblContador.setText("Total de letras = " + msg.obj.toString());

            }
            }
    };
    String sVal;
    int iContador;
    TextView lblLector,lblContador;
    EditText txtEscritor;
    Thread tHilo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        lblLector = findViewById(R.id.lblLector);
        txtEscritor = findViewById(R.id.txtEscritor);
        lblContador = findViewById(R.id.lblContador);
        Escritor mhHilo = new Escritor();
        tHilo = new Thread(mhHilo);
        tHilo.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tHilo.interrupt();

    }

    class Escritor extends Thread {

        @Override
        public void run() {
            super.run();


            while (true) {

                try {
                    Thread.sleep(100);

                    sVal = txtEscritor.getText().toString();
                    Message msg = hManejador.obtainMessage(1, sVal);
                    iContador = sVal.length();
                    Message msg2 = hManejador.obtainMessage(2, iContador);
                    hManejador.sendMessage(msg);
                    hManejador.sendMessage(msg2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
