package com.example.android.topic41.domain.usecase;

import com.example.android.topic41.data.repository.RepositoryCallbackInterface;

public interface SingleUseCaseInterface {
    void loadArticles(String theme, boolean cacheChecked);

    void setCallback(SingleUseCaseCallbackInterface viewModel);
}
