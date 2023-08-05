package com.example.manga.notification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

public class CreateNotification extends Application {
    public final static String CHANEL_ID = "Manga";
    @Override
    public void onCreate() {
        super.onCreate();
        config();
    }

    void config() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Tên chanel
            CharSequence name = "Manga"; // tên hiển thị trong cài đặt notify của điện thoại
            // mo ta:
            String describe = "Manga";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .build();

            // đăng ký notify
            NotificationChannel channel = new NotificationChannel(CHANEL_ID, name, importance);

            channel.setDescription(describe);

            channel.setSound(uri, audioAttributes);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
