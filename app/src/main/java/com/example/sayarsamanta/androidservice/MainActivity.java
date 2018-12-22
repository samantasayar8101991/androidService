package com.example.sayarsamanta.androidservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private MyBroadcastReceiver myReceiver;
    Button start, stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //On start button click start service
        start = (Button) findViewById(R.id.button_start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(getBaseContext(), MyService.class));
            }
        });

        //On stop button click start service
        stop = (Button) findViewById(R.id.button_stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(getBaseContext(), MyService.class));
            }
        });
    }

    @Override
    protected void onResume() {
        myReceiver = new MyBroadcastReceiver();
        final IntentFilter intentFilter = new IntentFilter("YourAction");
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver, intentFilter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (myReceiver != null)
            LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);
        myReceiver = null;
        super.onPause();
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // Here you have the received broadcast
            // And if you added extras to the intent get them here too
            // this needs some null checks
            Bundle b = intent.getExtras();
            String yourValue = b.getString("valueName");
            stop.setText(yourValue);
            double someDouble = b.getDouble("doubleName");
            ///do something with someDouble
        }
    }
}
