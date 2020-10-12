package com.example.android.topic61.model;

import androidx.annotation.NonNull;

import com.example.android.topic61.presenter.MainPresenter;
import com.example.android.topic61.data.news.News;
import com.example.android.topic61.presenter.MainPresenterInterface;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel implements MainModelInterface{
    private MainPresenterInterface presenter;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Call<News> call;

    private final String KEY = "apiKey";
    private final String KEY_VALUE = "eb3c938655f74099948faa2b250623df";
    private final String BASE_URL = "https://newsapi.org";


    public MainModel(@NonNull MainPresenterInterface presenter) {
        this.presenter = presenter;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    }

    public void setPresenter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    public JsonPlaceHolderApi getJsonPlaceHolderApi() {
        return jsonPlaceHolderApi;
    }

    public void setJsonPlaceHolderApi(JsonPlaceHolderApi jsonPlaceHolderApi) {
        this.jsonPlaceHolderApi = jsonPlaceHolderApi;
    }

    public Call<News> getCall() {
        return call;
    }

    public void setCall(Call<News> call) {
        this.call = call;
    }

    @Override
    public Call<News> createCall(@NonNull Map<String, String> parameters) {
        parameters.put(KEY, KEY_VALUE);
        call = jsonPlaceHolderApi.getNews(parameters);
        return call;
    }


    public void makeRequest() {
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (!response.isSuccessful()) {
                    presenter.setMessage("Code: " + response.code());
                    return;
                }
                News news = response.body();
                presenter.setArticles(news.getArticles());
            }
            @Override
            public void onFailure(Call<News> call, Throwable t) {
                presenter.setMessage(t.getMessage());
            }
        });
    }
}
