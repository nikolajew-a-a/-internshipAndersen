package com.example.android.topic41.data.network;

import com.example.android.topic41.domain.util.Article;

import java.util.List;

public interface NetworkCallbackInterface {
    void setArticlesFromNetwork(List<Article> articles);

    void setErrorMessage(String message);
}
