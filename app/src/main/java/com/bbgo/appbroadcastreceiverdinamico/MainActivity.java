package com.bbgo.appbroadcastreceiverdinamico;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //Classe LocalBroadcastManager:  mensagens trafegam apenas dentro da aplicação
    LocalBroadcastManager manager;

    private BroadcastReceiver helloReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("RESULTADO", "Hello Receiver Dinâmico!!!");
            TextView text = (TextView) findViewById(R.id.text);
            text.setText("Mensagem recebida pelo HelloReceiver Dinâmico!");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btEnviar).setOnClickListener(this);
        //Registra o receiver dinamicamente
        //registerReceiver(helloReceiver, new IntentFilter("BINGO"));

        //Registra o receiver local
        manager = LocalBroadcastManager.getInstance(this);
        manager.registerReceiver(helloReceiver, new IntentFilter("BINGO"));
    }

    @Override
    public void onClick(View v) {
        //Envia uma mensagem de broadcast para algum BroadcastReceiver.
        //sendBroadcast(new Intent("BINGO"));
        //Envio com manager
        manager.sendBroadcast(new Intent("BINGO"));
        Toast.makeText(this, "Intent enviada!", Toast.LENGTH_SHORT).show();
    }

    protected void onDestroy(){
        super.onDestroy();
        //Cancela o receiver quando a activity é encerrada
        //unregisterReceiver(helloReceiver);
        //Cancela manager
        manager.unregisterReceiver(helloReceiver);
    }
}
