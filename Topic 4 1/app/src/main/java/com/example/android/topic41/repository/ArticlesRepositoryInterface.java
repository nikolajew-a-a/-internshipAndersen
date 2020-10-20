package com.example.android.topic41.repository;

import com.example.android.topic41.data.news.Article;

import java.util.List;

public interface ArticlesRepositoryInterface {
    void setArticlesFromCache(List<Article> articles);

    void setArticlesFromNetwork(List<Article> articles);

    void setErrorMessage(String message);

    void loadArticles(String theme, boolean cacheChecked);
}
