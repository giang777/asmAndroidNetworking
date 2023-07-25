package com.example.manga.thread;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manga.adapter.ListChaptersAdapter;
import com.example.manga.api.ComicsApi;
import com.example.manga.elements.child.Contents;

import java.util.List;

public class GetListChapters implements Runnable{
    private Context context;
    private ListChaptersAdapter adapter;
    private RecyclerView recyclerView;
    private String id;

    public GetListChapters(Context context, ListChaptersAdapter adapter, RecyclerView recyclerView, String id) {
        this.context = context;
        this.adapter = adapter;
        this.recyclerView = recyclerView;
        this.id = id;
    }

    @Override
    public void run() {
        ComicsApi getListComics_api = new ComicsApi();
        List<Contents> list = getListComics_api.getDataChapter(this.id).getContents();

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter.setList(list);
                LinearLayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
