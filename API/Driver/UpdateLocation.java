package com.example.karchunkan.fyp.API.Driver;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.karchunkan.fyp.API.DownloadUrl;
import com.example.karchunkan.fyp.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @auth Priyanka
 */

public class UpdateLocation extends AsyncTask<Object,String,String> {

    String data;
    String url;

    @Override
    protected String doInBackground(Object... objects) {
        url = (String)objects[0];

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
        s="{\"status\":true,\"msg\":\"success\"}";

        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(s);
            boolean status=jsonObject.getBoolean("status");
            if(status==true){
                Log.d("updateLocation","success");
            }else{

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}