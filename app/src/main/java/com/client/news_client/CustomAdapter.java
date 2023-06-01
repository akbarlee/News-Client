package com.client.news_client;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.client.news_client.Models.NewsApiResponse;
import com.client.news_client.Models.NewsHeadlines;

import com.example.eatfood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlinesList; // make sure the list type is NewsHeadlines
    private SelectListener listener;


    public CustomAdapter(Context context, List<NewsHeadlines> headlinesList, SelectListener listener) {
        this.context = context;
        this.headlinesList = headlinesList;
        this.listener = listener;
    }
    public void ResponseFilling(NewsApiResponse newsApiResponse) { // change the method to take a NewsApiResponse parameter
        List<NewsHeadlines> articles = newsApiResponse.getArticles(); // get the list of articles from the news response
        headlinesList.addAll(articles); // add all the articles to the headlines list
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_headline_list_items , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
     //   ResponseFilling(newsApiResponse);
        NewsHeadlines newsHeadlines = headlinesList.get(position);
        Log.i("onBindViewHolder" , "NewsHeadlines test " + newsHeadlines);
        holder.text_title.setText((newsHeadlines.getTitle()));
        Log.i("onBindViewHolder" , "NewsHeadlines getTitle test " + newsHeadlines.getTitle());
        holder.text_source.setText(newsHeadlines.getSource().getName());

        if(newsHeadlines.getUrlToImage()!=null) {
            Picasso.get().load(newsHeadlines.getUrlToImage()).into(holder.img_headline);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() { // use itemView instead of cardView
            @Override
            public void onClick(View view) {
                listener.OnNewsClicked(newsHeadlines); // use the news headlines object directly
            }
        });
    }

    @Override
    public int getItemCount() {
       return headlinesList.size() ;
    }
}
