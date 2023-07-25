package com.example.manga.fragments.comics;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manga.R;
import com.example.manga.adapter.ListChaptersAdapter;
import com.example.manga.adapter.ListCommentAdapter;
import com.example.manga.api.ComicsApi;
import com.example.manga.dialog.PopupComment;
import com.example.manga.elements.child.Comics;
import com.example.manga.elements.child.Contents;
import com.example.manga.interface_item.OnClickchapter;
import com.example.manga.thread.GetListChapters;
import com.example.manga.thread.GetListComments;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FragmentComic extends Fragment {
    private TextView name,author,desc,viewComics,follow,popup;

    private FrameLayout frameLayout;
    private LinearLayout btn_readComic;
    private ImageView btn_back;

    private RecyclerView recyclerView,recyclerViewComment;
    private ListChaptersAdapter adapter;
    private ListCommentAdapter commentAdapter;

    private static final String TAG = "FRAGMENT_COMIC";
    private List<Contents> list = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comic_chapter,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.popup = view.findViewById(R.id.show_popupComment);
        this.name = view.findViewById(R.id.name_comics);
        this.author = view.findViewById(R.id.author_comic);
        this.desc = view.findViewById(R.id.describe_comic);
        this.viewComics = view.findViewById(R.id.view_comic);
        this.follow= view.findViewById(R.id.follow_comic);
        this.frameLayout = view.findViewById(R.id.header_comics);
        this.btn_back = view.findViewById(R.id.btn_back);
        this.btn_readComic = view.findViewById(R.id.btn_readComic);
        this.commentAdapter = new ListCommentAdapter(getActivity());
        this.recyclerView = view.findViewById(R.id.chapter_comics);
        this.recyclerViewComment = view.findViewById(R.id.list_comment);

        Bundle bundle = getArguments();
        Comics comics = (Comics) bundle.get("comics");

        name.setText(comics.getName());
        author.setText("Tác giả : " + comics.getAuthor());
        desc.setText(comics.getDescribe());
        viewComics.setText(String.valueOf(comics.getView()));
        follow.setText(String.valueOf(comics.getFollow()));

        Picasso.get().load(comics.getImg()).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                frameLayout.setBackground(new BitmapDrawable(bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        this.adapter= new ListChaptersAdapter(getActivity(), new OnClickchapter() {
            @Override
            public void onclickChapter(Contents contents) {
              sendData(contents,list);
            }
        });


        btn_readComic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              sendData(list.get(0),list);
            }
        });

        popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupComment popupComment = new PopupComment(getActivity(),comics);
                popupComment.setCancelable(false);
                popupComment.show();
                popupComment.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupComment.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                popupComment.getWindow().setGravity(Gravity.BOTTOM);
            }
        });

        Multithreading(comics.getId_chapter(),comics.get_id());

    }

    private void Multithreading(String id_chapter,String id_comics){
        ExecutorService executorService = Executors.newCachedThreadPool();
        GetListChapters getListChapters = new GetListChapters(getActivity(),adapter,recyclerView,id_chapter);
        GetListComments getListComments = new GetListComments(getActivity(),commentAdapter,recyclerViewComment,id_comics);
        executorService.execute(getListChapters);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                ComicsApi getListComics_api = new ComicsApi();
                list = getListComics_api.getDataChapter(id_chapter).getContents();
            }
        });
        executorService.execute(getListComments);
    }

    private void sendData(Contents contents,List<Contents> list){
        FragmentChapterContent chapterContent = new FragmentChapterContent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("content",contents);
        bundle.putSerializable("listContent", (Serializable) list);
        chapterContent.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fragment_open,R.anim.fragment_exit).replace(R.id.fragment_container,chapterContent).addToBackStack(TAG).commit();
    }
}
