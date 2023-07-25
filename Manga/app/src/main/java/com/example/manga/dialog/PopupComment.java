package com.example.manga.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manga.MainActivity;
import com.example.manga.R;
import com.example.manga.adapter.ListCommentHotAdapter;
import com.example.manga.api.CommentApi;
import com.example.manga.elements.child.Comics;
import com.example.manga.elements.child.Comments;
import com.example.manga.services.CommentService;
import com.example.manga.services.LoginService;
import com.example.manga.thread.GetListComments;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PopupComment extends Dialog{
    private ImageView close_dialog,avatar_user;
    private RecyclerView list_cmt;
    private TextInputLayout text_cmt;
    private AppCompatButton button;
    private Context context;
    private Comics comics;

    private IntentFilter filter;
    public PopupComment(@NonNull Context context, Comics comics) {
        super(context);
        this.context = context;
        this.comics = comics;
    }

    public PopupComment(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected PopupComment(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);


    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_show_comment);
        this.close_dialog = findViewById(R.id.close_dialog);
        this.list_cmt = findViewById(R.id.list_comment);
        this.avatar_user = findViewById(R.id.img_avatar_UserLogin);
        this.text_cmt = findViewById(R.id.comment_content);
        this.button = findViewById(R.id.btn_sendComment);

        this.filter = new IntentFilter(CommentService.ACTION_CMT);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(resultReceiver, filter);

        try{
            Picasso.get().load(MainActivity.UserLogin.getAvatar()).into(avatar_user);
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        close_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(text_cmt.getEditText().getText().toString().length() <=0 ){
                    text_cmt.setError("Không được để trống bình luận");
                }else{
                    Intent intent = new Intent(context, CommentService.class);
                    intent.setAction(CommentService.ACTION_CMT);
                    intent.putExtra(CommentService.EXTRA_CMT,text_cmt.getEditText().getText().toString());
                    intent.putExtra(CommentService.EXTRA_USER, MainActivity.UserLogin.get_id());
                    intent.putExtra(CommentService.EXTRA_COMICS,comics.get_id());
                    context.startService(intent);
                }
            }
        });

        Multithreading();
    }

    @Override
    protected void onStart() {
        super.onStart();
        context.registerReceiver(resultReceiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        context.unregisterReceiver(resultReceiver);
    }

    private void getData(){
        ListCommentHotAdapter adapter = new ListCommentHotAdapter(getContext());
        CommentApi commentApi = new CommentApi();
        List<Comments> list = commentApi.getDataComment(comics.get_id(),0);
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        list_cmt.post(new Runnable() {
            @Override
            public void run() {
                list_cmt.setLayoutManager(manager);
                list_cmt.setAdapter(adapter);
            }
        });

    }

    private void Multithreading(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
           @Override
           public void run() {
               getData();
           }
       });
    }

    private BroadcastReceiver resultReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(CommentService.ACTION_CMT.matches(intent.getAction())){
                String msg = intent.getStringExtra(CommentService.MESSAGE);
                boolean success = intent.getBooleanExtra(CommentService.RESULT,false);
                if(success){
                    Multithreading();
                    Toast.makeText(context,msg,Toast.LENGTH_SHORT);
                    text_cmt.getEditText().setText("");
                }else{
                    Toast.makeText(context,msg,Toast.LENGTH_SHORT);
                }
            }
        }
    };
}
