package com.example.android.topic41.di.modules;

import android.app.Activity;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.android.topic41.data.repository.ArticlesRepository;
import com.example.android.topic41.di.scope.PerActivity;
import com.example.android.topic41.domain.usecase.SingleUseCase;
import com.example.android.topic41.presentation.view.MainActivity;
import com.example.android.topic41.presentation.viewmodel.ArticlesViewModel;
import com.example.android.topic41.presentation.viewmodel.ViewModelProviderFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public abstract class ArticlesViewModelModule {

    @PerActivity
    @Provides
    static ArticlesViewModel provideArticlesViewModel(SingleUseCase useCase, Activity activity){
        ViewModelStoreOwner owner = (ViewModelStoreOwner) activity;
        return new ViewModelProvider(owner, new ViewModelProviderFactory(useCase))
                .get(ArticlesViewModel.class);
    }
}
