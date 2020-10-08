package com.example.android.topic61.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.android.topic61.data.news.Article;


public interface ArticleView extends MvpView {
    void showArticle(Article article);
}
