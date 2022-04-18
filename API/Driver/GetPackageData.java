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
import com.example.karchunkan.fyp.Driver.OptimizeScheduleActivity;
import com.example.karchunkan.fyp.Driver.SelectDeliveryActivity;
import com.example.karchunkan.fyp.Driver.SelectPackageActivity;
import com.example.karchunkan.fyp.Driver.SelectPickupActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by karchunkan on 12/8/2018.
 */

public class GetPackageData extends AsyncTask<Object, String, String> {

    Context context;
    String url;
    String googleDirectionsData;
    //String duration, distance;
    TableLayout tl;
    String packageID,fromActivity;

    @Override
    protected String doInBackground(Object... objects) {
        context = (Context) objects[0];
        tl = (TableLayout) objects[1];
        fromActivity=(String)objects[2];
        url = (String) objects[3];


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
        s = "{\"result\":200,\"message\":\"success\",\"data\":{\"Package\":[{\"packageID\":\"P00001\",\"itemID\":\"I00001\",\"driverID\":\"D00001\",\"pickupDateTime\":null}]}}";

        DataParser parser = new DataParser();
        JSONObject jsonObject;
        JSONArray jsonLegsArray;
        //String packageID = "";
        try {
            jsonObject = new JSONObject(s);
            jsonLegsArray = jsonObject.getJSONObject("data").getJSONArray("Package");
            int directionNum = jsonLegsArray.length(); //
            for (int i = 0; i < directionNum; i++) {
                packageID = jsonLegsArray.getJSONObject(0).getString("packageID");
                displayView();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void displayView() {
        TableRow tr=new TableRow(context);
        TableRow.LayoutParams lp=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        lp.gravity= Gravity.RIGHT;
        tr.setLayoutParams(lp);

        TextView tv=new TextView(context);
        tv.setPadding(20,20,20,20);
        tv.setText("package: "+packageID);
        tv.setTextSize(18);
        tr.addView(tv);

        Button btnTemp=new Button(context);
        btnTemp.setText("Select");
        btnTemp.setPadding(20,20,20,20);
        btnTemp.setLayoutParams(lp);
        btnTemp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=null;
                if(fromActivity.equals("From_Select_Pickup")) {
                    intent = new Intent(context, SelectPickupActivity.class);
                }else if(fromActivity.equals("From_Optimize_Schedule")){
                    intent = new Intent(context, OptimizeScheduleActivity.class);
                }else if(fromActivity.equals("From_Verify_Delivery")){
                    intent = new Intent(context, SelectDeliveryActivity.class);
                }
                intent.putExtra("packageID",packageID);
                context.startActivity(intent);
            }
        });
        tr.addView(btnTemp);

        tl.addView(tr);
    }
}