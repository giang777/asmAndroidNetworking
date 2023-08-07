package com.example.manga.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manga.R;
import com.example.manga.adapter.ListComicHotAdapter;
import com.example.manga.elements.child.Comics;
import com.example.manga.fragments.comics.FragmentCategoryComics;
import com.example.manga.fragments.comics.FragmentComic;
import com.example.manga.interface_item.OnClickcomics;
import com.example.manga.thread.GetListComics;
import com.example.manga.thread.SlideShow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FragmentMain extends Fragment {
    private LinearLayout layout;
    private int[] arrImg = {R.drawable.slide_show_1,R.drawable.slide_show_2,R.drawable.slide_show_3,R.drawable.slide_show_4,R.drawable.slide_show_5};
    private ListComicHotAdapter adapter;
    private RecyclerView recyclerView;
    private List<Comics> list;
    private static final String TAG = "FRAGMENT_MAIN";
    private ImageView icon_app;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.layout = view.findViewById(R.id.SliderShow);
        this.icon_app = view.findViewById(R.id.icon_app);
        this.recyclerView = view.findViewById(R.id.list_comic_hot);

        icon_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_open,R.anim.fragment_exit).replace(R.id.fragment_container,new FragmentCategoryComics()).addToBackStack(TAG).commit();
            }
        });

        this.adapter = new ListComicHotAdapter(getActivity(), new OnClickcomics() {
            @Override
            public void onclickItemComic(Comics comics) {
                FragmentComic fragmentComic = new FragmentComic();
                Bundle bundle = new Bundle();
                bundle.putSerializable("comics",comics);
                fragmentComic.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_open,R.anim.fragment_exit).replace(R.id.fragment_container,fragmentComic).addToBackStack(TAG).commit();
            }
        });
        this.list = new ArrayList<>();
        Multithreading();
    }

    private void Multithreading(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        SlideShow slideShow = new SlideShow(arrImg,layout);
        GetListComics getListComics = new GetListComics(getActivity(),adapter,recyclerView);
        executorService.execute(slideShow);
        executorService.execute(getListComics);
    }


}
