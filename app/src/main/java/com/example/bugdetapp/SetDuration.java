package com.example.bugdetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

public class SetDuration extends AppCompatActivity {
    LineChartView lineChartView;
    String[] axisData = {"Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept",
            "Oct", "Nov", "Dec"};
    int[] yAxisData = {5000, 2000, 1500, 3000, 2000, 6000, 1500, 4000, 4500, 1000, 9000, 1800};

    private String strJson, apiUrl = "http://"+Final_IP.IP_ADDRESS+"/bg_app/get_list.php";

    private OkHttpClient client;
    private Response response;
    private RequestBody requestBody;
    private Request request;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_duration);

        int i;

        int totalPerMonth[] = new int[12];

        for(i=0;i<FormData.pieBillPrice.length;i++){
            switch(FormData.month[i]){
                case "January":
                    totalPerMonth[0] += Integer.parseInt(FormData.pieBillPrice[i]);
                    break;
                case "February":
                    totalPerMonth[1] += Integer.parseInt(FormData.pieBillPrice[i]);
                    break;
                case "March":
                    totalPerMonth[2] += Integer.parseInt(FormData.pieBillPrice[i]);
                    break;
                case "April":
                    totalPerMonth[3] += Integer.parseInt(FormData.pieBillPrice[i]);
                    break;
                case "May":
                    totalPerMonth[4] += Integer.parseInt(FormData.pieBillPrice[i]);
                    break;
                case "June":
                    totalPerMonth[5] += Integer.parseInt(FormData.pieBillPrice[i]);
                    break;
                case "July":
                    totalPerMonth[6] += Integer.parseInt(FormData.pieBillPrice[i]);
                    break;
                case "August":
                    totalPerMonth[7] += Integer.parseInt(FormData.pieBillPrice[i]);
                    break;
                case "September":
                    totalPerMonth[8] += Integer.parseInt(FormData.pieBillPrice[i]);
                    break;
                case "October":
                    totalPerMonth[9] += Integer.parseInt(FormData.pieBillPrice[i]);
                    break;
                case "November":
                    totalPerMonth[10] += Integer.parseInt(FormData.pieBillPrice[i]);
                    break;
                case "December":
                    totalPerMonth[11] += Integer.parseInt(FormData.pieBillPrice[i]);
                    break;
            }
        }

        lineChartView = findViewById(R.id.getLineChart);

        List yAxisValues = new ArrayList();
        List axisValues = new ArrayList();


        Line line = new Line(yAxisValues).setColor(Color.parseColor("#90EE90"));

        for (i = 0; i < axisData.length; i++) {
            axisValues.add(i, new AxisValue(i).setLabel(axisData[i]));
        }

        for (i = 0; i < yAxisData.length; i++) {
            yAxisValues.add(new PointValue(i, totalPerMonth[i]));
        }

        List lines = new ArrayList();
        lines.add(line);

        LineChartData data = new LineChartData();
        data.setLines(lines);

        Axis axis = new Axis();
        axis.setValues(axisValues);
        axis.setTextSize(16);
        axis.setTextColor(Color.parseColor("#808080"));
        data.setAxisXBottom(axis);

        Axis yAxis = new Axis();
        yAxis.setName("Sales in millions");
        yAxis.setTextColor(Color.parseColor("#808080"));
        yAxis.setTextSize(16);
        data.setAxisYLeft(yAxis);

        lineChartView.setLineChartData(data);
        Viewport viewport = new Viewport(lineChartView.getMaximumViewport());
        viewport.top = 100000;
        lineChartView.setMaximumViewport(viewport);
        lineChartView.setCurrentViewport(viewport);
    }

    public void durBacktoGraphs(View view){
        Intent intent = new Intent(SetDuration.this, Graphs.class);
        startActivity(intent);
        finish();
    }


}