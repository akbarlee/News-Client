package com.client.news_client.Models;

import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NewsApiResponse implements Serializable {
    @SerializedName("response_status")
    @Expose
    String status;
    @SerializedName("response_total_results")
    @Expose
     int totalResults;

    List<NewsHeadlines> articles;


    @Override
    public String toString() {
        return "NewsApiResponse{" +
                "status='" + status + '\'' +
                ", totalResults=" + totalResults +
                ", articles=" + articles +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsHeadlines> getArticles() {

        return articles;

    }

    public void setArticles(List<NewsHeadlines> articles) {
        this.articles = articles;
    }
}
