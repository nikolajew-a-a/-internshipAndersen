package com.example.android.topic61.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.bumptech.glide.Glide;
import com.example.android.topic61.R;
import com.example.android.topic61.data.news.Article;
import com.squareup.picasso.Picasso;


public class ArticleActivity extends MvpAppCompatActivity implements ArticleView {
    private ImageView glide_image;
    private ImageView picassoImage;
    private TextView title;
    private TextView source;
    private TextView description;

    private static final String SELECTED_ARTICLE = "selectedArticle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        glide_image = findViewById(R.id.image_view_glide);
        picassoImage = findViewById(R.id.image_view_picasso);
        title = findViewById(R.id.title_article);
        source = findViewById(R.id.source_article);
        description = findViewById(R.id.description_article);

        Intent intent = getIntent();
        Article article = (Article) intent.getParcelableExtra(SELECTED_ARTICLE);
        this.showArticle(article);
    }



    @Override
    public void showArticle(@NonNull Article article) {
        title.setText(article.getTitle());
        source.setText(article.getSource().getName());
        description.setText(article.getDescription());

        Glide.with(this)
                .load(article.getUrlToImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .fitCenter()
                .into(glide_image);

        Picasso.get()
                .load(article.getUrlToImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(picassoImage);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
