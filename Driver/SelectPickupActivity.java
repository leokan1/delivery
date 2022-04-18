package com.example.karchunkan.fyp.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.example.karchunkan.fyp.API.Driver.GetPickupData;
import com.example.karchunkan.fyp.API.Driver.UpdatePickup;
import com.example.karchunkan.fyp.ApiAddress;
import com.example.karchunkan.fyp.IpAddress;
import com.example.karchunkan.fyp.R;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

public class SelectPickupActivity extends AppCompatActivity {

    Button btnEnter;
    Spinner spOrigin,spDestination;
    String packageID;
    String txtOrigin="",txtDestination="";
    HashMap<String, LatLng> addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pickup);

        packageID = this.getIntent().getExtras().getString("packageID");

        btnEnter = (Button) findViewById(R.id.btnEnter);
        spOrigin = (Spinner) findViewById(R.id.spOrigin);
        spDestination = (Spinner) findViewById(R.id.spDestination);

        Object dataTransfer[] = new Object[5];
        GetPickupData modifyData = new GetPickupData();
        String url = "http://" + IpAddress.IP_ADDRESS.getIp() + ApiAddress.Delivery.getAddress() +
                "?action=get&packageID=" + packageID;
        Log.d("url", url);

        dataTransfer[0] = SelectPickupActivity.this;
        dataTransfer[1] = spOrigin;
        dataTransfer[2] = spDestination;
        dataTransfer[3] = btnEnter;
        dataTransfer[4] = url;
        modifyData.execute(dataTransfer);

        addresses = modifyData.getAddresses();


    }
}
