package com.example.android.topic41.database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;


import androidx.room.Room;

import com.example.android.topic41.data.news.Article;
import com.example.android.topic41.repository.ArticlesRepositoryInterface;

import java.util.List;


public class Cache implements CacheInterface{
    private ArticlesDatabase database;
    private ArticleDao articleDao;
    private static Cache instance;
    private ArticlesRepositoryInterface repository;
    private static final int TIME_TO_REFRESH = 60_000;

    public static Cache getInstance(Context context, ArticlesRepositoryInterface repository) {
        if (instance == null) {
            instance = new Cache(context, repository);
        }
        return instance;
    }

    private Cache(Context context, ArticlesRepositoryInterface repository) {
        this.repository = repository;
        database = ArticlesDatabase.getInstance(context);
        articleDao = database.articleDao();
    }

    @Override
    public void getArticlesByTheme(String theme) {
        Log.i("mLog_CACHE", "getArticlesByTheme");
        new Cache.LoadArticleAsyncTask(this, articleDao, theme).execute();
    }

    @Override
    public void refresh(List<Article> articles) {
        new RefreshArticleAsyncTask(articleDao).execute(articles);
    }

    public void setArticles(List<Article> articles) {
        repository.setArticlesFromCache(articles);
    }

    public void getArticlesFromNetwork(String theme) {
        repository.loadArticles(theme, true);
    }


    private static class LoadArticleAsyncTask extends AsyncTask<String, Void, Void> {
        private Cache cache;
        private ArticleDao articleDao;
        private String theme;
        private List<Article> articles;
        private LoadArticleAsyncTask(Cache cache, ArticleDao articleDao, String theme) {
            this.cache = cache;
            this.articleDao = articleDao;
            this.theme = theme;
        }
        @Override
        protected Void doInBackground(String... strings) {
            Log.i("mLog_CACHE", "doInBackgroundStart");
            articles = articleDao.getArticlesByTheme(theme);
            Log.i("mLog_CACHE", "doInBackgroundEnd");
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.i("mLog_CACHE", "onPostExecute");
            long currentTime = System.currentTimeMillis();
            if (articles.size() > 0 && (currentTime - articles.get(0).getTime() < TIME_TO_REFRESH)) {
                cache.setArticles(articles);
            } else {
                cache.getArticlesFromNetwork(theme);
            }

        }
    }
    private static class RefreshArticleAsyncTask extends AsyncTask<List<Article>, Void, Void> {
        private ArticleDao articleDao;
        private RefreshArticleAsyncTask(ArticleDao articleDao) {
            this.articleDao = articleDao;
        }

        @Override
        protected Void doInBackground(List<Article>... lists) {
            articleDao.deleteByTheme(lists[0].get(0).getTheme());
            articleDao.insert(lists[0]);
            return null;
        }
    }

}
