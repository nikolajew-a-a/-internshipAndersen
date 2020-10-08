package com.example.android.topic61.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.android.topic61.data.news.Article;

import java.util.List;


@StateStrategyType(value = AddToEndStrategy.class)
public interface MainView extends MvpView {

    void showNews(List<Article> list);

    void showMessage(String message);

    @StateStrategyType(SingleStateStrategy.class)
    void setTitle(String string);

    @StateStrategyType(SkipStrategy.class)
    void startRefreshing();

    @StateStrategyType(SkipStrategy.class)
    void stopRefreshing();
}
