package com.example.android.topic61.data.news;

import java.util.List;

public class News {
    private String status;
    private String totalResults;
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }
}
