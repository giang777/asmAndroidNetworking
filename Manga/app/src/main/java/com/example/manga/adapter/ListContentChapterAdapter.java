package com.example.manga.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.manga.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ListContentChapterAdapter extends BaseAdapter {
    private Context context;
    private List<String> imgs;


    public ListContentChapterAdapter(Context context, List<String> imgs) {
        this.context = context;
        this.imgs = imgs;
    }

    @Override
    public int getCount() {
        return imgs.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholer viewholer = null;
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_list_img_content,null);
            viewholer = new Viewholer();
            viewholer.imageView = view.findViewById(R.id.item_img_content);
            try{
                Picasso.get().load(imgs.get(i)).into(viewholer.imageView);
            }catch (Exception e){

            }
            view.setTag(viewholer);
        }else{
            viewholer = (Viewholer) view.getTag();
        }
        return view;
    }

    public class Viewholer{
        ImageView imageView;
    }
}
