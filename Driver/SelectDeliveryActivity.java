package com.example.karchunkan.fyp.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.karchunkan.fyp.API.Driver.GetDeliveryData;
import com.example.karchunkan.fyp.ApiAddress;
import com.example.karchunkan.fyp.Customer.HomeCustomerActivity;
import com.example.karchunkan.fyp.IpAddress;
import com.example.karchunkan.fyp.R;

public class SelectDeliveryActivity extends AppCompatActivity {

    Button btnEnter;
    TableLayout tl;
    String packageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_pickup);

        packageID=this.getIntent().getExtras().getString("packageID");

        tl = (TableLayout) findViewById(R.id.tableLayoutList);

        Object dataTransfer[] = new Object[3];
        GetDeliveryData modifyData = new GetDeliveryData();
        String url="http://"+ IpAddress.IP_ADDRESS.getIp()+ ApiAddress.Delivery.getAddress()+"?action=get&packageID="+ packageID;
        Log.d("url", url);

        dataTransfer[0] = SelectDeliveryActivity.this;
        dataTransfer[1] = tl;
        dataTransfer[2] = url;
        modifyData.execute(dataTransfer);

//        View mTableRow=null;
//        int i=0;
//        while(i<10){ //while !last column in db status
//            i++;
//            TableRow tr=new TableRow(this);
//            TableRow.LayoutParams lp=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
//            lp.gravity= Gravity.RIGHT;
//            tr.setLayoutParams(lp);
//
//            TextView tv=new TextView(this);
//            tv.setPadding(20,20,20,20);
//            tv.setText("delivery: "+i);
//            tv.setTextSize(18);
//            tr.addView(tv);
//
//            Button btnTemp=new Button(this);
//            btnTemp.setText("Select");
//            btnTemp.setPadding(20,20,20,20);
//            btnTemp.setLayoutParams(lp);
//            btnTemp.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(SelectDeliveryActivity.this, VerifyDeliveryActivity.class);
//                    intent.putExtra("deliveryID","D123456");
//                    startActivity(intent);
//                }
//            });
//            tr.addView(btnTemp);
//
//            tl.addView(tr);
//        }
    }
}
