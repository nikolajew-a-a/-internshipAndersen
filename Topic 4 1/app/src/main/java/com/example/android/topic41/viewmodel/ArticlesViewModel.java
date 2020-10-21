package com.example.android.topic41.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.topic41.data.news.Article;
import com.example.android.topic41.repository.ArticlesRepository;
import com.example.android.topic41.repository.ArticlesRepositoryInterface;

import java.util.ArrayList;
import java.util.List;


public class ArticlesViewModel extends AndroidViewModel implements ArticlesViewModelInterface{
    private ArticlesRepositoryInterface repository;
    private boolean isFirstRequest = true;

    private MutableLiveData<List<Article>> temp = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingState = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> isShowedErrorMessage = new MutableLiveData<>(true);

    public ArticlesViewModel(Application application) {
        super(application);
        repository = ArticlesRepository.getInstance(application, this);
    }

    public void setRepository(ArticlesRepositoryInterface repository) {
        this.repository = repository;
    }

    public void loadArticles(String theme, boolean isSpinnerInit) {
        if(isFirstRequest || !isSpinnerInit) {
            isLoadingState.setValue(true);
            repository.loadArticles(theme, false);
            isFirstRequest = false;
        }
    }

    public void errorMessageShowed() {
        isShowedErrorMessage.setValue(true);
    }

    public LiveData<List<Article>> getArticles() {
        return temp;
    }
    public LiveData<Boolean> getIsLoadingState() {
        return isLoadingState;
    }
    public LiveData<Boolean> getIsShownErrorMessage() {
        return isShowedErrorMessage;
    }
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void setArticles(List<Article> articles) {
        temp.setValue(articles);
        isLoadingState.setValue(false);
    }

    @Override
    public void setErrorMessage(String message) {
        errorMessage.setValue(message);
        temp.setValue(new ArrayList<>());
        isShowedErrorMessage.setValue(false);
        isLoadingState.setValue(false);
    }
}


