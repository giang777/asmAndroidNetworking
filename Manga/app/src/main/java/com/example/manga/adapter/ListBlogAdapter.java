package com.example.manga.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manga.R;
import com.example.manga.elements.child.Blogs;
import com.example.manga.elements.child.Comics;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class ListBlogAdapter extends RecyclerView.Adapter<ListBlogAdapter.Item> {

    private Context context;
    private List<Blogs> list;

    public void setList(List<Blogs> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ListBlogAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public Item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_blogs,parent,false);

        return new Item(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Item holder, int position) {
        Blogs blogs = list.get(position);
        if(blogs == null){
            return;
        }
        holder.titel.setText(blogs.getTitel());
        holder.content.setText(blogs.getContent());
        Picasso.get()
                .load(blogs.getImg())
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        holder.imageView.setBackground(new BitmapDrawable(bitmap));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class Item extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView titel,content;
        public Item(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_blog_img);
            titel = itemView.findViewById(R.id.item_blog_titel);
            content = itemView.findViewById(R.id.item_blog_content);
        }
    }
}
