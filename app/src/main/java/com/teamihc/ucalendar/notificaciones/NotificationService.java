package com.teamihc.ucalendar.notificaciones;

import android.annotation.TargetApi;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.teamihc.ucalendar.R;
import com.teamihc.ucalendar.activities.MainActivity;
import com.teamihc.ucalendar.backend.Notificaciones;
import com.teamihc.ucalendar.backend.entidades.Evento;


public class NotificationService extends IntentService
{
    private static final String CHANNEL_ID = "perSio";
    private static final String CHANNEL_NAME = "mucho presio";
    private static final String CHANNEL_DESCRIPTION = "team_ihc.es.lo.mejor";
    private static final String KEY_GROUP = "EVENTOS GUARDADOS UCALENDAR";


    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;
    private String tittle, description;
    Notification notification;

    /**
     * @param name
     * @deprecated
     */
    public NotificationService(String name) {
        super(name);
    }

    public NotificationService() {
        super("SERVICIO DE NOTIFICACIONES UCALENDAR");
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onHandleIntent(@Nullable Intent intent){ }

    public NotificationManager getNotificationManager()
    {
        if (notificationManager == null)
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        return notificationManager;
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId)
    {
        Context context = Notificaciones.getContext();
        tittle = intent.getStringExtra("tittle");
        description = intent.getStringExtra("description");

        Intent mIntent = new Intent(this, MainActivity.class);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = getNotificationManager().getNotificationChannel(CHANNEL_ID);

            if (channel == null)
            {
                channel = new NotificationChannel(
                        CHANNEL_ID,
                        CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT
                );
                channel.setDescription(CHANNEL_DESCRIPTION);
                channel.enableLights(false);
                channel.enableVibration(true);
                channel.setLightColor(getColor(R.color.design_default_color_primary));
                channel.setShowBadge(true);
                channel.setSound(null, null);

                getNotificationManager().createNotificationChannel(channel);
            }

            mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(Notificaciones.getContext(), CHANNEL_ID).
                    setSmallIcon(R.drawable.ucalendar_logo).
                    setContentTitle("Inscripciones PLIUL").
                    setContentText("Apuesta por tu formación personal, espiritual, ciudadana e incluso profesional con el Programa de Liderazgo Ignaciano Universitario").
                    setPriority(NotificationCompat.PRIORITY_DEFAULT).
                    setCategory(NotificationCompat.CATEGORY_SERVICE).
                    setGroup(KEY_GROUP).
                    setColor(Color.GREEN)
                    .setContentIntent(pendingIntent)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

            notification = builder.build();
            Notificaciones.getManager().notify(1, notification);
            startForeground(0451, notification);
            System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        }  else {
            pendingIntent = PendingIntent.getActivity(context, 1, mIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification = new NotificationCompat.Builder(this)
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ucalendar_logo)
                    .setContentTitle(tittle)
                    .setContentText(description)
                    .setCategory(Notification.CATEGORY_SERVICE).build();
            Notificaciones.getManager().notify(1, notification);
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
