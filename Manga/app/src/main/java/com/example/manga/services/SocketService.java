package com.example.manga.services;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.manga.MainActivity;
import com.example.manga.R;
import com.example.manga.broadcastReceiver.PushNotification;
import com.example.manga.elements.child.Notifi;
import com.example.manga.notification.CreateNotification;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.util.Date;

import io.socket.emitter.Emitter;

public class SocketService extends Service {
    public static final String ACTION_CLOSE_SOCKET = "com.example.app.ACTION_CLOSE_SOCKET";
    private NotificationCompat.Builder builder;
    private NotificationManagerCompat notificationManager;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if (intent != null) {
            String action = intent.getAction();
            if (action != null && action.equals(ACTION_CLOSE_SOCKET)) {
                if (MainActivity.socket != null) {
                    MainActivity.socket.close();
                }
                stopSelf();
                return START_NOT_STICKY;
            }
        }
        connectToSocket();
        return START_STICKY;
    }

    private void connectToSocket() {
        MainActivity.socket.connect();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MainActivity.socket.on("new msg", new Emitter.Listener() {
                        @Override
                        public void call(Object... args) {
                            String data_sv_send = (String) args[0];
                            Gson gson = new Gson();
                            Notifi notifi = gson.fromJson(data_sv_send,Notifi.class);
                            showNotification(notifi);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    stopSelf();
                }
            }
        }).start();

    }

    private void showNotification(Notifi notifi) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "Manga")
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

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (MainActivity.socket != null) {
            MainActivity.socket.close();
        }
    }
}
