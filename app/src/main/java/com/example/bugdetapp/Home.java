package com.example.bugdetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class Home extends AppCompatActivity {

    private RequestQueue queue;
    TextView tv;
    String setL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        queue = Volley.newRequestQueue(this);

        tv = findViewById(R.id.tv);

        jsonParse();
        jsonParse1();

    }

    public void showBudget(View view){
        Intent intent = new Intent(Home.this, SetBudget.class);
        startActivity(intent);
        finish();
    }

    public void showGraph(View view){
        Intent intent = new Intent(Home.this, Graphs.class);
        startActivity(intent);
        finish();
    }

    public void showMore(View view){
        Toast.makeText(Home.this, "Logged out Successfully.", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Home.this, Login.class);
        startActivity(intent);
        finish();
    }



    private void jsonParse() {

        String url = "http://"+Final_IP.IP_ADDRESS+"/bg_app/get_list.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    FormData.pieBillName = new String[jsonArray.length()];
                    FormData.pieBillPrice = new String[jsonArray.length()];
                    FormData.linePaymentDuration = new String[jsonArray.length()];
                    FormData.month = new String[jsonArray.length()];
                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String bp = object.getString("bp");
                        FormData.pieBillPrice[i] =bp;
                        String bn = object.getString("bn");
                        FormData.pieBillName[i] =bn;
                        String pd = object.getString("pd");
                        FormData.linePaymentDuration[i] =pd;
                        String mth = object.getString("mth");
                        FormData.month[i] =mth;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, "Invalid action.", Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(request);
        super.onStart();
    }

    private void jsonParse1() {

        String url = "http://"+Final_IP.IP_ADDRESS+"/bg_app/getbymonth.php";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArray = jsonObject.getJSONArray("list");
                    FormData.thisMonthName = new String[jsonArray.length()];
                    FormData.thisMonthPrice = new String[jsonArray.length()];

                    for (int i = 0; i <jsonArray.length() ; i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String bp = object.getString("bp");
                        FormData.thisMonthPrice[i] =bp;
                        String bn = object.getString("bn");
                        FormData.thisMonthName[i] =bn;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Home.this, "Invalid action.", Toast.LENGTH_SHORT).show();

            }
        });

        queue.add(request);
        super.onStart();
    }
}