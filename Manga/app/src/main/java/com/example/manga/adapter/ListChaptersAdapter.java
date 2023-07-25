package com.example.manga.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manga.R;
import com.example.manga.elements.child.Contents;
import com.example.manga.interface_item.OnClickchapter;

import java.util.List;

public class ListChaptersAdapter extends RecyclerView.Adapter<ListChaptersAdapter.Item> {

    private Context context;

    private List<Contents> list;

    private OnClickchapter clickchapter;

    public void setList(List<Contents> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ListChaptersAdapter(Context context,OnClickchapter clickchapter) {
        this.context = context;
        this.clickchapter = clickchapter;
    }

    @NonNull
    @Override
    public Item onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lits_chapters,parent,false);

        return new Item(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Item holder, int position) {
        Contents contents = list.get(position);
        if(contents == null){
            return;
        }
        holder.name.setText(contents.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickchapter.onclickChapter(contents);
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
        private TextView name,view;
        public Item(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name_chapter);
        }
    }
}
