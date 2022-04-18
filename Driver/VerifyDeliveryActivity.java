package com.example.karchunkan.fyp.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.karchunkan.fyp.API.GlobalModifyData;
import com.example.karchunkan.fyp.ApiAddress;
import com.example.karchunkan.fyp.Customer.HomeCustomerActivity;
import com.example.karchunkan.fyp.IpAddress;
import com.example.karchunkan.fyp.R;

public class VerifyDeliveryActivity extends AppCompatActivity {

    String deliveryID,db_deliveryPin;
    Button btnEnter;
    EditText txtdeliveryPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_delivery);

        deliveryID=this.getIntent().getExtras().getString("deliveryID");
        db_deliveryPin=this.getIntent().getExtras().getString("deliveryPin");

        txtdeliveryPin=(EditText)findViewById(R.id.txtDeliveryPin);

        btnEnter = (Button) findViewById(R.id.btnEnter);
        btnEnter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Log.d("db_Pin",db_deliveryPin);
                Log.d("Pin",txtdeliveryPin.getText().toString());
                if(db_deliveryPin.equals(txtdeliveryPin.getText().toString())) {
                    Object dataTransfer[] = new Object[3];
                    GlobalModifyData modifyData = new GlobalModifyData();
                    String url="http://"+ IpAddress.IP_ADDRESS.getIp()+ ApiAddress.Delivery.getAddress()+"?action=update&deliveryID="+ deliveryID;
                    Intent intent = new Intent(VerifyDeliveryActivity.this,HomeDriverActivity.class);
                    Log.d("url", url);

                    dataTransfer[0] = VerifyDeliveryActivity.this;
                    dataTransfer[1] = intent;
                    dataTransfer[2] = url;
                    modifyData.execute(dataTransfer);
                }
            }
        });
    }
}
