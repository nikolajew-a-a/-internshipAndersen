package com.example.android;


import com.example.android.topic61.data.Theme;
import com.example.android.topic61.data.news.Article;
import com.example.android.topic61.data.news.Source;
import com.example.android.topic61.model.MainModel;
import com.example.android.topic61.model.MainModelInterface;
import com.example.android.topic61.presenter.MainPresenter;
import com.example.android.topic61.view.MainView$$State;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Mock;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainView$$State mainViewState;

    @Mock
    MainModelInterface mainModel;

    private MainPresenter mainPresenter;

    @Before
    public void mainPresenterTestBefore() {
        MockitoAnnotations.openMocks(this);
        mainPresenter = new MainPresenter();
        mainPresenter.setViewState(mainViewState);
        mainPresenter.setModel(mainModel);
    }

    @Test
    public void setMessageTest() {
        String message = "";
        mainPresenter.setMessage(message);
        verify(mainViewState).stopRefreshing();
        verify(mainViewState).showMessage(message);
    }

    @Test
    public void setArticlesTest() {
        List<Article> articleList  = new ArrayList<>();
        mainPresenter.setArticles(articleList);
        verify(mainViewState, times(1)).stopRefreshing();
        verify(mainViewState, times(1)).showMessage(Mockito.any());
        verify(mainViewState, times(0)).showNews(articleList);

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);
        articleList.add(new Article(new Source("", ""),
                "", "", "", "", "",
                todayDate, ""));
        mainPresenter.setArticles(articleList);
        verify(mainViewState, times(2)).stopRefreshing();
        verify(mainViewState, times(1)).showMessage(Mockito.any());
        verify(mainViewState, times(1)).showNews(articleList);
    }

    @Test
    public void getArticlesTest() {
        mainPresenter.getArticles();
        verify(mainViewState).startRefreshing();
        verify(mainPresenter.getModel()).createCall(Mockito.any());
        verify(mainPresenter.getModel()).makeRequest();
    }

    @Test
    public void selectThemeFirstTimeTest() {
        String key = "";
        Theme data = new Theme();
        for (int i = 0; i < 10; i++) {
            mainPresenter.selectThemeFirstTime(key, data);
            verify(mainViewState, times(1)).setTitle(key);
            verify(mainViewState, times(1)).startRefreshing();
            verify(mainPresenter.getModel(), times(1)).createCall(Mockito.any());
            verify(mainPresenter.getModel(), times(1)).makeRequest();
        }
    }

    @Test
    public void selectThemeNextTimeTest() {
        String key = "";
        Theme data = new Theme();
        for (int i = 0; i < 10; i++) {
            verify(mainViewState, times(i)).setTitle(key);
            verify(mainViewState, times(i)).startRefreshing();
            verify(mainPresenter.getModel(), times(i)).createCall(Mockito.any());
            verify(mainPresenter.getModel(), times(i)).makeRequest();
            mainPresenter.selectThemeNextTime(key, data);
        }
    }
}
