package com.example.android.topic41.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.example.android.topic41.data.news.Article;

import java.util.List;

public interface ArticlesViewModelInterface {
    void setArticles(List<Article> articles);
    void setErrorMessage(String message);
}
