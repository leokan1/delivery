package com.example.karchunkan.fyp.API.Driver;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.karchunkan.fyp.API.DataParser;
import com.example.karchunkan.fyp.API.DownloadUrl;
import com.example.karchunkan.fyp.Customer.ViewStatusActivity;
import com.example.karchunkan.fyp.Driver.VerifyDeliveryActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by karchunkan on 12/8/2018.
 */

public class GetDeliveryData extends AsyncTask<Object, String, String> {

    Context context;
    String url;
    String googleDirectionsData;
    //String duration, distance;
    TableLayout tl;
    String deliveryID;

    @Override
    protected String doInBackground(Object... objects) {
        context = (Context) objects[0];
        tl = (TableLayout) objects[1];
        url = (String) objects[2];


        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googleDirectionsData = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return googleDirectionsData;
    }

    @Override
    protected void onPostExecute(String s) {
        //testing
//        s = "{\"result\":200,\"message\":\"success\",\"data\":{\"Delivery\":{\"deliveryID\":\"D00001\",\"custID\":\"C00001\",\"itemID\":\"I00001\",\"packageID\":\"P00001\",\"expectedTime\":\"200\",\"address\":\"??????\",\"gpsX\":\"22.3074357\",\"gpsY\":\"114.2600173\",\"payFlag\":\"1\",\"status\":\"delivery\",\"deliveryPin\":\"123456\",\"deliveryOrder\":\"1\"}}}";
        DataParser parser = new DataParser();
        JSONObject jsonObject;
        JSONArray jsonLegsArray;
        String deliveryPin = "";
        try {
            jsonObject = new JSONObject(s);
            jsonLegsArray = jsonObject.getJSONObject("data").getJSONArray("Delivery");
            int directionNum = jsonLegsArray.length(); //
            for (int i = 0; i < directionNum; i++) {
                deliveryID = jsonLegsArray.getJSONObject(i).getString("deliveryID");
                deliveryPin = jsonLegsArray.getJSONObject(i).getString("deliveryPin");
                displayView(deliveryPin);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void displayView(final String deliveryPin) {
        TableRow tr = new TableRow(context);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.RIGHT;
        tr.setLayoutParams(lp);

        TextView tv = new TextView(context);
        tv.setPadding(20, 20, 20, 20);
        tv.setText("delivery: " + deliveryID);
        tv.setTextSize(18);
        tr.addView(tv);

        Button btnTemp = new Button(context);
        btnTemp.setText("Select");
        btnTemp.setPadding(20, 20, 20, 20);
        btnTemp.setLayoutParams(lp);
        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VerifyDeliveryActivity.class);
                intent.putExtra("deliveryID",deliveryID);
                intent.putExtra("deliveryPin",deliveryPin);
                context.startActivity(intent);
            }
        });
        tr.addView(btnTemp);

        tl.addView(tr);
    }
}