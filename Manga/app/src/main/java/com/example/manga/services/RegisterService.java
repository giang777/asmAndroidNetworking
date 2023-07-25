package com.example.manga.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.manga.api.LinkApi;
import com.example.manga.api.UsersApi;
import com.example.manga.elements.post.Data_Response_UserLogin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RegisterService extends Service {

    public final static String ACTION_REGISTER = "register";
    public final static String EXTRA_USERNAME = "username";
    public final static String EXTRA_EMAIL = "email";
    public final static String EXTRA_PASSWRD = "paswrd";
    public final static String EXTRA_PASSWRD2 = "paswrd2";
    public final static String EXTRA_USER = "user";
    public final static String EXTRA_MSG = "msg";
    public final static String EXTRA_RESULT = "result";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null && ACTION_REGISTER.equals(intent.getAction())){
            String username = intent.getStringExtra(EXTRA_USERNAME);
            String email = intent.getStringExtra(EXTRA_EMAIL);
            String passwrd = intent.getStringExtra(EXTRA_PASSWRD);
            String passwrd2 = intent.getStringExtra(EXTRA_PASSWRD2);
            if(username.length() <=0 || email.length() <=0 || passwrd.length() <=0 || passwrd2.length() <= 0){
                Toast.makeText(this, "Không được để chống !", Toast.LENGTH_SHORT).show();
            }else{
                if(!passwrd2.equals(passwrd)){
                    Toast.makeText(this, "Mật khẩu không khớp !", Toast.LENGTH_SHORT).show();
                }else{ //????????? bằng nhau mà lại vào được
                    ExecutorService executorService = Executors.newCachedThreadPool();
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            Intent resultIntent = new Intent();
                            UsersApi api = new UsersApi();
                            Data_Response_UserLogin userLogin = api.postUserLogin(username,email,passwrd, LinkApi.POST_USER_REGISTER);
                            resultIntent.setAction(ACTION_REGISTER);
                            resultIntent.putExtra(EXTRA_MSG,userLogin.getMsg());
                            resultIntent.putExtra(EXTRA_USER,userLogin.getData());
                            resultIntent.putExtra(EXTRA_RESULT,userLogin.isSuccess());
                            sendBroadcast(resultIntent);
                        }
                    });
                }
            }
        }
        return START_NOT_STICKY;
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
