package com.example.karchunkan.fyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.karchunkan.fyp.API.Customer.GetLocationData;
import com.example.karchunkan.fyp.ApiAddress;
import com.example.karchunkan.fyp.Driver.MapsDriverActivity;
import com.example.karchunkan.fyp.IpAddress;
import com.example.karchunkan.fyp.MainActivity;
import com.example.karchunkan.fyp.R;

public class ViewStatusActivity extends AppCompatActivity {

    TextView tvItemID,tvDescription,tvPickupStart,tvPickupEnd,tvExpectedTime;
    Button btnLocation;
    String packageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewstatus);

        packageID=getIntent().getStringExtra("packageID");

        tvItemID =(TextView)findViewById(R.id.tvItemID);
        tvItemID.setText("ItemID "+getIntent().getStringExtra("itemID"));
        tvDescription =(TextView)findViewById(R.id.tvDescription);
        tvDescription.setText("Description "+getIntent().getStringExtra("description"));
        tvPickupStart =(TextView)findViewById(R.id.tvPickupStart);
        tvPickupStart.setText("Pickup Start "+getIntent().getStringExtra("pickupStart"));
        tvPickupEnd =(TextView)findViewById(R.id.tvPickupEnd);
        tvPickupEnd.setText("Pickup End "+getIntent().getStringExtra("pickupEnd"));
        tvExpectedTime =(TextView)findViewById(R.id.tvExpectedTime);
        tvExpectedTime.setText("Expected Time "+getIntent().getIntExtra("expectedTime",0));

        btnLocation=(Button)findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
//                String packageID=getIntent().getStringExtra("packageID");
//
//                startActivity(intent);

                Object dataTransfer[] = new Object[3];
                Intent intent = new Intent(ViewStatusActivity.this,MapsCustomerActivity.class);
                GetLocationData getLocationData = new GetLocationData();
                String url="http://"+ IpAddress.IP_ADDRESS.getIp()+ ApiAddress.Status.getAddress()+
                        "?packageID="+ packageID+
                        "&custID="+MainActivity.userID;
                Log.d("url", url);

                dataTransfer[0] = ViewStatusActivity.this;
                dataTransfer[1] = intent;
                dataTransfer[2] = url;
                getLocationData.execute(dataTransfer);
            }
        });
    }
}
