package com.teamihc.ucalendar.notificaciones;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.teamihc.ucalendar.backend.entidades.Evento;

public class AlarmCreator
{
    public static void setAlarm(int id, Long time, Context context, Evento evento) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent alarmIntent = new Intent(context, AlarmReceiver.class);
        alarmIntent.putExtra("tittle", evento.getNombre());
        alarmIntent.putExtra("description", evento.getDescripcion());
        PendingIntent pendingIntent;
        pendingIntent = PendingIntent.getBroadcast(context, id, alarmIntent, PendingIntent.FLAG_ONE_SHOT);
        alarmIntent.setData((Uri.parse("custom://" + System.currentTimeMillis())));
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);

    }
}
