package com.example.manga.broadcastReceiver;

import android.Manifest;
import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.manga.MainActivity;
import com.example.manga.R;
import com.example.manga.notification.CreateNotification;
import com.example.manga.services.SocketService;

import java.util.Date;

public class PushNotification extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Gửi broadcast để đóng kết nối Socket
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent serviceIntent = new Intent(context, SocketService.class);
            context.startService(serviceIntent);
        }
    }

}
