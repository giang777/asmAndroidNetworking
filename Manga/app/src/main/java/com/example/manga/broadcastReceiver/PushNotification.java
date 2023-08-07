package com.example.manga.broadcastReceiver;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.manga.MainActivity;
import com.example.manga.R;
import com.example.manga.elements.child.Notifi;
import com.example.manga.notification.CreateNotification;
import com.example.manga.services.SocketService;

import java.util.Date;

public class PushNotification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Gửi broadcast để đóng kết nối Socket
        if (intent.getAction().equals(SocketService.ACTION_NOTIFICATION)) {
            Notifi notifi = (Notifi) intent.getSerializableExtra(SocketService.EXTRA_NOTIFI);
            showNotification(notifi,context);
        }
    }


    private void showNotification(Notifi notifi,Context context) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext(), "Manga")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(notifi.getTitel())
                .setContentText(notifi.getMsg())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        int id_notiy = (int) new Date().getTime();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("Manga", "Thông báo chạy ngầm", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(id_notiy, builder.build());
    }

}
