package com.example.manga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.Manifest;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.manga.api.LinkApi;
import com.example.manga.broadcastReceiver.PushNotification;
import com.example.manga.elements.child.User;
import com.example.manga.fragments.FragmentWelcome;
import com.example.manga.services.FollowOrViewComicService;
import com.example.manga.services.SocketService;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;

public class MainActivity extends AppCompatActivity {

    public static User UserLogin = null;
    public static Socket socket;
    {
        try {
            socket = IO.socket(LinkApi.URI);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private IntentFilter filter;


    //hehe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //socket.connect();
        Intent intent = new Intent(this, SocketService.class);
        startService(intent);
        this.filter = new IntentFilter(FollowOrViewComicService.ACTION_FOLLOW);
        LocalBroadcastManager.getInstance(this).registerReceiver(new PushNotification(), filter);
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

            // Gọi hộp thoại hiển thị xin quyền người dùng
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, 999999);
            return; // thoát khỏi hàm nếu chưa được cấp quyền
        }


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FragmentWelcome()).commit();
    }

    public boolean phanQuyen()
    {
        if(Build.VERSION.SDK_INT>=23) {
            if (checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) ==
                    PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_WIFI_STATE) ==
                    PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.CAMERA) ==
                    PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.READ_MEDIA_IMAGES) ==
                    PackageManager.PERMISSION_GRANTED)
            {
                return true;
            }
            else
            {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_NETWORK_STATE,
                                Manifest.permission.ACCESS_WIFI_STATE,Manifest.permission.CAMERA,Manifest.permission.READ_MEDIA_IMAGES},1);
                return false;
            }

        }else{
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(new PushNotification(),this.filter);
    }


    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(new PushNotification());
    }
}