package com.example.android.topic61.presenter;

import androidx.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.android.topic61.data.Theme;
import com.example.android.topic61.data.news.Article;
import com.example.android.topic61.model.MainModel;
import com.example.android.topic61.model.MainModelInterface;
import com.example.android.topic61.view.MainView;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements MainPresenterInterface {
    private MainModelInterface model;
    private Map<String, String> parameters = new HashMap<>();
    private boolean isFirstRequest = true;

    public MainPresenter() {
        model = new MainModel(this);
    }

    @Override
    public void setMessage(@NonNull String message) {
        getViewState().stopRefreshing();
        getViewState().showMessage(message);
    }

    @Override
    public void setArticles(@NonNull List<Article> articles) {
        getViewState().stopRefreshing();
        List<Article> todayArticles = articlesToday(articles);
        if (todayArticles.size() == 0) {
            getViewState().showMessage("No news. Change parameters.");
        } else {
            getViewState().showNews(articlesToday(articles));
        }
    }

    private List<Article> articlesToday(@NonNull List<Article> allArticles) {
        List<Article> articles = new ArrayList<>();
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);
        for (Article article : allArticles) {
            if(article.getPublishedAt().contains(todayDate)) {
                articles.add(article);
            }
        }
        return articles;
    }

    @Override
    public void getArticles() {
        getViewState().startRefreshing();
        model.getNews(parameters);
    }

    @Override
    public void selectThemeFirstTime(@NonNull String key, @NonNull Theme data) {
        if(isFirstRequest) {
            getViewState().setTitle(key);
            getViewState().startRefreshing();

            String value = data.getRequestParameterValues().get(key);
            parameters.put(data.getRequestParameter(), value);
            model.getNews(parameters);
        }
        isFirstRequest = false;
    }

    @Override
    public void selectThemeNextTime(@NonNull String key, @NonNull Theme data) {
        getViewState().setTitle(key);
        getViewState().startRefreshing();

        String value = data.getRequestParameterValues().get(key);
        parameters.put(data.getRequestParameter(), value);
        model.getNews(parameters);
    }
}
