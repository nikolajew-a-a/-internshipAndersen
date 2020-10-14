package com.example.android.topic41.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import com.example.android.topic41.R;
import com.example.android.topic41.data.Theme;
import com.example.android.topic41.data.news.Article;
import com.example.android.topic41.view.adapter.Adapter;
import com.example.android.topic41.viewmodel.ArticlesViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Article> articles = new ArrayList<>();;
    private Adapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private TextView message;
    private String theme;
    private ArticlesViewModel model;
    boolean isInit = true;


    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSpinner(R.id.spinner_category, new Theme());

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new Adapter(articles);
        recyclerView.setAdapter(adapter);

        refreshLayout =  findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(() -> model.loadArticles(theme, isInit));

        model = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        model.getIsLoadingState().observe(MainActivity.this, loadingState -> {
            refreshLayout.setRefreshing(loadingState);
            recyclerView.setVisibility(model.getIsShowArticlesState().getValue());
            if (!model.getIsShownErrorMessage().getValue()) {
                Toast.makeText(this, model.getErrorMessage().getValue(), Toast.LENGTH_LONG).show();
                model.errorMessageShowed();
            }
        });
        model.getArticles().observe(MainActivity.this, list -> {
            adapter.setArticles(list);
            Log.i("mLog_ACTIVITY", String.valueOf(list.size()));
            for (Article article : list) {
                Log.i("mLog_ACTIVITY", article.getTheme() + " // " + article.getTitle());
            }

        });
    }

    private Spinner initSpinner(int id, @NonNull Theme data) {
        Spinner spinner = findViewById(id);
        ArrayAdapter<String> adapterForBandSpinner = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, data.getRequestParameterKeys());
        adapterForBandSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterForBandSpinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(view != null) {
                    String key = parent.getSelectedItem().toString();
                    theme = data.getRequestParameterValues().get(key);
                    model.loadArticles(theme, isInit);
                    isInit = false;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return spinner;
    }
}
