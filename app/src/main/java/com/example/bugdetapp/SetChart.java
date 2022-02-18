package com.example.bugdetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;

public class SetChart extends AppCompatActivity {
    Button btnBack;
    int c =0;
    int total = 0;
    int formula;
    int[] pricethisMonth;
    String[] namethisMonth;
    int i=0;

    int lgByMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_chart);

        for(i=0;i<FormData.month.length;i++){
            if(FormData.month[i] =="February"){
                c++;
            }
        }

        lgByMonth = c;


        pricethisMonth =new int[lgByMonth];
        namethisMonth =new String[lgByMonth];

        c=0;

        for(i=0;i<FormData.month.length;i++){
            if(FormData.month[i] =="February"){
                pricethisMonth[c] = Integer.parseInt(FormData.pieBillPrice[i]);
                namethisMonth[c] = FormData.pieBillName[i];
                c++;
            }
        }

        c=0;

        PieChartView pieChartView = findViewById(R.id.getPieChart);

        List<SliceValue> pieData = new ArrayList<>();

        for(i =0;i<FormData.thisMonthName.length;i++){
            String label = "Name: "+FormData.thisMonthName[i]+"\nPrice: "+FormData.thisMonthPrice[i];
           pieData.add(new SliceValue(Float.parseFloat(FormData.thisMonthPrice[i]), getRandomColor()).setLabel(label));
        }

        //pieData.add(new SliceValue(150, Color.BLUE).setLabel("Q1: $10"));
        //pieData.add(new SliceValue(250, Color.GRAY).setLabel("Q2: $4"));
        //pieData.add(new SliceValue(100, Color.RED).setLabel("Q3: $18"));
        //pieData.add(new SliceValue(600, Color.MAGENTA).setLabel("Q4: $28"));

        PieChartData pieChartData = new PieChartData(pieData);
        pieChartData.setHasLabels(true);
        pieChartData.setHasCenterCircle(true).setCenterText1("Month of February").setCenterText1FontSize(20).setCenterText1Color(Color.parseColor("#90EE90"));
        pieChartView.setPieChartData(pieChartData);

        btnBack = findViewById(R.id.chartToHome);
        btnBack.bringToFront();

    }

    public void GraphtoHome(View view){
        Intent intent = new Intent(SetChart.this, Graphs.class);
        startActivity(intent);
        finish();
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}