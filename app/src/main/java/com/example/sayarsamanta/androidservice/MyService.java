package com.example.sayarsamanta.androidservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

public class MyService extends Service {
    private MediaPlayer player;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        player = MediaPlayer.create(this,Settings.System.DEFAULT_ALARM_ALERT_URI);
//        player.setLooping(true);
//        player.start();
//        return START_STICKY;
        Intent intent1 = new Intent("YourAction");
        Bundle bundle = new Bundle();
        for (int i=0;i<100;i++){
            bundle.putString("valueName", "The value "+i);
        }
        //bundle.put... // put extras you want to pass with broadcast. This is optional

        //bundle.putDouble("doubleName", someDouble);
        intent1.putExtras(bundle);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent1);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
