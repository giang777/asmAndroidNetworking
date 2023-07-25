package com.example.manga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.example.manga.elements.child.User;
import com.example.manga.fragments.FragmentWelcome;

public class MainActivity extends AppCompatActivity {

    public static User UserLogin = null;


    //hehe
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phanQuyen();
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
}