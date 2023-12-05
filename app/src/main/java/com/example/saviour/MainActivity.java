package com.example.saviour;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Drawable drawable;
    private Button WelcomeDriverButton;
    private Button WelcomecustomerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=findViewById(R.id.imageview);
        drawable=getResources().getDrawable(R.drawable.ambulance);
        imageView.setImageDrawable(drawable);

        WelcomecustomerButton=(Button) findViewById(R.id.welcome_customer_btn);
        WelcomeDriverButton = (Button) findViewById(R.id.welcome_driver_btn);

        WelcomecustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginRegisterCustomerIntent = new Intent(MainActivity.this, CustomerLoginRegisterActivity.class);
                startActivity(LoginRegisterCustomerIntent);

            }

        });
        WelcomeDriverButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginRegisterDriverIntent = new Intent(MainActivity.this, DriverLoginRegisterActivity.class);
                startActivity(LoginRegisterDriverIntent);

            }
        });

        }

        }




