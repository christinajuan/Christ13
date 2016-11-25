package com.example.user.christ13;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean isStart;
    private MyReseiver receiver;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.tv);

        receiver = new MyReseiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("SayIt");
        registerReceiver(receiver,filter);
    }
    @Override
    public void finish() {
        unregisterReceiver(receiver);
        super.finish();
    }

    public void startService(View v){
        Intent it = new Intent(this,MyService.class);
        it.putExtra("isstart",isStart);
        it.putExtra("value", (int)(Math.random()*100+1));
        startService(it);
        isStart=true;
    }
    public void stopService(View v){
        Intent it = new Intent(this,MyService.class);
        stopService(it);
    }
    private class MyReseiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("brad","got it");
            String mesg = intent.getStringExtra("data");
            textView.setText(mesg);
        }
    }
}
