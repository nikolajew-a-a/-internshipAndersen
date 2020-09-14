package com.example.android.topic13;

import android.graphics.drawable.Drawable;

public class Figure {
    private String value;
    private Drawable figure;

    public Figure(String value, Drawable figure){
        this.value = value;
        this.figure  = figure;
    }

    public String getValue() {
        return value;
    }

    public Drawable getFigure() {
        return figure;
    }
}
