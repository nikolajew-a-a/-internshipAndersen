package com.example.android.topic41.repository;

import android.content.Context;
import android.util.Log;

import com.example.android.topic41.data.news.Article;
import com.example.android.topic41.database.Cache;
import com.example.android.topic41.database.CacheInterface;
import com.example.android.topic41.network.Network;
import com.example.android.topic41.network.NetworkInterface;
import com.example.android.topic41.viewmodel.ArticlesViewModel;
import com.example.android.topic41.viewmodel.ArticlesViewModelInterface;

import java.util.List;


public class ArticlesRepository implements ArticlesRepositoryInterface {
    private static ArticlesRepository instance;
    private NetworkInterface network;
    private CacheInterface cache;
    private ArticlesViewModelInterface viewModel;

    public static ArticlesRepository getInstance(Context context, ArticlesViewModel viewModel) {
        if (instance == null) {
            instance = new ArticlesRepository(context, viewModel);
        }
        return instance;
    }

    public ArticlesRepository(Context context, ArticlesViewModel viewModel) {
        this.viewModel = viewModel;
        cache = Cache.getInstance(context, this);
        network = new Network(this);
    }

    @Override
    public void loadArticles(String theme, boolean cacheChecked) {
        if (!cacheChecked) {
            Log.i("mLog_REPOSITORY", "YES. Maybe is data in the cache");
            cache.getArticlesByTheme(theme);
        } else {
            Log.i("mLog_REPOSITORY", "NO. Hear is not data in the cache");
            network.createCall(theme);
            network.makeRequest(theme);
        }
    }

    @Override
    public void setArticlesFromCache(List<Article> articles) {
        viewModel.setArticles(articles);
    }

    @Override
    public void setArticlesFromNetwork(List<Article> articles) {
        cache.refresh(articles);
        viewModel.setArticles(articles);
    }

    @Override
    public void setErrorMessage(String message) {
        viewModel.setErrorMessage(message);
    }


}
