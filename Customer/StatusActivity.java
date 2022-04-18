package com.example.karchunkan.fyp.Customer;

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

import com.example.karchunkan.fyp.API.Customer.GetStatusData;
import com.example.karchunkan.fyp.ApiAddress;
import com.example.karchunkan.fyp.IpAddress;
import com.example.karchunkan.fyp.MainActivity;
import com.example.karchunkan.fyp.R;

public class StatusActivity extends AppCompatActivity {

    TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        tl = (TableLayout) findViewById(R.id.tableLayoutList);

        Object dataTransfer[] = new Object[3];
        GetStatusData modifyData = new GetStatusData();
        String url="http://"+ IpAddress.IP_ADDRESS.getIp()+ ApiAddress.Status.getAddress()+"?custID="+ MainActivity.userID;
        Log.d("url", url);

        dataTransfer[0] = StatusActivity.this;
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
//            tv.setText("test: "+i);
//            tv.setTextSize(18);
//            tr.addView(tv);
//
//            Button btnTemp=new Button(this);
//            btnTemp.setText("Button");
//            btnTemp.setPadding(20,20,20,20);
//            btnTemp.setLayoutParams(lp);
//            btnTemp.setOnClickListener(new View.OnClickListener(){
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(StatusActivity.this,ViewStatusActivity.class);
//                    intent.putExtra("itemID","itemID");
//                    startActivity(intent);
//                }
//            });
//            tr.addView(btnTemp);
//
//            tl.addView(tr);
//        }
    }
}
