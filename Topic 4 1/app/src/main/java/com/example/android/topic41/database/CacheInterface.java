package com.example.android.topic41.database;

import com.example.android.topic41.data.news.Article;

import java.util.List;

public interface CacheInterface {
    void getArticlesByTheme(String theme);

    void refresh(List<Article> articles);
}
