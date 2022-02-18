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

public class GetReceipt extends AppCompatActivity {
    private String sbn, sbp,spd,smth ,sdt, sid;
    private TextView tbn, tbp,tpd,tmth ,tdt, tid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_receipt);

        tbn = findViewById(R.id.getBillName);
        tbp = findViewById(R.id.getBillPrice);
        tpd = findViewById(R.id.getPaymentDuration);
        tmth = findViewById(R.id.getMonth);
        tdt = findViewById(R.id.getDatetime);
        tid = findViewById(R.id.getIdNumber);

        tbn.setText(FormData.bill_data[0]);
        tbp.setText(FormData.bill_data[1]);
        tpd.setText(FormData.bill_data[2]);
        tmth.setText(FormData.bill_data[3]);
        tdt.setText(FormData.bill_data[4]);
        tid.setText(FormData.bill_data[5]);

    }


    public void setBgFinale(View view){
        sbn = FormData.bill_data[0].trim();
        sbp = FormData.bill_data[1].trim();
        spd = FormData.bill_data[2].trim();
        smth = FormData.bill_data[3].trim();
        sdt = FormData.bill_data[4].trim();
        sid = FormData.bill_data[5];

        if(sid.isEmpty()){
            sid = "0";
        }


            final String URL = "http://" + Final_IP.IP_ADDRESS + "/bg_app/set_bg.php";
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                Log.d("res", response);
                if (response.equals("success")) {
                    Toast.makeText(GetReceipt.this, "Filled up Successfully.", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(GetReceipt.this, Home.class);
                    startActivity(intent);
                    finish();
                } else if (response.equals("failure")) {
                    Toast.makeText(GetReceipt.this, "Something wrong, try again.", Toast.LENGTH_SHORT).show();
                }
            }, error -> Toast.makeText(GetReceipt.this, error.toString().trim(), Toast.LENGTH_SHORT).show()) {
                @Override
                public Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("bn", sbn);
                    data.put("bp", sbp);
                    data.put("pd", spd);
                    data.put("mth", smth);
                    data.put("dt", sdt);
                    data.put("bid", sid);

                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);

    }
}