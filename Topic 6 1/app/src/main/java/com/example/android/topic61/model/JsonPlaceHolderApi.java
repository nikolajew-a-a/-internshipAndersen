package com.example.android.topic61.model;


import com.example.android.topic61.data.news.News;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface JsonPlaceHolderApi {
    @GET("/v2/top-headlines")
    Call<News> getNews(@QueryMap Map<String, String> parameters);
}
