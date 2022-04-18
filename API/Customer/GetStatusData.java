package com.example.karchunkan.fyp.API.Customer;

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
import com.example.karchunkan.fyp.Customer.StatusActivity;
import com.example.karchunkan.fyp.Customer.ViewStatusActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by karchunkan on 12/8/2018.
 */

public class GetStatusData extends AsyncTask<Object, String, String> {

    Context context;
    String url;
    String googleDirectionsData;
    //String duration, distance;
    TableLayout tl;
    String itemID;

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
        //s = "{\"result\":200,\"message\":\"success\",\"data\":{\"Status\":[{\"deliveryID\":\"D00001\",\"custID\":\"C00001\",\"itemID\":\"I00001\",\"packageID\":\"P00001\",\"expectedTime\":\"2018-08-21 08:00:01\",\"address\":\"??????\",\"gpsX\":\"22.3074357\",\"gpsY\":\"114.2600173\",\"payFlag\":\"1\",\"status\":\"delivery\",\"deliveryPin\":\"123456\",\"deliveryOrder\":\"1\",\"description\":\"goods\"}]}}";

        DataParser parser = new DataParser();
        JSONObject jsonObject;
        JSONArray jsonLegsArray;
        String description = "",status="",itemID="",packageID="",pickupStart="",pickupEnd="";
        int expectedTime;
        try {
            jsonObject = new JSONObject(s);
            int result=jsonObject.getInt("result");
            if(result==200) {
                jsonLegsArray = jsonObject.getJSONObject("data").getJSONArray("Status");
                int arrayNum = jsonLegsArray.length(); //
                for (int i = 0; i < arrayNum; i++) {
                    description=jsonObject.getJSONObject("data").getJSONArray("Status").getJSONObject(i).getString("description");
                    status=jsonObject.getJSONObject("data").getJSONArray("Status").getJSONObject(i).getString("status");
                    itemID=jsonObject.getJSONObject("data").getJSONArray("Status").getJSONObject(i).getString("itemID");
                    packageID=jsonObject.getJSONObject("data").getJSONArray("Status").getJSONObject(i).getString("packageID");
                    pickupStart=jsonObject.getJSONObject("data").getJSONArray("Status").getJSONObject(i).getString("pickupStart");
                    pickupEnd=jsonObject.getJSONObject("data").getJSONArray("Status").getJSONObject(i).getString("pickupEnd");
                    expectedTime=jsonObject.getJSONObject("data").getJSONArray("Status").getJSONObject(i).getInt("expectedTime");
                    displayView(description, status,itemID,packageID,pickupStart,pickupEnd,expectedTime);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void displayView(final String description, String status, final String itemID, final String packageID, final String pickupStart, final String pickupEnd, final int expectedTime) {
        TableRow tr = new TableRow(context);
        TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.RIGHT;
        tr.setLayoutParams(lp);

        TextView tv = new TextView(context);
        tv.setPadding(20, 20, 20, 20);
        tv.setText("description: "+description+"\nstatus: " + status);
        tv.setTextSize(18);
        tr.addView(tv);

        Button btnTemp = new Button(context);
        btnTemp.setText("Select");
        btnTemp.setPadding(20, 20, 20, 20);
        btnTemp.setLayoutParams(lp);
        btnTemp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewStatusActivity.class);
                intent.putExtra("itemID", itemID);
                intent.putExtra("packageID", packageID);
                intent.putExtra("description", description);
                intent.putExtra("pickupStart", pickupStart);
                intent.putExtra("pickupEnd", pickupEnd);
                intent.putExtra("expectedTime", expectedTime);
                context.startActivity(intent);
            }
        });
        tr.addView(btnTemp);

        tl.addView(tr);
    }
}