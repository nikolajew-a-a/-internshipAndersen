package com.example.android.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import com.example.android.topic41.data.news.Article;
import com.example.android.topic41.data.news.Source;
import com.example.android.topic41.database.ArticleDao;
import com.example.android.topic41.database.ArticlesDatabase;
import com.example.android.topic41.database.Cache;
import com.example.android.topic41.network.Network;
import com.example.android.topic41.repository.ArticlesRepositoryInterface;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class CacheTest {

    @Mock
    private ArticlesRepositoryInterface repository;

    private ArticlesDatabase database;
    private ArticleDao articleDao;

    private Cache cache;
    private List<Article> relevantTestList;
    private List<Article> oldTestList;
    private static final int TIME_TO_REFRESH = 60_000;

    @Before
    public void cacheTestBefore() {
        MockitoAnnotations.openMocks(this);
        Context context = ApplicationProvider.getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, ArticlesDatabase.class).build();
        articleDao = database.articleDao();
        cache = Cache.getInstance(context, repository);
        cache.setDatabase(database);
        cache.setArticleDao(articleDao);
        initData();
    }

    @Test
    public void cacheNotEmptyTest() {
        new Thread(() -> {
            AsyncTask refreshTask = cache.refresh(relevantTestList);
            try {
                refreshTask.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            AsyncTask loadTask = cache.getArticlesByTheme("theme");
            try {
                loadTask.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            verify(repository).setArticlesFromCache(relevantTestList);
        });
    }

    @Test
    public void cacheEmptyTest() {
        new Thread(() -> {
            AsyncTask refreshTask = cache.refresh(oldTestList);
            try {
                refreshTask.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String testTheme  = "theme";
            AsyncTask loadTask = cache.getArticlesByTheme(testTheme);
            try {
                loadTask.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            verify(repository).loadArticles(testTheme, true);
        });
    }

    @After
    public void cacheTestAfter() {
        database.close();
    }

    public void initData() {
        relevantTestList = new ArrayList<>();
        oldTestList = new ArrayList<>();
        long currentTime = System.currentTimeMillis();
        Article article1 = new Article(new Source(null, "1"), "1", "1", "1","1","1","1","1");
        Article article2 = new Article(new Source(null, "2"), "2", "2", "2","2","2","2","2");
        Article article3 = new Article(new Source(null, "3"), "3", "3", "3","3","3","3","3");
        article1.setTheme("theme");
        article2.setTheme("theme");
        article3.setTheme("theme");

        article1.setTime(currentTime);
        article2.setTime(currentTime);
        article3.setTime(currentTime);
        relevantTestList.add(article1);
        relevantTestList.add(article2);
        relevantTestList.add(article3);

        article1.setTime(currentTime - TIME_TO_REFRESH - 1000);
        article2.setTime(currentTime - TIME_TO_REFRESH - 1000);
        article3.setTime(currentTime - TIME_TO_REFRESH - 1000);
        oldTestList.add(article1);
        oldTestList.add(article2);
        oldTestList.add(article3);
    }
}
