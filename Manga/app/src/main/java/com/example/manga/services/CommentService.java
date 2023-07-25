package com.example.manga.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.manga.api.CommentApi;
import com.example.manga.elements.post.Data_Response_Post;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CommentService extends Service {

    public static final String ACTION_CMT = "cmt";
    public static final String EXTRA_CMT = "content";
    public static final String EXTRA_USER = "id_user";
    public static final String EXTRA_COMICS ="id_comics";
    public static final String MESSAGE = "message";
    public static final String RESULT = "result";

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null && ACTION_CMT.equals(intent.getAction())){
            String cmt = intent.getStringExtra(EXTRA_CMT);
            String id_user = intent.getStringExtra(EXTRA_USER);
            String id_comics = intent.getStringExtra(EXTRA_COMICS);
            ExecutorService service = Executors.newSingleThreadExecutor();
            service.execute(new Runnable() {
                @Override
                public void run() {
                    CommentApi api = new CommentApi();
                    Data_Response_Post post = api.postComment(cmt,id_user,id_comics);
                    Intent resultIntent = new Intent();
                    resultIntent.setAction(ACTION_CMT);
                    resultIntent.putExtra(RESULT,post.isSuccess());
                    resultIntent.putExtra(MESSAGE,post.getMsg());
                    sendBroadcast(resultIntent);
                }
            });
        }


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
