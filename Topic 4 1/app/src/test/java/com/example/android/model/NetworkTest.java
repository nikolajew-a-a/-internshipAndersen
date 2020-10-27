package com.example.android.model;

import androidx.test.runner.AndroidJUnit4;

import com.example.android.topic41.domain.util.News;
import com.example.android.topic41.data.network.JsonPlaceHolderApi;
import com.example.android.topic41.data.network.Network;
import com.example.android.topic41.data.repository.ArticlesRepositoryInterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class NetworkTest {

    @Mock
    private ArticlesRepositoryInterface repository;

    @Mock
    private Call<News> call;

    @Mock
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    private Network network;

    @Before
    public void articlePresenterTestBefore() {
        MockitoAnnotations.openMocks(this);
        network = new Network(repository);
        network.setJsonPlaceHolderApi(jsonPlaceHolderApi);
        network.setCall(call);
    }


    @Test
    public void createCallTest() {
        String testTheme = "testTheme";
        Map<String, String> testParameters = new HashMap<>();
        testParameters.put("theme","testTheme");
        network.createCall(testTheme);
        verify(jsonPlaceHolderApi).getNews(Mockito.any());
    }

    @Test
    public void makeRequestTest() {
        String testTheme = "testTheme";
        network.makeRequest(testTheme);
        verify(call).enqueue(Mockito.any());
    }
}
