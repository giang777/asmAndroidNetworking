package com.example.manga.thread;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manga.adapter.ListChaptersAdapter;
import com.example.manga.adapter.ListCommentAdapter;
import com.example.manga.api.CommentApi;
import com.example.manga.elements.child.Comments;

import java.util.List;

public class GetListComments implements Runnable{
    private Context context;
    private ListCommentAdapter adapter;
    private RecyclerView recyclerView;
    private String id;

    public GetListComments(Context context, ListCommentAdapter adapter, RecyclerView recyclerView, String id) {
        this.context = context;
        this.adapter = adapter;
        this.recyclerView = recyclerView;
        this.id = id;
    }

    @Override
    public void run() {
        CommentApi commentApi = new CommentApi();
        List<Comments> list = commentApi.getDataComment(this.id,3);
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter.setList(list);
                LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
