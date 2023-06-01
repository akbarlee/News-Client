package com.client.news_client;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.client.news_client.Models.NewsApiResponse;
import com.client.news_client.Models.NewsHeadlines;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager  {
    Context context;
    CustomAdapter customAdapter;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.6:8080/")
            .addConverterFactory(GsonConverterFactory.create( new Gson()))
            .build();
    // Retrofitin logunu log icinde gormek uchun
    HttpUrl baseUrl = retrofit.baseUrl();
    String baseUrlString = baseUrl.toString();

    NewsApiResponse newsApiResponse = new NewsApiResponse ();


    public void getNewsHeadlines(OnFetchDataListener listener , String category , String query) {

        Log.i("getNewsHeadlines" , "Retrofit url ugurlu "+ baseUrlString);
   CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);  // http://localhost:8080/news -> callNewsApi

   Call<List<NewsApiResponse>> call = callNewsApi.callHeadlines("us" , category , query); // getString metodu ile strings.xml icindeki api keyi cagirdim
        Log.i("getNewsHeadlines", "Calling the callNewsApi.callHeadlines() method with the following parameters:");

        Log.i("getNewsHeadlines" , "Category "+ category);
        Log.i("getNewsHeadlines" , "Query "+ query);
        try {
            Log.i("getNewsHeadlines" , "try method test ");
            call.enqueue(new Callback<List<NewsApiResponse>>() {

                @Override
                public void onResponse(Call<List<NewsApiResponse>> call, Response<List<NewsApiResponse>> response) {
                    Log.i("getNewsHeadlines", "Call  " + call);
                    // Log the status code of the response.
                    Log.i("getNewsHeadlines", "Response status code: " + response.code());

                    // Log the headers of the response.
                    for (String header : response.headers().names()) {
                        Log.i("getNewsHeadlines", "Response header: " + header + " = " + response.headers().get(header));
                    }

                    // Log the body of the response.
                    if (response.body() != null) {
                        Log.i("getNewsHeadlines", "Response body: " + response.body());
                    }


                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    } else {

                        try {
                            listener.onFetchData(response.body(), response.message());
                        } catch (Exception e) {
                            listener.getClass();
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<NewsApiResponse>> call, Throwable t) {
                    listener.onError("Request Failed");
                }


               });
           }
           catch (Exception e) {
               e.printStackTrace();
           }
     }
    public RequestManager(Context context) {
        this.context = context;
    }

    public  interface  CallNewsApi {

        @GET ("news") // GET  Burdaki top-headlinesdi
        Call<List<NewsApiResponse>> callHeadlines(
               @Query("country") String country ,
               @Query("category") String category ,
               @Query("q") String q


       );


    }
}
