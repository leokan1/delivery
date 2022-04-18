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

import com.example.karchunkan.fyp.API.Driver.GetPackageData;
import com.example.karchunkan.fyp.ApiAddress;
import com.example.karchunkan.fyp.Customer.HomeCustomerActivity;
import com.example.karchunkan.fyp.Customer.StatusActivity;
import com.example.karchunkan.fyp.Customer.ViewStatusActivity;
import com.example.karchunkan.fyp.IpAddress;
import com.example.karchunkan.fyp.MainActivity;
import com.example.karchunkan.fyp.R;

public class SelectPackageActivity extends AppCompatActivity {

    EditText txtPackageID;
    Button btnEnter;
    TableLayout tl;
    String fromActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_package);

        Intent intent=this.getIntent();
        if(intent!=null){
            fromActivity=intent.getExtras().getString("startFrom");
        }

        tl = (TableLayout) findViewById(R.id.tableLayoutList);

        Object dataTransfer[] = new Object[4];
        GetPackageData modifyData = new GetPackageData();
        String url="http://"+ IpAddress.IP_ADDRESS.getIp()+ ApiAddress.Package.getAddress()+"?driverID="+ MainActivity.userID;
        Log.d("url", url);

        dataTransfer[0] = SelectPackageActivity.this;
        dataTransfer[1] = tl;
        dataTransfer[2] = fromActivity;
        dataTransfer[3] = url;
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
//            tv.setText("package: "+i);
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
//                    Intent intent=null;
//                    if(fromActivity.equals("From_Select_Pickup")) {
//                        intent = new Intent(SelectPackageActivity.this, SelectPickupActivity.class);
//                    }else if(fromActivity.equals("From_Optimize_Schedule")){
//                        intent = new Intent(SelectPackageActivity.this, OptimizeScheduleActivity.class);
//                    }else if(fromActivity.equals("From_Verify_Delivery")){
//                        intent = new Intent(SelectPackageActivity.this, SelectDeliveryActivity.class);
//                    }
//                    intent.putExtra("packageID","P123456");
//                    startActivity(intent);
//                }
//            });
//            tr.addView(btnTemp);
//
//            tl.addView(tr);
//        }
    }
}
