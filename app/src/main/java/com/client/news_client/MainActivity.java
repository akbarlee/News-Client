package com.client.news_client;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;

import android.widget.TextView;

import com.example.eatfood.R;
import com.google.android.material.textfield.TextInputEditText;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
    public FirebaseAuth mAuth;
    private static final String TAG = "MainActivity";
  
    
     @Override
      protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


    }
}