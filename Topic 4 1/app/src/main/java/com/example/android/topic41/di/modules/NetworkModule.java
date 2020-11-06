package com.example.android.topic41.di.modules;

import com.example.android.topic41.data.network.Network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class NetworkModule {
    private static String BASE_URL = "https://newsapi.org";

    @Singleton
    @Provides
    static Network provideNetwork(Retrofit retrofit){
        return new Network(retrofit);
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(){
        return new Retrofit.Builder()
                                    .baseUrl(BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                    .build();
    }
}
