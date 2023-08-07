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
    private NotificationCompat.Builder builder;
    private NotificationManagerCompat notificationManager;

    public final static String ACTION_NOTIFICATION = "push_notification";
    public final static String EXTRA_NOTIFI = "notification";
    @Override
    public void onCreate() {
        super.onCreate();
        MainActivity.socket.connect();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        connectToSocket(intent);
        return START_STICKY;
    }

    private void connectToSocket(Intent intent) {

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
                            intent.setAction(ACTION_NOTIFICATION);
                            intent.putExtra(EXTRA_NOTIFI,notifi);
                            sendBroadcast(intent);
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
    }
}
