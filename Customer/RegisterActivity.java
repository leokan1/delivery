package com.example.karchunkan.fyp.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.karchunkan.fyp.API.Customer.DoRegister;
import com.example.karchunkan.fyp.API.GlobalModifyData;
import com.example.karchunkan.fyp.ApiAddress;
import com.example.karchunkan.fyp.IpAddress;
import com.example.karchunkan.fyp.MainActivity;
import com.example.karchunkan.fyp.R;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class RegisterActivity extends AppCompatActivity {

    EditText txtUsername,txtPassword,txtName,txtAddress,txtPhone;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtUsername=(EditText) findViewById(R.id.txtUsername);
        txtPassword=(EditText) findViewById(R.id.txtPassword);
        txtName=(EditText) findViewById(R.id.txtName);
        txtAddress=(EditText) findViewById(R.id.txtAddress);
        txtPhone=(EditText) findViewById(R.id.txtPhone);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Object dataTransfer[] = new Object[3];
                DoRegister modifyData = new DoRegister();
                String url= null;
                try {
                    url = "http://"+ IpAddress.IP_ADDRESS.getIp()+ ApiAddress.Register.getAddress()+
                            "?loginID="+ txtUsername.getText().toString()+
                            "&password="+txtPassword.getText().toString()+
                            "&name="+txtName.getText().toString()+
                            "&address="+ URLEncoder.encode(txtAddress.getText().toString(),"utf-8")+
                            "&telephone="+txtPhone.getText().toString();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                Log.d("url", url);

                dataTransfer[0] = RegisterActivity.this;
                dataTransfer[1] = intent;
                dataTransfer[2] = url;
                modifyData.execute(dataTransfer);
                
//                Intent intent = new Intent(RegisterActivity.this,HomeCustomerActivity.class);
//                intent.putExtra("username",txtUsername.getText().toString());
//                MainActivity.userID="U1"; //
//                startActivity(intent);
            }
        });
    }
}
