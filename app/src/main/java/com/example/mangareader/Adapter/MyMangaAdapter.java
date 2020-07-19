package com.example.mangareader.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mangareader.ChaptersActivity;

import com.example.mangareader.Interface.IRecyclerItemClickListener;
import com.example.mangareader.Model.Manga;
import com.example.mangareader.R;
import com.example.mangareader.Static;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyMangaAdapter extends RecyclerView.Adapter<MyMangaAdapter.MyViewHolder> {

    Context context;
    List<Manga> mangaList;
    LayoutInflater inflater;

    public MyMangaAdapter(Context context, List<Manga> mangaList) {
        this.context = context;
        this.mangaList = mangaList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.manga_item, parent, false);
        return new MyViewHolder(itemView);
}

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.get().load(mangaList.get(position).getImage()).into(holder.image_manga);
        holder.manga_name.setText(mangaList.get(position).getName());


        //event
        holder.setRecyclerItemClickListener(new IRecyclerItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                //save manga selected

                Static.mangaSelected = mangaList.get(position);
                //context.startActivity(new Intent(context, ChaptersActivity.class));

                Intent intent = new Intent(context, ChaptersActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                }
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mangaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView manga_name;
        ImageView image_manga;

        IRecyclerItemClickListener recyclerItemClickListener;

        public void setRecyclerItemClickListener(IRecyclerItemClickListener recyclerItemClickListener) {
            this.recyclerItemClickListener = recyclerItemClickListener;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            manga_name = (TextView) itemView.findViewById(R.id.manga_name);
            image_manga = (ImageView) itemView.findViewById(R.id.image_manga);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            recyclerItemClickListener.onClick(v, getAdapterPosition());

        }
    }
}
