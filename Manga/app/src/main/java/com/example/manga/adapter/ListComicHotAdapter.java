package com.example.manga.adapter;

import android.content.Context;
import android.content.res.Resources;
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
import com.example.manga.elements.child.Comics;
import com.example.manga.fragments.FragmentWelcome;
import com.example.manga.interface_item.OnClickcomics;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

public class ListComicHotAdapter extends RecyclerView.Adapter<ListComicHotAdapter.Item> {

    private Context context;
    private List<Comics> list;

    private OnClickcomics clickcomics;

    public void setList(List<Comics> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ListComicHotAdapter(Context context,OnClickcomics onClickcomics) {
        this.context = context;
        this.clickcomics = onClickcomics;
    }

    @NonNull
    @Override
    public Item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_comics,parent,false);

        return new Item(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Item holder, int position) {
        Comics comics = list.get(position);
        if(comics == null){
            return;
        }
        holder.name.setText(comics.getName());
        holder.view.setText(String.valueOf(comics.getView()));
        Picasso.get()
                .load(comics.getAvatar_story())
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
                clickcomics.onclickItemComic(comics);
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
        private LinearLayout imageView;
        private TextView name,view;
        public Item(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_img_avatar);
            name = itemView.findViewById(R.id.item_name_comics);
            view = itemView.findViewById(R.id.item_view_comic);
        }
    }
}
