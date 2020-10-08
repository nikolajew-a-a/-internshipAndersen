package com.example.android.topic61.data.news;

import androidx.annotation.NonNull;

public class Source {
    private String id;
    private String name;

    public Source(@NonNull String id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
