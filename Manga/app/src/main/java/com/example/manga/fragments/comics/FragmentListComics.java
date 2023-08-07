package com.example.manga.fragments.comics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manga.R;
import com.example.manga.adapter.ListComicHotAdapter;
import com.example.manga.api.ComicsApi;
import com.example.manga.elements.child.Comics;
import com.example.manga.interface_item.OnClickcomics;
import com.example.manga.interface_item.SenDataTabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FragmentListComics extends Fragment{
    private RecyclerView recyclerView;
    private ListComicHotAdapter adapter;
    private List<Comics> list = new ArrayList<>();
    public String category = " ";

    private SearchView searchView;
    private static final String TAG = "FRAGMENT_LISTCOMICS";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_comics_category,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.recyclerView = view.findViewById(R.id.list_comic_category);
        this.searchView = view.findViewById(R.id.search_bar);
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

        Multithreading();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(list.isEmpty()){
                    return true;
                }

                if(newText.equals("")){
                    adapter.setList(list);
                    return true;
                }

                List<Comics> listFillter = new ArrayList<>();
                for (Comics comics : list){
                    if(comics.getName().toLowerCase().contains(newText.toLowerCase())){
                        listFillter.add(comics);
                    }
                }

                adapter.setList(listFillter);

                return true;
            }
        });

    }

    public void Multithreading(){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ComicsApi comicsApi = new ComicsApi();
                list = comicsApi.getDataComics(category);
                adapter.setList(list);

                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                    }
                });
            }
        });
    }


}
