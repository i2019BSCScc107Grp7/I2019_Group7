package com.example.bugdetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Graphs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);
    }
    public void showPie(View view){
        Intent intent = new Intent(Graphs.this, SetChart.class);
        startActivity(intent);
        finish();
    }
    public void showLine(View view){
        Intent intent = new Intent(Graphs.this, SetDuration.class);
        startActivity(intent);
        finish();
    }
    public void showBar(View view){
        Intent intent = new Intent(Graphs.this, SetChart.class);
        startActivity(intent);
        finish();
    }

    public void graphBacktoHome(View view){
        Intent intent = new Intent(Graphs.this, Home.class);
        startActivity(intent);
        finish();
    }
}