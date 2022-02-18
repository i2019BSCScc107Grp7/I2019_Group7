package com.example.bugdetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    private EditText etUsername, etPassword;
    private String username, password;

    private final String URL = "http://"+Final_IP.IP_ADDRESS+"/bg_app/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = password = "";
        etUsername = findViewById(R.id.getUsernameLogin);
        etPassword = findViewById(R.id.getPasswordLogin);
    }

    public void login(View view) {
        username = etUsername.getText().toString().trim();
        password = etPassword.getText().toString().trim();
        if(!username.equals("") && !password.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                Log.d("res", response);


                if (response.equals("success")) {
                    FormData.username = username;

                    Intent intent = new Intent(Login.this, Home.class);
                    startActivity(intent);
                    finish();


                } else if (response.equals("failure")) {
                    Toast.makeText(Login.this, "Invalid Username/Password", Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(Login.this, error.toString().trim(), Toast.LENGTH_SHORT).show()){
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("username", username);
                    data.put("password", password);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }

    public void register(View view){
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
        finish();
    }


}