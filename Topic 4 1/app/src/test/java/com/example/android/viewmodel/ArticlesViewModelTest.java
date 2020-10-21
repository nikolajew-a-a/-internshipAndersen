package com.example.android.viewmodel;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.test.runner.AndroidJUnit4;

import com.example.android.topic41.data.news.Article;
import com.example.android.topic41.data.news.Source;
import com.example.android.topic41.repository.ArticlesRepositoryInterface;
import com.example.android.topic41.viewmodel.ArticlesViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(AndroidJUnit4.class)
public class ArticlesViewModelTest {

    @Mock
    ArticlesRepositoryInterface repository;

    ArticlesViewModel viewModel;
    private List<Article> testList;

    @Before
    public void articlesViewModelTestBefore() {
        MockitoAnnotations.openMocks(this);
        Context context = getApplicationContext();
        viewModel = new ArticlesViewModel((Application) context);
        viewModel.setRepository(repository);
    }

    @Test
    public void setArticlesTest() {
        viewModel.setArticles(testList);
        assertEquals(testList, viewModel.getArticles().getValue());
        assertFalse(viewModel.getArticles().hasObservers());
        assertEquals(false, viewModel.getIsLoadingState().getValue());
        assertEquals(true, viewModel.getIsShownErrorMessage().getValue());
    }

    @Test
    public void setErrorMessageTest() {
        String testMessage = "testMessage";
        viewModel.setErrorMessage(testMessage);
        assertEquals(testMessage, viewModel.getErrorMessage().getValue());
        assertFalse(viewModel.getArticles().hasObservers());
        assertEquals(false, viewModel.getIsLoadingState().getValue());
        assertEquals(false, viewModel.getIsShownErrorMessage().getValue());
    }

    @Test
    public void loadArticlesTest() {
        String testMessage = "testTheme";
        int count = 1;
        viewModel.loadArticles(testMessage, true);
        verify(repository, times(count)).loadArticles(testMessage, false);

        for (int i = 0; i < 10; i++) {
            viewModel.loadArticles(testMessage, true);
            verify(repository, times(count)).loadArticles(testMessage, false);
        }


        for (int i = 0; i < 10; i++) {
            count++;
            viewModel.loadArticles(testMessage, false);
            verify(repository, times(count)).loadArticles(testMessage, false);
        }
    }

    public void initData() {
        testList = new ArrayList<>();
        Article article1 = new Article(new Source(null, "1"), "1", "1", "1","1","1","1","1");
        Article article2 = new Article(new Source(null, "2"), "2", "2", "2","2","2","2","2");
        Article article3 = new Article(new Source(null, "3"), "3", "3", "3","3","3","3","3");
        article1.setTheme("theme");
        article2.setTheme("theme");
        article3.setTheme("theme");
        testList.add(article1);
        testList.add(article2);
        testList.add(article3);
    }
}
