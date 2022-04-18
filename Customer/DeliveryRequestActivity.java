package com.example.karchunkan.fyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.karchunkan.fyp.API.Customer.GetDeliveryRequestData;
import com.example.karchunkan.fyp.API.GlobalModifyData;
import com.example.karchunkan.fyp.ApiAddress;
import com.example.karchunkan.fyp.IpAddress;
import com.example.karchunkan.fyp.MainActivity;
import com.example.karchunkan.fyp.R;

public class DeliveryRequestActivity extends AppCompatActivity {

    EditText txtItemID;
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_request);

        txtItemID=(EditText) findViewById(R.id.txtItemID);

        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String itemID=txtItemID.getText().toString();

                Object dataTransfer[] = new Object[3];
                GetDeliveryRequestData modifyData = new GetDeliveryRequestData();
                String url="http://"+ IpAddress.IP_ADDRESS.getIp()+ ApiAddress.DeliveryReq.getAddress()+"?custID="+ MainActivity.userID+"&itemID="+itemID;
                Log.d("url", url);
                Intent intent = new Intent(DeliveryRequestActivity.this,PayDeliveryActivity.class);
                //intent.putExtra("itemID",itemID);

                dataTransfer[0] = DeliveryRequestActivity.this;
                dataTransfer[1] = intent;
                dataTransfer[2] = url;
                modifyData.execute(dataTransfer);
            }
        });
    }
}
