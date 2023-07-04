package com.client.news_client;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.client.news_client.Models.NewsHeadlines;

import com.client.news_client.Models.Source;
import com.example.eatfood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlinesList;
    private SelectListener listener;



    public CustomAdapter(Context context, List<NewsHeadlines> headlinesList, SelectListener listener ) {
        this.context = context;
        this.headlinesList = headlinesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_headline_list_items , parent , false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Log.i("CustomAdapter", "Holder " + holder);
        Log.i("CustomAdapter", "Position " + position);


            NewsHeadlines headlines = headlinesList.get(position);
            holder.text_title.setText((headlines.getTitle()));
       

            Log.i("For test", "Source " + headlinesList.toString());


                holder.text_source.setText(headlines.getSource().getName());

                Picasso.get().load(headlinesList.get(position).getUrlToImage()).into(holder.img_headline);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnNewsClicked(headlines);
                }
            });
        }

    @Override
    public int getItemCount() {
       return headlinesList.size() ;
    }
}
