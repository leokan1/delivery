package com.example.karchunkan.fyp.API.Customer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.karchunkan.fyp.API.DownloadUrl;
import com.example.karchunkan.fyp.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @auth Priyanka
 */

public class GetDeliveryRequestData extends AsyncTask<Object,String,String> {

    Context context;
    Intent intent;
    String data;
    String url;

    @Override
    protected String doInBackground(Object... objects) {
        context = (Context) objects[0];
        intent = (Intent) objects[1];
        url = (String)objects[2];


        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            data = downloadUrl.readUrl(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //testing
        //s="{\"status\":true,\"msg\":\"success\",\"data\":{\"Item\":{\"itemID\":\"I00001\",\"custID\":\"C00001\",\"description\":\"goods\",\"weight\":\"1.0\",\"cost\":\"1.00\"}}}";

        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(s);
            boolean status=jsonObject.getBoolean("status");
            String itemID=jsonObject.getJSONObject("data").getJSONObject("Item").getString("itemID");
            String description=jsonObject.getJSONObject("data").getJSONObject("Item").getString("description");
            String weight=jsonObject.getJSONObject("data").getJSONObject("Item").getString("weight");
            String cost=jsonObject.getJSONObject("data").getJSONObject("Item").getString("cost");
            if(status==true){
                intent.putExtra("itemID",itemID);
                intent.putExtra("description",description);
                intent.putExtra("weight",weight);
                intent.putExtra("cost",cost);
                context.startActivity(intent);
            }else{

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}