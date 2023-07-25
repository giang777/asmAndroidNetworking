package com.example.manga.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.manga.api.LinkApi;
import com.example.manga.api.UsersApi;
import com.example.manga.elements.post.Data_Response_UserLogin;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginService extends Service {
    public static final String ACTION_LOGIN = "login";
    public static final String EXTRA_EMAIL = "email";
    public static final String EXTRA_PASSWRD = "passwrd";
    public static final String USER = "user";
    public static final String MESSAGE = "message";
    public static final String RESULT = "result";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && ACTION_LOGIN.equals(intent.getAction())) {
            String email = intent.getStringExtra(EXTRA_EMAIL);
            String passwrd = intent.getStringExtra(EXTRA_PASSWRD);

            if(email.length() <=0 || passwrd.length() <=0){
                Toast.makeText(this, "Không được để trống !", Toast.LENGTH_SHORT).show();
            }else{
                String EMAIL_PATTERN =
                        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                Pattern pattern = Pattern.compile(EMAIL_PATTERN);
                Matcher matcher = pattern.matcher(email);
                if(matcher.matches()){
                    ExecutorService executorService = Executors.newCachedThreadPool();
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            UsersApi api = new UsersApi();
                            //Chết ở chỗ này
                            Data_Response_UserLogin userLogin = api.postUserLogin("",email,passwrd, LinkApi.POST_USER_LOGIN);
                            Intent resultIntent = new Intent();
                            resultIntent.setAction(ACTION_LOGIN);
                            resultIntent.putExtra(RESULT,userLogin.isSuccess());
                            resultIntent.putExtra(MESSAGE,userLogin.getMsg());
                            resultIntent.putExtra(USER, (Serializable) userLogin.getData());
                            sendBroadcast(resultIntent);
                        }
                    });
                }else{
                    Toast.makeText(this, "Không đúng định dạng email", Toast.LENGTH_SHORT).show();
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
