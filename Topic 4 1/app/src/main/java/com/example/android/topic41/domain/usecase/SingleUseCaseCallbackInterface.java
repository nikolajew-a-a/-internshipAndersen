package com.example.android.topic41.domain.usecase;

import com.example.android.topic41.domain.util.Article;

import java.util.List;

public interface SingleUseCaseCallbackInterface {
    void setArticles(List<Article> articles);
    void setErrorMessage(String message);
}
