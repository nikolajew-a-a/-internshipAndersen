package com.example.android.topic41.network;

import android.util.Log;

import androidx.annotation.NonNull;


import com.example.android.topic41.data.news.Article;
import com.example.android.topic41.data.news.News;
import com.example.android.topic41.repository.ArticlesRepositoryInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Network implements NetworkInterface {
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Call<News> call;

    private ArticlesRepositoryInterface repository;
    private Map<String, String> parameters = new HashMap<>();

    private final String KEY = "apiKey";
    private final String KEY_VALUE = "eb3c938655f74099948faa2b250623df";
    private final String BASE_URL = "https://newsapi.org";


    public Network(ArticlesRepositoryInterface repository) {
        this.repository = repository;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    public void setJsonPlaceHolderApi(JsonPlaceHolderApi jsonPlaceHolderApi) {
        this.jsonPlaceHolderApi = jsonPlaceHolderApi;
    }

    public void setCall(Call<News> call) {
        this.call = call;
    }

    @Override
    public Call<News> createCall(@NonNull String theme) {
        parameters.put(KEY, KEY_VALUE);
        parameters.put("q", theme);
        call = jsonPlaceHolderApi.getNews(parameters);
        return call;
    }

    @Override
    public void makeRequest(String theme) {
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                long currentTime = System.currentTimeMillis();
                Log.i("mLog", "Удачный запрос");
                if (!response.isSuccessful()) {
                    repository.setErrorMessage("Code: " + response.code());
                    Log.i("mLog_NETWORK", "Code: " + response.code());
                    return;
                }
                News news = response.body();
                List<Article> articles = news.getArticles();
                if(articles.size() == 0) {
                    repository.setErrorMessage("No news. Change parameters");
                    Log.i("mLog_NETWORK", "No news. Change parameters");
                    return;
                }
                for (Article article : articles) {
                    Log.i("mLog_NETWORK", article.getTitle());
                    article.setTheme(theme);
                    article.setTime(currentTime);
                }
                repository.setArticlesFromNetwork(articles);
            }
            @Override
            public void onFailure(Call<News> call, Throwable t) {
                repository.setErrorMessage(t.getMessage());
                Log.i("mLog_NETWORK", t.getMessage());
            }
        });
    }
}
