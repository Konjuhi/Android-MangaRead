package com.example.mangareader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mangareader.Adapter.MyChapterAdapter;

import com.example.mangareader.Model.Chapters;
import com.example.mangareader.Model.Manga;

import java.util.List;

public class ChaptersActivity extends AppCompatActivity {

    RecyclerView recycler_chapter;
    TextView txt_chapter_name;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapters);

        //View
        recycler_chapter = (RecyclerView) findViewById(R.id.recycler_chapter);
        txt_chapter_name = (TextView) findViewById(R.id.txt_chapter_name);
        recycler_chapter.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        //recycler_chapter.setLayoutManager(new LinearLayoutManager(this));
        recycler_chapter.setLayoutManager(layoutManager);
        recycler_chapter.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(Static.mangaSelected.getName());

        //Set icon
        toolbar.setNavigationIcon(R.drawable.ic_keyboard1_arrow_left_black_40dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }


        });

        fetchChapter(Static.mangaSelected);
    }

    private void fetchChapter(Manga mangaSelected)
    {
        Static.chapterList = mangaSelected.getChapters();
        recycler_chapter.setAdapter(new MyChapterAdapter(this, mangaSelected.getChapters()));
        txt_chapter_name.setText(new StringBuilder("CHAPTERS (").append(mangaSelected.getChapters().size()).append(")"));
    }
}