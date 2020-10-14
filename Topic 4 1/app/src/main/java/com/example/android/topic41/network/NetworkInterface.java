package com.example.android.topic41.network;



import com.example.android.topic41.data.news.News;

import retrofit2.Call;

public interface NetworkInterface {
    Call<News> createCall(String theme);
    void makeRequest(String theme);
}
