package com.example.mangareader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mangareader.Adapter.MyReadPageAdapter;

import com.example.mangareader.Model.Chapters;
import com.muddzdev.styleabletoastlibrary.StyleableToast;
import com.wajahatkarim3.easyflipviewpager.BookFlipPageTransformer;

public class ReadMangaActivity extends AppCompatActivity {

    ViewPager viewPager;
    TextView txt_chapter_name;
    View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_manga);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        txt_chapter_name = (TextView) findViewById(R.id.txt_chapter_name);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fetchLinks(Static.chapterSelected);
    }

    private void fetchLinks(Chapters chapters) {
        if (chapters.getLinks() != null) {
            if (chapters.getLinks().size() > 0) {
                MyReadPageAdapter adapter = new MyReadPageAdapter(getBaseContext(), chapters.getLinks());
                viewPager.setAdapter(adapter);

                txt_chapter_name.setText(formatString(Static.chapterSelected.getName()));

                BookFlipPageTransformer bookFlipPageTransformer = new BookFlipPageTransformer();
                bookFlipPageTransformer.setScaleAmountPercent(10f);
                viewPager.setPageTransformer(true, bookFlipPageTransformer);

            }

        }
    }

    public String formatString(String name) {
        StringBuilder finalResult = new StringBuilder(name.length() > 15 ? name.substring(0, 15) + "..." : name);
        return finalResult.toString();
    }
}

