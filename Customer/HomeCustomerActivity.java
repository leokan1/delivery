package com.example.karchunkan.fyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.karchunkan.fyp.R;

public class HomeCustomerActivity extends AppCompatActivity {

    TextView welcomeMsg;
    Button btnStatus,btnDeliveryReq;
    public String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_customer);

        if (username == null) {
            username = getIntent().getStringExtra("username");
        }
        welcomeMsg = (TextView) findViewById(R.id.welcomeMsg);
        welcomeMsg.setText("Welcome " + username + "!");

        btnStatus = (Button) findViewById(R.id.btnDeliveryStat);
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeCustomerActivity.this, StatusActivity.class);
                startActivity(intent);
            }
        });

        btnDeliveryReq = (Button) findViewById(R.id.btnDeliveryReq);
        btnDeliveryReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeCustomerActivity.this, DeliveryRequestActivity.class);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Intent intent;
                        switch (item.getItemId()) {
                            case R.id.action_request:
                                 intent = new Intent(HomeCustomerActivity.this, DeliveryRequestActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.action_status:
                                 intent = new Intent(HomeCustomerActivity.this, StatusActivity.class);
                                startActivity(intent);
                                break;
                            case R.id.action_notification:
                                break;
                        }
                        return true;
                    }
                });

    }
}
