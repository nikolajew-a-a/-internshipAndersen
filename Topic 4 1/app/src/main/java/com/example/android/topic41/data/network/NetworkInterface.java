package com.example.android.topic41.data.network;



import com.example.android.topic41.domain.util.News;

import retrofit2.Call;

public interface NetworkInterface {
    Call<News> createCall(String theme);

    void makeRequest(String theme);

    void setCallback(NetworkCallbackInterface callback);
}
