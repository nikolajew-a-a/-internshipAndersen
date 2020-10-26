package com.example.android.model;

import android.app.Application;
import android.content.Context;

import androidx.test.runner.AndroidJUnit4;

import com.example.android.topic41.data.news.Article;
import com.example.android.topic41.data.news.Source;
import com.example.android.topic41.database.CacheInterface;
import com.example.android.topic41.network.NetworkInterface;
import com.example.android.topic41.repository.ArticlesRepository;
import com.example.android.topic41.viewmodel.ArticlesViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class RepositoryTest {

    @Mock
    private NetworkInterface network;

    @Mock
    private CacheInterface cache;

    @Mock
    private ArticlesViewModel viewModel;

    private ArticlesRepository repository;
    private List<Article> testList;

    @Before
    public void articlesViewModelTestBefore() {
        MockitoAnnotations.openMocks(this);
        initData();
        Context context = getApplicationContext();
        repository = new ArticlesRepository(context, viewModel);
        repository.setNetwork(network);
        repository.setCache(cache);
    }

    @Test
    public void loadArticlesTest() {
        String testTheme = "testTheme";
        int cacheRequestCount = 0;
        int networkRequestCount = 0;

        for (int i = 0; i < 10; i++) {
            repository.loadArticles(testTheme, false);
            cacheRequestCount++;
            verify(cache, times(cacheRequestCount)).getArticlesByTheme(testTheme);
            verify(network, times(0)).createCall(testTheme);
            verify(network, times(0)).makeRequest(testTheme);
        }

        for (int i = 0; i < 10; i++) {
            repository.loadArticles(testTheme, true);
            networkRequestCount++;
            verify(cache, times(cacheRequestCount)).getArticlesByTheme(testTheme);
            verify(network, times(networkRequestCount)).createCall(testTheme);
            verify(network, times(networkRequestCount)).makeRequest(testTheme);
        }
    }

    @Test
    public void setArticlesFromCacheTest() {
        repository.setArticlesFromCache(testList);
        verify(viewModel).setArticles(testList);
    }

    @Test
    public void setArticlesFromNetwork() {
        repository.setArticlesFromNetwork(testList);
        verify(cache).refresh(testList);
        verify(viewModel).setArticles(testList);
    }

    @Test
    public void setErrorMessageTest() {
        String testMessage = "testMessage";
        repository.setErrorMessage(testMessage);
        verify(viewModel).setErrorMessage(testMessage);
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
