package com.teamihc.ucalendar.notificaciones;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.core.content.ContextCompat;

public class AlarmReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent service1 = new Intent(context, NotificationService.class);
        service1.setData((Uri.parse("custom://" + System.currentTimeMillis())));
        ContextCompat.startForegroundService(context, service1 );
    }
}
