package com.example.android.topic41.data.database;

import android.os.AsyncTask;
import android.util.Log;


import com.example.android.topic41.domain.util.Article;

import java.util.List;


public class Cache implements CacheInterface{
    private ArticlesDatabase database;
    private ArticleDao articleDao;
    private static Cache instance;
    private CacheCallbackInterface callback;
    private static final int TIME_TO_REFRESH = 60_000;

    public static Cache getInstance(ArticlesDatabase database, ArticleDao articleDao) {
        if (instance == null) {
            instance = new Cache(database, articleDao);
        }
        return instance;
    }

    public Cache(ArticlesDatabase database, ArticleDao articleDao) {
        this.database = database;
        this.articleDao = articleDao;
    }

    @Override
    public void setCallback(CacheCallbackInterface callback) {
        this.callback = callback;
    }


    @Override
    public AsyncTask getArticlesByTheme(String theme) {
        Log.i("mLog_CACHE", "getArticlesByTheme");
        return new Cache.LoadArticleAsyncTask(this, articleDao, theme).execute();
    }

    @Override
    public AsyncTask refresh(List<Article> articles) {
        return new RefreshArticleAsyncTask(articleDao).execute(articles);
    }

    public void setArticles(List<Article> articles) {
        callback.setArticlesFromCache(articles);
    }

    public void getArticlesFromNetwork(String theme) {
        callback.loadArticles(theme, true);
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
