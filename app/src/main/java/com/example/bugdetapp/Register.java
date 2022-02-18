package com.example.bugdetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText address, username, password;
    private String saddress, susername, spassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        address = findViewById(R.id.getAddressRegister);
        username = findViewById(R.id.getUsernameRegister);
        password = findViewById(R.id.getPasswordRegister);

    }

    public void register1(View view){
        saddress = address.getText().toString().trim();
        susername = username.getText().toString().trim();
        spassword = password.getText().toString().trim();

        if(saddress.isEmpty() || susername.isEmpty() || spassword.isEmpty()){
            Toast.makeText(Register.this, "Please fill up the form.", Toast.LENGTH_SHORT).show();
        }

        else {
            final String URL = "http://" + Final_IP.IP_ADDRESS + "/bg_app/register.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                Log.d("res", response);
                if (response.equals("success")) {
                    Toast.makeText(Register.this, "Register Successfully.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Register.this, Login.class);
                    startActivity(intent);
                    finish();
                } else if (response.equals("failure")) {
                    Toast.makeText(Register.this, "Something wrong, try again.", Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(Register.this, error.toString().trim(), Toast.LENGTH_SHORT).show()) {
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("address", saddress);
                    data.put("username", susername);
                    data.put("password", spassword);

                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
    public void login1(View view){
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
        finish();
    }
}