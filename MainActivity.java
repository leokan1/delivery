package com.example.karchunkan.fyp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.example.karchunkan.fyp.API.GetAccessData;
import com.example.karchunkan.fyp.API.GlobalModifyData;
import com.example.karchunkan.fyp.Customer.HomeCustomerActivity;
import com.example.karchunkan.fyp.Customer.RegisterActivity;
import com.example.karchunkan.fyp.Driver.HomeDriverActivity;

public class MainActivity extends AppCompatActivity {

    Button btnLogin,btnRegister;
    EditText txtUsername;
    EditText txtPassword;
    public static String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Object dataTransfer[] = new Object[2];
                GetAccessData modifyData = new GetAccessData();
                String url="http://"+ IpAddress.IP_ADDRESS.getIp()+ ApiAddress.Login.getAddress()+
                        "?uid="+txtUsername.getText().toString()+
                        "&pwd="+txtPassword.getText().toString();
                Log.d("url", url);

                dataTransfer[0] = MainActivity.this;
                dataTransfer[1] = url;
                modifyData.execute(dataTransfer);

//                Intent intent = new Intent(MainActivity.this,HomeCustomerActivity.class);
//                Intent intent = new Intent(MainActivity.this,HomeDriverActivity.class);
//                intent.putExtra("username",txtUsername.getText().toString());
//                userID="D00001";
//                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
}
