package com.example.android.topic61.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.android.topic61.R;
import com.example.android.topic61.data.Theme;
import com.example.android.topic61.model.MainModel;
import com.example.android.topic61.presenter.MainPresenter;
import com.example.android.topic61.data.news.Article;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends MvpAppCompatActivity implements MainView {
    @InjectPresenter
    MainPresenter mainPresenter;

    private RecyclerView recyclerView;
    private List<Article> articles = new ArrayList<>();;
    private Adapter adapter;
    private SwipeRefreshLayout refreshLayout;
    private TextView message;
    boolean isInit = true;


    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initSpinner(R.id.spinner_category, new Theme());

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new Adapter(articles);
        recyclerView.setAdapter(adapter);

        message = findViewById(R.id.text_message);

        refreshLayout =  findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(() -> mainPresenter.getArticles());
    }


    @Override
    public void showNews(@NonNull List<Article> list) {
        adapter.setArticles(list);
        adapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMessage(@NonNull String message) {
        this.message.setText(message);
        this.message.setVisibility(View.VISIBLE);
    }

    @Override
    public void startRefreshing() {
        recyclerView.setVisibility(View.GONE);
        message.setVisibility(View.GONE);
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void stopRefreshing() {
        refreshLayout.setRefreshing(false);
    }


    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
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
                    if (isInit) {
                        mainPresenter.selectThemeFirstTime(key, data);
                        isInit = false;
                    } else {
                        mainPresenter.selectThemeNextTime(key, data);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return spinner;
    }
}
