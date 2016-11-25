package com.example.user.christ13;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {
    private Timer timer;
    private int i;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer = new Timer();
        timer.schedule(new MyTask(),1000,1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            boolean isStart = intent.getBooleanExtra("isstart", false);
            if (isStart) {
                i = intent.getIntExtra("value", -100);
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer !=null){
            timer.purge();
            timer.cancel();
            timer=null;
        }
    }

    private class MyTask extends TimerTask{
        @Override
        public void run() {
            //Log.v("brad", "i=" + i++);

            Intent it = new Intent("SayIt");
            it.putExtra("data", "i = " + i++);
            sendBroadcast(it);
        }
    }
}
