package com.example.karchunkan.fyp.Driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.karchunkan.fyp.R;

public class HomeDriverActivity extends AppCompatActivity {

    TextView welcomeMsg;
    Button btnLocationUpdate, btnSelectPickup,btnSchedule,btnVerifyDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_driver);

        String username=getIntent().getStringExtra("username");
        welcomeMsg=(TextView)findViewById(R.id.welcomeMsg);
        welcomeMsg.setText("Welcome "+username+"!");

        btnLocationUpdate = (Button) findViewById(R.id.btnLocationUpdate);
        btnLocationUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDriverActivity.this,LocationUpdateActivity.class);
                startActivity(intent);
            }
        });

        btnSelectPickup = (Button) findViewById(R.id.btnSelectPickup);
        btnSelectPickup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDriverActivity.this,SelectPackageActivity.class);
                intent.putExtra("startFrom","From_Select_Pickup");
                startActivity(intent);
            }
        });

        btnSchedule = (Button) findViewById(R.id.btnSchedule);
        btnSchedule.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDriverActivity.this,SelectPackageActivity.class);
                intent.putExtra("startFrom","From_Optimize_Schedule");
                startActivity(intent);
            }
        });

        btnVerifyDelivery = (Button) findViewById(R.id.btnVerifyDelivery);
        btnVerifyDelivery.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeDriverActivity.this,SelectPackageActivity.class);
                intent.putExtra("startFrom","From_Verify_Delivery");
                startActivity(intent);
            }
        });
    }
}
