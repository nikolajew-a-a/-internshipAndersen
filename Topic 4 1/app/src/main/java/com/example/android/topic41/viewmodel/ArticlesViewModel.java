package com.example.android.topic41.viewmodel;

import android.app.Application;
import android.view.View;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android.topic41.data.news.Article;
import com.example.android.topic41.repository.ArticlesRepository;

import java.util.List;


public class ArticlesViewModel extends AndroidViewModel implements ArticlesViewModelInterface{
    private ArticlesRepository repository;
    private boolean isFirstRequest = true;

    private MutableLiveData<List<Article>> temp = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoadingState = new MutableLiveData<>();
    private MutableLiveData<Integer> isShowArticlesState = new MutableLiveData<>();
    private MutableLiveData<Boolean> isShowedErrorMessage = new MutableLiveData<>();

    public ArticlesViewModel(Application application) {
        super(application);
        repository = ArticlesRepository.getInstance(application, this);
        setStateLoading();
    }

    public void loadArticles(String theme, boolean isSpinnerInit) {
            if(isFirstRequest || !isSpinnerInit) {
            setStateLoading();
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
    public LiveData<Integer> getIsShowArticlesState() {
        return isShowArticlesState;
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
        setStateShowArticles();
    }

    @Override
    public void setErrorMessage(String message) {
        errorMessage.setValue(message);
        setStateShowErrorMessage();
    }

    private void setStateLoading() {
        isShowArticlesState.setValue(View.GONE);
        isShowedErrorMessage.setValue(true);
        isLoadingState.setValue(true);
    }

    private void setStateShowArticles() {
        isShowArticlesState.setValue(View.VISIBLE);
        isShowedErrorMessage.setValue(true);
        isLoadingState.setValue(false);
    }

    private void setStateShowErrorMessage() {
        isShowArticlesState.setValue(View.GONE);
        isShowedErrorMessage.setValue(false);
        isLoadingState.setValue(false);
    }
}


