package com.example.android.topic41.domain.usecase;

import android.util.Log;

import com.example.android.topic41.data.repository.ArticlesRepositoryInterface;
import com.example.android.topic41.data.repository.RepositoryCallbackInterface;
import com.example.android.topic41.domain.util.Article;

import java.util.List;

public class SingleUseCase implements RepositoryCallbackInterface, SingleUseCaseInterface {
    private ArticlesRepositoryInterface repository;
    private SingleUseCaseCallbackInterface callBack;

    public SingleUseCase(ArticlesRepositoryInterface repository) {
        Log.i("mLog_USECASE", "I am new again.");
        this.repository = repository;
        repository.setCallback(this);
    }

    @Override
    public void setArticles(List<Article> articles) {
        callBack.setArticles(articles);
    }

    @Override
    public void setErrorMessage(String message) {
        callBack.setErrorMessage(message);
    }

    @Override
    public void loadArticles(String theme, boolean cacheChecked) {
        repository.loadArticles(theme, cacheChecked);
    }

    @Override
    public void setCallback(SingleUseCaseCallbackInterface viewModel) {
        this.callBack = viewModel;
    }
}
