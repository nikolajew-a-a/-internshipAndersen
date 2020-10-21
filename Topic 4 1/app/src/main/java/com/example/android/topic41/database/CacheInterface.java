package com.example.android.topic41.database;

import android.os.AsyncTask;

import com.example.android.topic41.data.news.Article;

import java.util.List;

public interface CacheInterface {
    AsyncTask getArticlesByTheme(String theme);

    AsyncTask refresh(List<Article> articles);
}
