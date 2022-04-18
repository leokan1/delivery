package com.example.karchunkan.fyp.API.Customer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.karchunkan.fyp.API.DataParser;
import com.example.karchunkan.fyp.API.DownloadUrl;
import com.example.karchunkan.fyp.Customer.ViewStatusActivity;
import com.example.karchunkan.fyp.MainActivity;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by karchunkan on 12/8/2018.
 */

public class GetLocationData extends AsyncTask<Object, String, String> {

    Context context;
    Intent intent;
    String url;
    String googleDirectionsData;
    //String duration, distance;
    TableLayout tl;
    String itemID;

    @Override
    protected String doInBackground(Object... objects) {
        context = (Context) objects[0];
        intent = (Intent) objects[1];
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
        //s = "{\"result\":200,\"message\":\"success\",\"data\":{\"Status\":[{\"deliveryID\":\"D00001\",\"custID\":\"C00001\",\"itemID\":\"I00001\",\"packageID\":\"P00001\",\"expectedTime\":\"200\",\"address\":\"??????\",\"gpsX\":\"22.3074357\",\"gpsY\":\"114.2600173\",\"payFlag\":\"1\",\"status\":\"delivery\",\"deliveryPin\":\"123456\",\"deliveryOrder\":\"1\",\"custGpsY\":\"114.2600173\",\"driverGpsX\":\"22.3074357\",\"driverGpsY\":\"114.2600173\"}]}}";
        DataParser parser = new DataParser();
        JSONObject jsonObject;
        JSONArray jsonArray;
        String description = "";
        try {
            jsonObject = new JSONObject(s);
            jsonArray = jsonObject.getJSONObject("data").getJSONArray("Status");
            int directionNum = jsonArray.length(); //
            double[][] waypoints=new double[directionNum][2];
            for (int i = 0; i < directionNum; i++) {


                waypoints[i][0]=jsonArray.getJSONObject(i).getDouble("gpsX");
                waypoints[i][1]=jsonArray.getJSONObject(i).getDouble("gpsY");
            }
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("waypoints",  waypoints);
            intent.putExtras(mBundle);
            LatLng custGps=new LatLng(jsonArray.getJSONObject(0).getDouble("custGpsX"),jsonArray.getJSONObject(0).getDouble("custGpsY"));
            LatLng driverGps=new LatLng(jsonArray.getJSONObject(0).getDouble("driverGpsX"),jsonArray.getJSONObject(0).getDouble("driverGpsY"));
            Bundle args0=new Bundle();
            args0.putParcelable("custGps",custGps);
            Bundle args1=new Bundle();
            args1.putParcelable("driverGps",driverGps);
            intent.putExtras(args0);
            intent.putExtras(args1);
//            intent.putExtra("custGpsX",jsonArray.getJSONObject(0).getDouble("custGpsX"));
//            intent.putExtra("custGpsY",jsonArray.getJSONObject(0).getDouble("custGpsY"));
//            intent.putExtra("driverGpsX",jsonArray.getJSONObject(0).getDouble("driverGpsX"));
//            intent.putExtra("driverGpsY",jsonArray.getJSONObject(0).getDouble("driverGpsY"));

            context.startActivity(intent);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}