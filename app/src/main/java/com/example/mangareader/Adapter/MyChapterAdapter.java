package com.example.mangareader.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangareader.Interface.IRecyclerItemClickListener;
import com.example.mangareader.Model.Chapters;
import com.example.mangareader.R;
import com.example.mangareader.ReadMangaActivity;
import com.example.mangareader.Static;

import java.util.List;

public class MyChapterAdapter extends RecyclerView.Adapter<MyChapterAdapter.MyViewHolder> {
    Context context;
    List<Chapters> chaptersList;
    LayoutInflater inflater;

    public MyChapterAdapter(Context context, List<Chapters> chaptersList) {
        this.context = context;
        this.chaptersList = chaptersList;
        inflater = LayoutInflater.from(context);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_chapter_numb;
        IRecyclerItemClickListener recyclerItemClickListener;

        public void setRecyclerItemClickListener(IRecyclerItemClickListener recyclerItemClickListener) {
            this.recyclerItemClickListener = recyclerItemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_chapter_numb = (TextView) itemView.findViewById(R.id.txt_chapter_numb);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recyclerItemClickListener.onClick(v, getAdapterPosition());
        }
    }

    @NonNull
    @Override
    public MyChapterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.chapteritem, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyChapterAdapter.MyViewHolder holder, int position) {
        holder.txt_chapter_numb.setText(chaptersList.get(position).getName());

        holder.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Static.chapterSelected = chaptersList.get(position);
                context.startActivity(new Intent(context, ReadMangaActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return chaptersList.size();
    }
}
