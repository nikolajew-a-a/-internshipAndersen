package com.example.android.topic41.di.modules;

import androidx.lifecycle.ViewModel;

import com.example.android.topic41.data.database.ArticleDao;
import com.example.android.topic41.data.database.ArticlesDatabase;
import com.example.android.topic41.data.network.Network;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
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
                                    .build();
    }
}
