package com.example.android.topic61.model;

import com.example.android.topic61.data.news.News;

import java.util.Map;

import retrofit2.Call;

public interface MainModelInterface {
    Call<News> createCall(Map<String, String> parameters);
    void makeRequest();
}
