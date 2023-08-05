package com.example.manga.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.manga.api.ComicsApi;
import com.example.manga.api.UsersApi;
import com.example.manga.elements.child.Follow;
import com.example.manga.elements.post.Data_Response_FollowComic;

import java.io.Serializable;
import java.util.List;

public class FollowOrViewComicService extends Service {

    public static final String ACTION_FOLLOW = "follow";
    public static final String EXTRA_ISFOLLOW = "isFollow";
    public static final String EXTRA_ID_USER = "id_user";
    public static final String EXTRA_LIST_COMICS = "listComics";
    public static final String LIST_COMICS = "user";
    public static final String MESSAGE = "message";
    public static final String RESULT = "result";
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null && intent.getAction().equals(ACTION_FOLLOW)){
            String id_user = intent.getStringExtra(EXTRA_ID_USER);
            List<String> list = (List<String>) intent.getSerializableExtra(EXTRA_LIST_COMICS);
            boolean isFollow = intent.getBooleanExtra(EXTRA_ISFOLLOW,false);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    UsersApi usersApi = new UsersApi();
                    Data_Response_FollowComic dataResponseFollowComic = usersApi.patchUserFlComic(new Follow(id_user,list,isFollow));
                    Intent intent1 = new Intent();
                    intent1.putExtra(LIST_COMICS, (Serializable) dataResponseFollowComic.getListComics());
                    intent1.putExtra(MESSAGE,dataResponseFollowComic.getMsg());
                    intent1.putExtra(RESULT,dataResponseFollowComic.isSuccess());
                    intent1.setAction(ACTION_FOLLOW);
                    sendBroadcast(intent1);
                }
            }).start();
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
