package com.client.news_client;

import com.client.news_client.Models.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener <NewsHeadlines> {
    void onFetchData(List<NewsHeadlines> list , String message);
    void onError(String message);
}
