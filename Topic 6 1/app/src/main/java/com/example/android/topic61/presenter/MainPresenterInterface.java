package com.example.android.topic61.presenter;

import com.example.android.topic61.data.Theme;
import com.example.android.topic61.model.MainModel;
import com.example.android.topic61.data.news.Article;

import java.util.List;

public interface MainPresenterInterface {

        void setMessage(String message);

        void setArticles(List<Article> articles);

        void getArticles();

        void selectThemeFirstTime(String key, Theme data);

        void selectThemeNextTime(String key, Theme data);
}
