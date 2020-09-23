package com.example.android.topic41;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.topic41.news.Article;
import com.squareup.picasso.Picasso;

public class ArticleActivity extends AppCompatActivity {
    private static final String SELECTED_ARTICLE = "selectedArticle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView title = findViewById(R.id.title_article);
        TextView source = findViewById(R.id.source_article);
        TextView description = findViewById(R.id.description_article);
        ImageView glide_image = findViewById(R.id.image_view_glide);
        ImageView picassoImage = findViewById(R.id.image_view_picasso);

        Intent intent = getIntent();
        Article selectedArticle = (Article) intent.getParcelableExtra(SELECTED_ARTICLE);

        title.setText(selectedArticle.getTitle());
        source.setText(selectedArticle.getSource().getName());
        description.setText(selectedArticle.getDescription());

        Glide.with(this)
                .load(selectedArticle.getUrlToImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .fitCenter()
                .into(glide_image);

        Picasso.get()
                .load(selectedArticle.getUrlToImage())
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(picassoImage);
    }
}
