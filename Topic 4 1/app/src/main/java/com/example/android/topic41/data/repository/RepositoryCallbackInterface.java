package com.example.android.topic41.data.repository;

import com.example.android.topic41.domain.util.Article;

import java.util.List;

public interface RepositoryCallbackInterface {
    void setArticles(List<Article> articles);
    void setErrorMessage(String message);
}
