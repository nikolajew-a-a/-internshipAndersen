package com.example.android.topic41.data.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.example.android.topic41.domain.util.Article;

import java.util.List;


@Dao
public interface ArticleDao {
    @Query("SELECT * FROM articles_table WHERE theme = :topic  ORDER BY publishedAt DESC")
    List<Article> getArticlesByTheme(String topic);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<Article> articles);

    @Query("DELETE FROM articles_table WHERE theme = :topic")
    void deleteByTheme(String topic);
}
