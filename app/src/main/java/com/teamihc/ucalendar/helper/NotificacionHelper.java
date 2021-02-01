package com.teamihc.ucalendar.helper;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.teamihc.ucalendar.R;

public class NotificacionHelper extends ContextWrapper
{
    public static final String CANAL_ID = "perSio";
    public static final String CANAL_NOMBRE = "mucho presio";
    public static final String CANAL_DESCRIPCION = "team_ihc.es.lo.mejor";
    public static final String KEY_GROUP = "EVENTOS GUARDADOS UCALENDAR";

    private NotificationManager notificationManager;

    public NotificacionHelper(Context base) { super(base); notificationManager = null;}


    public void crearCanales()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel canal = new NotificationChannel(
                    CANAL_ID,
                    CANAL_NOMBRE,
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            canal.setDescription(CANAL_DESCRIPCION);
            canal.enableLights(false);
            canal.enableVibration(true);
            canal.setLightColor(getColor(R.color.design_default_color_primary));
            canal.setShowBadge(true);
            canal.setSound(null, null);

            getNotificationManager().createNotificationChannel(canal);

        }
    }

    public NotificationManager getNotificationManager()
    {
        if (notificationManager == null)
        {
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return notificationManager;
    }
}
