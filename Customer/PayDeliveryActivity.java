package com.example.karchunkan.fyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.karchunkan.fyp.API.GlobalModifyData;
import com.example.karchunkan.fyp.ApiAddress;
import com.example.karchunkan.fyp.IpAddress;
import com.example.karchunkan.fyp.MainActivity;
import com.example.karchunkan.fyp.R;

public class PayDeliveryActivity extends AppCompatActivity {

    TextView tvItemID,tvDescription, tvWeight, tvCost;
    Button btnPay;
    String itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_delivery);

        itemID=getIntent().getStringExtra("itemID");

        tvItemID =(TextView)findViewById(R.id.tvItemID);
        tvItemID.setText("ItemID "+itemID);
        tvDescription =(TextView)findViewById(R.id.tvDescription);
        tvDescription.setText("Description "+getIntent().getStringExtra("description"));
        tvWeight =(TextView)findViewById(R.id.tvWeight);
        tvWeight.setText("Pickup Start "+getIntent().getStringExtra("weight"));
        tvCost =(TextView)findViewById(R.id.tvCost);
        tvCost.setText("Pickup End "+getIntent().getStringExtra("cost"));

        btnPay =(Button)findViewById(R.id.btnPay);
        btnPay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Object dataTransfer[] = new Object[3];
                GlobalModifyData modifyData = new GlobalModifyData();
                String url="http://"+ IpAddress.IP_ADDRESS.getIp()+ ApiAddress.Payment.getAddress()+"?custID="+ MainActivity.userID+"&itemID="+itemID;
                Intent intent = new Intent(PayDeliveryActivity.this,HomeCustomerActivity.class);
                Log.d("url", url);

                dataTransfer[0] = PayDeliveryActivity.this;
                dataTransfer[1] = intent;
                dataTransfer[2] = url;
                modifyData.execute(dataTransfer);
            }
        });
    }
}
