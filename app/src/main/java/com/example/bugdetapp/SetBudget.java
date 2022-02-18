package com.example.bugdetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SetBudget extends AppCompatActivity {
    private EditText bn, bp, dt, id;

    Spinner pd,mth;

    int pdi, mthi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_budget);

        pd = (Spinner) findViewById(R.id.pd_s1);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> pd_a = ArrayAdapter
                .createFromResource(this, R.array.dur_list,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        pd_a
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        pd.setAdapter(pd_a);

        mth = (Spinner) findViewById(R.id.m_s2);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> m1_a = ArrayAdapter
                .createFromResource(this, R.array.cal_list,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        m1_a
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mth.setAdapter(m1_a);

        int sMon = 1;

        bn = findViewById(R.id.bn_et);
        bp = findViewById(R.id.bp_et);
        dt = findViewById(R.id.dt_et);
        id = findViewById(R.id.bi_id);

        String dt1 = new SimpleDateFormat("dd-MM-yyyy hh:mm aa", Locale.getDefault()).format(new Date());
        String sm1 = new SimpleDateFormat("MMM", Locale.getDefault()).format(new Date());

        for(int i =0;i<FormData.gm.length;i++){
            if(FormData.gm[i] == sm1){
                sMon = i;
            }
        }

        dt.setText(dt1);
        dt.setEnabled(false);
        mth.setSelection(sMon);

    }

    public void setBgForm(View view){
        pdi = pd.getSelectedItemPosition();
        mthi = mth.getSelectedItemPosition();

        FormData.bill_data[0] = bn.getText().toString();
        FormData.bill_data[1] = bp.getText().toString();
        FormData.bill_data[2] = FormData.gpd[pdi];
        FormData.bill_data[3] = FormData.gm[mthi];
        FormData.bill_data[4] = dt.getText().toString();
        FormData.bill_data[5] = id.getText().toString();

            if (FormData.bill_data[0].isEmpty()|| FormData.bill_data[1].isEmpty()){
                Toast.makeText(SetBudget.this, "Please fill up the form.", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(SetBudget.this, GetReceipt.class);
                startActivity(intent);
                finish();
            }

    }

    public void BudgettoHome(View view){
        Intent intent = new Intent(SetBudget.this, Graphs.class);
        startActivity(intent);
        finish();
    }

}