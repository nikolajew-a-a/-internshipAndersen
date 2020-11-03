package com.example.android.topic41.data.repository;

import android.util.Log;

import com.example.android.topic41.data.database.CacheCallbackInterface;
import com.example.android.topic41.data.network.NetworkCallbackInterface;
import com.example.android.topic41.domain.util.Article;
import com.example.android.topic41.data.database.CacheInterface;
import com.example.android.topic41.data.network.NetworkInterface;

import java.util.List;


public class ArticlesRepository implements ArticlesRepositoryInterface, CacheCallbackInterface, NetworkCallbackInterface {
    private static ArticlesRepository instance;
    private NetworkInterface network;
    private CacheInterface cache;
    private RepositoryCallbackInterface callBack;

    public static ArticlesRepository getInstance(CacheInterface cache, NetworkInterface network) {
        if (instance == null) {
            instance = new ArticlesRepository(cache, network);
        }
        return instance;
    }

    public ArticlesRepository(CacheInterface cache, NetworkInterface network) {
        this.cache = cache;
        this.network = network;
        cache.setCallback(this);
        network.setCallback(this);
    }

    @Override
    public void setCallback(RepositoryCallbackInterface useCase) {
        this.callBack = useCase;
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
        callBack.setArticles(articles);
    }

    @Override
    public void setArticlesFromNetwork(List<Article> articles) {
        cache.refresh(articles);
        callBack.setArticles(articles);
    }

    @Override
    public void setErrorMessage(String message) {
        Log.i("mLog_REPOSITORY", "setErrorMessage");
        callBack.setErrorMessage(message);
    }
}
