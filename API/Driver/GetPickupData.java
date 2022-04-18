package com.example.karchunkan.fyp.API.Driver;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.karchunkan.fyp.API.DataParser;
import com.example.karchunkan.fyp.API.DownloadUrl;
import com.example.karchunkan.fyp.ApiAddress;
import com.example.karchunkan.fyp.Driver.HomeDriverActivity;
import com.example.karchunkan.fyp.Driver.SelectPickupActivity;
import com.example.karchunkan.fyp.IpAddress;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by karchunkan on 12/8/2018.
 */

public class GetPickupData extends AsyncTask<Object, String, String> {

    Context context;
    String url;
    String googleDirectionsData;
    //String duration, distance;
    Spinner spOrigin,spDestination;
    Button btnEnter;
    String packageID,startX,startY,endX,endY;
    HashMap<String,LatLng> addresses;
    String txtOrigin="",txtDestination="";

    @Override
    protected String doInBackground(Object... objects) {
        context = (Context) objects[0];
        spOrigin = (Spinner) objects[1];
        spDestination = (Spinner) objects[2];
        btnEnter = (Button) objects[3];
        url = (String) objects[4];


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
//        s = "{\"result\":200,\"message\":\"success\",\"data\":{\"Delivery\":[{\"deliveryID\":\"D00001\",\"custID\":\"C00001\",\"itemID\":\"I00001\",\"packageID\":\"P00001\",\"expectedTime\":\"200\",\"address\":\"??????\",\"gpsX\":\"22.3074357\",\"gpsY\":\"114.2600173\",\"payFlag\":\"1\",\"status\":\"delivery\",\"deliveryPin\":\"123456\",\"deliveryOrder\":\"1\"}]}}";

        DataParser parser = new DataParser();
        JSONObject jsonObject;
        JSONArray jsonLegsArray;
        String address = "";
        try {
            jsonObject = new JSONObject(s);
            jsonLegsArray = jsonObject.getJSONObject("data").getJSONArray("Delivery");
            int directionNum = jsonLegsArray.length(); //
            addresses = new HashMap<>();
            String[] arraySpinner=new String[directionNum];
            for (int i = 0; i < directionNum; i++) {
                packageID = jsonLegsArray.getJSONObject(i).getString("packageID");
                address = jsonLegsArray.getJSONObject(i).getString("address");
                LatLng ll=new LatLng(jsonLegsArray.getJSONObject(i).getDouble("gpsX"),jsonLegsArray.getJSONObject(i).getDouble("gpsY"));
                addresses.put(address,ll);
                arraySpinner[i]=address;
            }

            displayView(arraySpinner);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void displayView(String[] arraySpinner) {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spOrigin.setAdapter(adapter);
        spDestination.setAdapter(adapter);
        spOrigin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                txtOrigin=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spDestination.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                txtDestination=adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("addresses",""+addresses.size());
                Log.d("txtOrigin",""+txtOrigin);
                if (addresses != null&&txtOrigin!=""&&txtDestination!="") {
                    Object dataTransfer[] = new Object[3];
                    UpdatePickup modifyData = new UpdatePickup();
                    LatLng origin = addresses.get(spOrigin.getSelectedItem().toString());
                    LatLng destination = addresses.get(spDestination.getSelectedItem().toString());
                    String url = "http://" + IpAddress.IP_ADDRESS.getIp() + ApiAddress.Pickup.getAddress() +
                            "?packageID=" + packageID +
                            "&startX=" + origin.latitude +
                            "&startY=" + origin.longitude +
                            "&endX=" + destination.latitude +
                            "&endY=" + destination.longitude;
                    Intent intent = new Intent(context, HomeDriverActivity.class);
                    Log.d("url",url);

                    dataTransfer[0] = context;
                    dataTransfer[1] = intent;
                    dataTransfer[2] = url;
                    modifyData.execute(dataTransfer);
                }
            }
        });
    }

    public HashMap<String,LatLng> getAddresses(){
        return addresses;
    }
}