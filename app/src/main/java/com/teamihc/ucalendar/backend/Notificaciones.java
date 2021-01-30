package com.teamihc.ucalendar.backend;

import android.content.Context;

import androidx.core.app.NotificationManagerCompat;

public class Notificaciones
{
    private static NotificationManagerCompat manager;
    private static Context context;
    
    public static void inicializarManager(Context c)
    {
        context = c;
        manager = NotificationManagerCompat.from(context);
    }
    
    public static Context getContext()
    {
        return context;
    }
    public static NotificationManagerCompat getManager()
    {
        return manager;
    }
}
