package com.client.news_client.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewsHeadlines implements Serializable {
    @SerializedName("source")
    @Expose
    Source source ;

    @SerializedName("author")
    @Expose
    String author = "";
    @SerializedName("title")
    @Expose
    String title = "";
    @SerializedName("description")
    @Expose
    String description = "";
    @SerializedName("url")
    @Expose
    String url = "";
    @SerializedName("urlToImage")
    @Expose
    String urlToImage = "";
    @SerializedName("publishedAt")
    @Expose
    String publishedAt = "";
    @SerializedName("content")
    @Expose
    String content = "";

    @Override
    public String toString() {
        return "NewsHeadlines{" +
                "source=" + source +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public NewsHeadlines(Source source,
                         String author,
                         String title,
                         String description,
                         String url,
                         String urlToImage,
                         String publishedAt,
                         String content) {
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

    public void setSource(Source source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
