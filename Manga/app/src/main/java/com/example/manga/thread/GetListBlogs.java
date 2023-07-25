package com.example.manga.thread;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manga.adapter.ListBlogAdapter;
import com.example.manga.api.BlogsApi;
import com.example.manga.elements.child.Blogs;

import java.util.List;

public class GetListBlogs implements Runnable{
    private Context context;
    private ListBlogAdapter adapter;
    private RecyclerView recyclerView;

    public GetListBlogs(Context context, ListBlogAdapter adapter, RecyclerView recyclerView) {
        this.context = context;
        this.adapter = adapter;
        this.recyclerView = recyclerView;
    }

    @Override
    public void run() {
        BlogsApi api = new BlogsApi();
        List<Blogs> list = api.getDataBlogs();
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
