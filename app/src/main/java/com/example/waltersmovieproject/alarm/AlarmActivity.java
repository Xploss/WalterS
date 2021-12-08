package com.example.waltersmovieproject.alarm;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.waltersmovieproject.AboutActivity;
import com.example.waltersmovieproject.MainActivity;
import com.example.waltersmovieproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AlarmActivity extends AppCompatActivity {
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private static final int NOTIF_ID = 0;
    private NotificationManager mNotificationManager;
    Toolbar tbMvAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        tbMvAlarm = findViewById(R.id.tbAlarm);
        tbMvAlarm.setTitle("AlarmActivity");
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        //set laman alarm
        bottomNavigationView.setSelectedItemId(R.id.alarm);

        //itemselectlistener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.about:
                        startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.alarm:
                        return true;
                }
                return false;
            }
        });

        mNotificationManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);

        ToggleButton alarmToggle = findViewById(R.id.alarmToggle);
        // ngeset notif bc inten
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);

        boolean alarmUp = (PendingIntent.getBroadcast(this, NOTIF_ID, notifyIntent,
                PendingIntent.FLAG_NO_CREATE) != null);
        alarmToggle.setChecked(alarmUp);

        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, NOTIF_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmToggle.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                        String toastMessage;
                        if (isChecked) {
                            long repeatInterval = 10;
                            long triggerTime = SystemClock.elapsedRealtime()
                                    + repeatInterval;
                            // AlarmActivity setiap 5 detik
                            if (alarmManager != null) {
                                alarmManager.setInexactRepeating
                                        (AlarmManager.RTC,
                                                triggerTime, repeatInterval,
                                                notifyPendingIntent);
                            }
                            //buat toast alarm aktif
                            toastMessage = getString(R.string.alarm_on);

                        }  else {
                            //membatalkan pemberitahuan alarm
                            mNotificationManager.cancelAll();
                            if (alarmManager != null) {
                                alarmManager.cancel(notifyPendingIntent);
                            }
                            //buat toast alarm nonaktif
                            toastMessage = getString(R.string.alarm_off);
                        }
                        //kasih lihat toast alarm
                        Toast.makeText(AlarmActivity.this, toastMessage,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        createNotifChannel();

    }

    public void createNotifChannel() {
        // Create a notification manager object.
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Notification channels are only available in OREO and higher.
        // So, add a check on SDK version.
        if (android.os.Build.VERSION.SDK_INT >=
                android.os.Build.VERSION_CODES.O) {

            // Create the NotificationChannel with all the parameters.
            NotificationChannel notifChannel = new NotificationChannel
                    (PRIMARY_CHANNEL_ID,
                            "Stand up notification",
                            NotificationManager.IMPORTANCE_HIGH);

            notifChannel.enableLights(true);
            notifChannel.setLightColor(Color.RED);
            notifChannel.enableVibration(true);
            notifChannel.setDescription
                    ("Notifies every 15 minutes to stand up and walk");
            mNotificationManager.createNotificationChannel(notifChannel);
        }

    }
}