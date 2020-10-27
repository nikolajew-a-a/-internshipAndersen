package com.example.android.topic41.data.database;

import android.os.AsyncTask;

import com.example.android.topic41.domain.util.Article;

import java.util.List;

public interface CacheInterface {
    AsyncTask getArticlesByTheme(String theme);

    AsyncTask refresh(List<Article> articles);

    void setCallback(CacheCallbackInterface callback);
}
