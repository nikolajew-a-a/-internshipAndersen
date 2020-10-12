package com.example.android;

import com.example.android.topic61.data.news.News;
import com.example.android.topic61.model.JsonPlaceHolderApi;
import com.example.android.topic61.model.MainModel;
import com.example.android.topic61.presenter.MainPresenter;
import com.example.android.topic61.presenter.MainPresenterInterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainModelTest {

    @Mock
    MainPresenterInterface mainPresenter;

    @Mock
    Call<News> call;

    @Mock
    JsonPlaceHolderApi jsonPlaceHolderApi;

    MainModel mainModel;

    @Before
    public void articlePresenterTestBefore() {
        MockitoAnnotations.openMocks(this);
        mainModel = new MainModel(mainPresenter);
        mainModel.setJsonPlaceHolderApi(jsonPlaceHolderApi);
        mainModel.setCall(call);
    }


    @Test
    public void createCallTest() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("","");
        mainModel.createCall(parameters);
        verify(mainModel.getJsonPlaceHolderApi()).getNews(parameters);
    }

    @Test
    public void makeRequestTest() {
        mainModel.makeRequest();
        verify(mainModel.getCall()).enqueue(Mockito.any());
    }
}
