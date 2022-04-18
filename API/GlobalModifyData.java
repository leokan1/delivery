package com.example.karchunkan.fyp.API;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @auth Priyanka
 */

public class GlobalModifyData extends AsyncTask<Object,String,String> {

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
        s="{\"status\":true,\"msg\":\"success\"}";

        JSONObject jsonObject;

        try {
            jsonObject = new JSONObject(s);
            boolean status=jsonObject.getBoolean("status");
            if(status==true){
                context.startActivity(intent);
            }else{

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}