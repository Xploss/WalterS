package com.example.waltersmovieproject.alarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.waltersmovieproject.R;

public class AlarmReceiver extends BroadcastReceiver {

    private NotificationManager mNotificationManager;
    private static final int NOTIF_ID = 0;

    // Notification channel ID.
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        deliverNotification(context);
    }
    private void deliverNotification(Context context) {
        Intent contentIntent = new Intent(context, AlarmActivity.class);
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (context, NOTIF_ID, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, PRIMARY_CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentTitle(context.getString(R.string.notification_title))
                .setContentText(context.getString(R.string.notification_text))
                .setContentIntent(contentPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL);
        mNotificationManager.notify(NOTIF_ID, builder.build());
    }
}

