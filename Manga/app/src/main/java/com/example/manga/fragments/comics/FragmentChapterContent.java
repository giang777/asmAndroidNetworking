package com.example.manga.fragments.comics;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.manga.R;
import com.example.manga.adapter.ListContentChapterAdapter;
import com.example.manga.elements.child.Contents;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FragmentChapterContent extends Fragment {
    private ImageView btn_back,btn_prewChapter,btn_nextChapter;
    private ListView listView;
    private TextView nameChapter;
    private LinearLayout header,footer;
    private Spinner spinner;
    private int index= 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chapter,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.btn_back = view.findViewById(R.id.btn_back);
        this.btn_prewChapter = view.findViewById(R.id.btn_prewCHapter);
        this.btn_nextChapter = view.findViewById(R.id.btn_nextCHapter);
        this.listView = view.findViewById(R.id.list_item_content);
        this.spinner = view.findViewById(R.id.my_spinner);
        this.nameChapter = view.findViewById(R.id.name_chapter);
        this.header = view.findViewById(R.id.header_name_chapter);
        this.footer = view.findViewById(R.id.footer_name_chapter);

        Bundle bundle = getArguments();
        Contents contents = (Contents) bundle.get("content");
        List<Contents> list = (List<Contents>) bundle.get("listContent");

        this.index = searchIndex(list,contents);
        btn_prewChapter.setVisibility(this.index == 0 ? View.INVISIBLE : View.VISIBLE);
        btn_nextChapter.setVisibility(this.index == (list.size()-1) ? View.INVISIBLE : View.VISIBLE);

        ListContentChapterAdapter adapter = new ListContentChapterAdapter(getActivity(),contents.getPainting_data());

        nameChapter.setText(contents.getName());
        listView.setAdapter(adapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                header.setVisibility(View.INVISIBLE);
                footer.setVisibility(View.INVISIBLE);
                if(i == 0){
                    header.setVisibility(View.VISIBLE);
                    footer.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                index = list.size() - (i+1);
                Contents contents = list.get(index);
                ListContentChapterAdapter adapter = new ListContentChapterAdapter(getActivity(),contents.getPainting_data());
                nameChapter.setText(contents.getName());
                listView.setAdapter(adapter);
                btn_prewChapter.setVisibility(index == 0 ? View.INVISIBLE : View.VISIBLE);
                btn_nextChapter.setVisibility(index == (list.size()-1) ? View.INVISIBLE : View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_prewChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prewChapter(list);
            }
        });

        btn_nextChapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextChapter(list);
            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        Multithreading(list,this.index);
    }

    private int searchIndex(List<Contents> list,Contents contents){
        int index = 0;
        for (int i =0;i < list.size();i++){
            if(list.get(i).getName().equals(contents.getName())){
                index = i;
                break;
            }
        }

        return index;
    }

    private void prewChapter(List<Contents> list){
        this.index--;
        if(this.index >= 0){
            Contents contents = list.get(index);
            ListContentChapterAdapter adapter = new ListContentChapterAdapter(getActivity(),contents.getPainting_data());
            this.nameChapter.setText(contents.getName());
            this.listView.setAdapter(adapter);
            this.spinner.setSelection(list.size() - (index+1));
            btn_nextChapter.setVisibility(View.VISIBLE);
        }

        if(index == 0){
            btn_prewChapter.setVisibility(View.INVISIBLE);
        }
    }

    private void nextChapter(List<Contents> list){
        this.index++;
        if(index < list.size()){
            Contents contents = list.get(index);
            ListContentChapterAdapter adapter = new ListContentChapterAdapter(getActivity(),contents.getPainting_data());
            this.nameChapter.setText(contents.getName());
            this.listView.setAdapter(adapter);
            this.spinner.setSelection(list.size() - (index+1));
            btn_prewChapter.setVisibility(View.VISIBLE);
        }

        if(index == (list.size()-1)){
            btn_nextChapter.setVisibility(View.INVISIBLE);
        }
    }

    private void Multithreading(List<Contents> list,int index){
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                List<String> itemName = new ArrayList<>();
                for (int i =(list.size()-1);i >= 0;i--){
                    itemName.add(list.get(i).getName());
                }

                spinner.post(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, itemName);
                        int indexItem = list.size() - (index+1);
                        spinner.setAdapter(adapter);
                        spinner.setSelection(indexItem);
                    }
                });

            }
        });
    }


}
