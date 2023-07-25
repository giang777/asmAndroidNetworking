package com.example.manga.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manga.R;
import com.example.manga.adapter.ListBlogAdapter;
import com.example.manga.thread.GetListBlogs;
import com.example.manga.thread.GetListComics;
import com.example.manga.thread.SlideShow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FragmentNews extends Fragment {
    private ListBlogAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.recyclerView = view.findViewById(R.id.list_blog);
        this.adapter = new ListBlogAdapter(getActivity());
        Multithreading();
    }

    private void Multithreading(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        GetListBlogs getListBlogs = new GetListBlogs(getActivity(),adapter,recyclerView);
        executorService.execute(getListBlogs);
    }
}
