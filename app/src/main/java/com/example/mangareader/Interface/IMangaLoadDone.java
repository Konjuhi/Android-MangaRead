package com.example.mangareader.Interface;

import com.example.mangareader.Model.Manga;

import java.util.List;

public interface IMangaLoadDone {
    void onMangaLoadDoneListener(List<Manga> mangaList);
}
