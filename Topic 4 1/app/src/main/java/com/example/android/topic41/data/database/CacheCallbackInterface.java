package com.example.android.topic41.data.database;

import com.example.android.topic41.domain.util.Article;

import java.util.List;

public interface CacheCallbackInterface {
    void setArticlesFromCache(List<Article> articles);

    void setErrorMessage(String message);

    void loadArticles(String theme, boolean cacheChecked);
}
