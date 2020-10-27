package com.example.android.topic41.data.repository;

public interface ArticlesRepositoryInterface {
    void loadArticles(String theme, boolean cacheChecked);

    void setCallback(RepositoryCallbackInterface viewModel);
}
