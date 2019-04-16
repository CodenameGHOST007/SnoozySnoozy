package com.example.snoozysnoozy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReciever extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){                           //receives the broadcast
            Intent i = new Intent();
            i.setClassName(context.getPackageName(),AlarmShow.class.getName());             //intents to Show Alarm in foreground
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            MainActivity.def=1;



        }
}

