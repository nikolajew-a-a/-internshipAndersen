package com.example.android.topic41;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.topic41.data.Theme;
import com.example.android.topic41.news.Article;
import com.example.android.topic41.news.News;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TextView textViewResult;
    private List<Article> articles = new ArrayList<>();;
    private Adapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private Map<String, String> parameters;

    private final String KEY = "apiKey";
    private final String KEY_VALUE = "eb3c938655f74099948faa2b250623df";
    private final String BASE_URL = "https://newsapi.org";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new Adapter(articles);
        recyclerView.setAdapter(adapter);

        refreshLayout =  findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(() -> getNews(jsonPlaceHolderApi));

        textViewResult = findViewById(R.id.text_view_result);

        Spinner themeSpinner = initSpinner(R.id.spinner_category, new Theme());

        parameters = new HashMap<>();
        parameters.put(KEY, KEY_VALUE);
    }


    private Spinner initSpinner(int id, @NonNull Theme data) {
        Spinner spinner = findViewById(id);
        ArrayAdapter<String> adapterForBandSpinner = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, data.getRequestParameterKeys());
        adapterForBandSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterForBandSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            boolean isInit = true;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String key = parent.getSelectedItem().toString();
                String value = data.getRequestParameterValues().get(key);
                parameters.put(data.getRequestParameter(), value);
                getNews(jsonPlaceHolderApi);
                getSupportActionBar().setTitle(key);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return spinner;
    }


    private void getNews(@NonNull JsonPlaceHolderApi jsonPlaceHolderApi) {
        refreshLayout.setRefreshing(true);
        Call<News> call = jsonPlaceHolderApi.getNews(parameters);
        recyclerView.setVisibility(View.GONE);
        textViewResult.setVisibility(View.GONE);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                refreshLayout.setRefreshing(true);
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    textViewResult.setVisibility(View.VISIBLE);
                    refreshLayout.setRefreshing(false);
                    return;
                }
                News news = response.body();
                articles = articlesToday(news.getArticles());

                if (articles.size() == 0) {
                    textViewResult.setText("No news. Change parameters.");
                    textViewResult.setVisibility(View.VISIBLE);
                    refreshLayout.setRefreshing(false);
                    return;
                }
                adapter.setArticles(articles);
                adapter.notifyDataSetChanged();
                refreshLayout.setRefreshing(false);
                recyclerView.setVisibility(View.VISIBLE);
            }
            @Override
            public void onFailure(Call<News> call, Throwable t) {
                textViewResult.setText(t.getMessage());
                textViewResult.setVisibility(View.VISIBLE);
                refreshLayout.setRefreshing(false);
            }
        });

    }


    private List<Article> articlesToday(@NonNull List<Article> allArticles) {
        List<Article> articles = new ArrayList<>();
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(currentDate);
        for (Article article : allArticles) {
            if(article.getPublishedAt().contains(todayDate)) {
                articles.add(article);
            }
        }
        return articles;
    }
}
