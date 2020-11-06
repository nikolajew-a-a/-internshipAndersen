package com.example.android.topic41.data.network;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;


import com.example.android.topic41.domain.util.Article;
import com.example.android.topic41.domain.util.News;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.Retrofit;

public class Network implements NetworkInterface {
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Single<List<Article>> observable;
    private Map<String, String> parameters = new HashMap<>();

    private final String KEY = "apiKey";
    private final String KEY_VALUE = "eb3c938655f74099948faa2b250623df";



    public Network(Retrofit retrofit) {
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    @Override
    public Single<List<Article>> createObservable(@NonNull String theme) {
        parameters.put(KEY, KEY_VALUE);
        parameters.put("q", theme);
        observable = jsonPlaceHolderApi.getNews(parameters).map(news -> {
            List<Article> articles = news.getArticles();
            Log.i("mLog_CACHE", "" + articles.size());
            return articles;
        });
        return observable;
    }
}
