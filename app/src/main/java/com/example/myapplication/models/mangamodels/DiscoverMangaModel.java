package com.example.myapplication.models.mangamodels;

import lombok.Data;

@Data
public class DiscoverMangaModel {
    private String mangaTitle;
    private String mangaType;
    private String mangaThumb;
    private String mangaLatestChapter;
    private String mangaLatestChapterText;
    private String mangaRating;
    private String mangaURL;
    private boolean mangaStatus;
}
