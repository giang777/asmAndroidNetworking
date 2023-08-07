package com.example.manga.thread;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manga.adapter.ListComicHotAdapter;
import com.example.manga.api.ComicsApi;
import com.example.manga.elements.child.Comics;

import java.util.List;

public class GetListComics implements Runnable{
    private Context context;
    private ListComicHotAdapter adapter;
    private RecyclerView recyclerView;


    public GetListComics(Context context,ListComicHotAdapter adapter, RecyclerView recyclerView) {
        this.context = context;
        this.adapter = adapter;
        this.recyclerView = recyclerView;
    }


    @Override
    public void run() {
        ComicsApi api = new ComicsApi();
        List<Comics> list = api.getDataComics("");
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
