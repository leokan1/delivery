package com.example.karchunkan.fyp.API;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.karchunkan.fyp.ApiAddress;
import com.example.karchunkan.fyp.IpAddress;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @auth Priyanka
 */

public class GetAccessData extends AsyncTask<Object,String,String> {

    Context context;
    String data;
    String url;

    @Override
    protected String doInBackground(Object... objects) {
        context = (Context) objects[0];
        url = (String)objects[1];


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
            int result=jsonObject.getInt("result");
            String access_token=jsonObject.getJSONObject("data").getString("token");
            String uid=jsonObject.getJSONObject("data").getString("uid");
            if(result==200){
                Object dataTransfer[] = new Object[3];
                GetLoginData modifyData = new GetLoginData();
                String url = "http://" + IpAddress.IP_ADDRESS.getIp() + ApiAddress.Login.getAddress() +
                        "?access_token=" + access_token +
                        "&uid=" + uid;
                Log.d("url",url);

                dataTransfer[0] = context;
                dataTransfer[1] = uid;
                dataTransfer[2] = url;
                modifyData.execute(dataTransfer);
            }else{

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}