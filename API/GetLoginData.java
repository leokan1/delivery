package com.example.karchunkan.fyp.API;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.karchunkan.fyp.ApiAddress;
import com.example.karchunkan.fyp.Customer.HomeCustomerActivity;
import com.example.karchunkan.fyp.Driver.HomeDriverActivity;
import com.example.karchunkan.fyp.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * @auth Priyanka
 */

public class GetLoginData extends AsyncTask<Object,String,String> {

    Context context;
    String data;
    String url;
    String uid;

    @Override
    protected String doInBackground(Object... objects) {
        context = (Context) objects[0];
        uid = (String) objects[1];
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
            int result=jsonObject.getInt("result");
            if(result==200){
                if(uid.substring(0,1).equals("C")) {
                    String userID=jsonObject.getJSONObject("data").getJSONObject("customer").getString("custID");
                    String username=jsonObject.getJSONObject("data").getJSONObject("customer").getString("name");

                    Intent intent = new Intent(context, HomeCustomerActivity.class);
                    intent.putExtra("username", username);
                    MainActivity.userID=userID;

                    context.startActivity(intent);
                }else if(uid.substring(0,1).equals("D")){
                    String userID=jsonObject.getJSONObject("data").getJSONObject("driver").getString("driverID");
                    String username=jsonObject.getJSONObject("data").getJSONObject("driver").getString("name");

                    Intent intent = new Intent(context, HomeDriverActivity.class);
                    intent.putExtra("username", username);
                    MainActivity.userID=userID;

                    context.startActivity(intent);
                }

            }else{

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}