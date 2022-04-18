package com.example.karchunkan.fyp.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.karchunkan.fyp.API.Driver.GetOptimizeScheduleData;
import com.example.karchunkan.fyp.API.Driver.GetScheduleData;
import com.example.karchunkan.fyp.ApiAddress;
import com.example.karchunkan.fyp.Customer.HomeCustomerActivity;
import com.example.karchunkan.fyp.Customer.ViewStatusActivity;
import com.example.karchunkan.fyp.IpAddress;
import com.example.karchunkan.fyp.R;

public class OptimizeScheduleActivity extends AppCompatActivity {

    Button btnLocation;
    TableLayout tl;
    String packageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimize_schedule);

        packageID=this.getIntent().getExtras().getString("packageID");

        tl = (TableLayout) findViewById(R.id.tableLayoutList);

        tl = (TableLayout) findViewById(R.id.tableLayoutList);

        Object dataTransfer[] = new Object[3];
        GetOptimizeScheduleData modifyData = new GetOptimizeScheduleData();
        String url="http://"+ IpAddress.IP_ADDRESS.getIp()+ ApiAddress.Schedule.getAddress()+"?packageID="+ packageID;
        Log.d("url", url);

        dataTransfer[0] = OptimizeScheduleActivity.this;
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
//            tv.setText("Schedule: "+i);
//            tv.setTextSize(18);
//            tr.addView(tv);
//
//            tl.addView(tr);
//        }

        btnLocation=(Button)findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Object dataTransfer[] = new Object[3];
                Intent intent = new Intent(OptimizeScheduleActivity.this,MapsDriverActivity.class);
                GetScheduleData getLocationData = new GetScheduleData();
                String url="http://"+ IpAddress.IP_ADDRESS.getIp()+ ApiAddress.Schedule.getAddress()+"?packageID="+ packageID;
                Log.d("url", url);

                dataTransfer[0] = OptimizeScheduleActivity.this;
                dataTransfer[1] = intent;
                dataTransfer[2] = url;
                getLocationData.execute(dataTransfer);
            }
        });
    }
}
