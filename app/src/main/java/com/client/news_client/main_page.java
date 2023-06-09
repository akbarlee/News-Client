package com.client.news_client;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.client.news_client.Models.NewsApiResponse;
import com.client.news_client.Models.NewsHeadlines;
import com.example.eatfood.R;

import java.util.List;


public class main_page extends MainActivity implements SelectListener , View.OnClickListener {
    private Toolbar mToolbar;
    MenuItem menuItem , logout;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button b1,b2,b3,b4,b5,b6,b7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);


        dialog = new ProgressDialog(this);
        dialog.setTitle("Yeni xəbərlər yüklənir...");
        dialog.show();

        b1=findViewById(R.id.btn_1);
        b1.setOnClickListener(this);
        b2=findViewById(R.id.btn_2);
        b2.setOnClickListener(this);
        b3=findViewById(R.id.btn_3);
        b3.setOnClickListener(this);
        b4=findViewById(R.id.btn_4);
        b4.setOnClickListener(this);
        b5=findViewById(R.id.btn_5);
        b5.setOnClickListener(this);
        b6=findViewById(R.id.btn_6);
        b6.setOnClickListener(this);
        b7=findViewById(R.id.btn_7);
        b7.setOnClickListener(this);


        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, "general",null);
        Log.i("Request Manager" , "manager " + manager);

    }
    private  final OnFetchDataListener<NewsHeadlines> listener = new OnFetchDataListener<NewsHeadlines>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {


            if(list.isEmpty()) {
                Toast.makeText(main_page.this, "Məlumat tapılmadı", Toast.LENGTH_SHORT).show();
                // log the empty list case
                Log.w("onFetchData", "list is empty");
            }
            else {

                Log.i("onFetchData", "News list: " + list); // Source Null
                Log.i("onFetchData", "list size: " + list.size());

             showNews(list);
                dialog.dismiss();

            }
        }


        @Override
        public void onError(String message) {
            Toast.makeText(main_page.this, "Xəta yarandı!!!", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewsHeadlines> list) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this ,1));
        adapter = new CustomAdapter(this , list , this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
      startActivity(new Intent(main_page.this , DetailsActivity.class)
              .putExtra("data",headlines));
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String category = button.getText().toString();
        dialog.setTitle(category+ " xəbərləri yüklənir...");
        dialog.show();
        RequestManager manager = new RequestManager(this);
        manager.getNewsHeadlines(listener, category,null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu , menu);
        logout = menu.findItem(R.id.logout);

        logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startActivity(new Intent(main_page.this,MainActivity.class));
                finish();
                return true;
            }
        });

        menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle(query +" xəbərləri yüklənir");
                dialog.show();
                RequestManager manager = new RequestManager(main_page.this);
                manager.getNewsHeadlines(listener, "general",query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return  super.onCreateOptionsMenu(menu);
    }
}