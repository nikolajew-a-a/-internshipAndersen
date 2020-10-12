package com.example.android.topic61.data.news;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Article implements Parcelable {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;
    private String content;

    public Article(@NonNull Parcel in) {
        String[] data = new String[9];
        in.readStringArray(data);
        source = new Source(data[0], data[1]);
        author = data[2];
        title = data[3];
        description = data[4];
        url = data[5];
        urlToImage = data[6];
        publishedAt = data[7];
        content = data[8];
    }

    public Article(@NonNull Source source, @NonNull String author, @NonNull String title, @NonNull String description,
                   @NonNull String url, @NonNull String urlToImage, @NonNull String publishedAt, @NonNull String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
    }

    public Source getSource() {
        return source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeStringArray(new String[] {source.getId(), source.getName(), author, title,
                                            description, url, urlToImage, publishedAt, content});
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(@NonNull Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };
}
