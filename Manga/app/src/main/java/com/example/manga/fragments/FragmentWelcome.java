package com.example.manga.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.manga.R;

import java.util.Timer;
import java.util.TimerTask;

public class FragmentWelcome extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            WifiManager wifiManager = (WifiManager) getContext().getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();

            int linkSpeed = wifiInfo.getLinkSpeed();

            double speedInKbps = linkSpeed * 1000.0;
            double delayInMilliseconds = 1000.0 / speedInKbps;

            try {
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_open,R.anim.fragment_exit).replace(R.id.fragment_container,new FragmentLogin()).commit();
                    }
                },(int)delayInMilliseconds + 2000);
            }catch (Exception e){
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_open,R.anim.fragment_exit).replace(R.id.fragment_container,new FragmentWelcome()).commit();
            }

        }

    }
}
