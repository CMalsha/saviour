package com.example.saviour;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DriverLoginRegisterActivity extends AppCompatActivity {
    private Button DriverLoginButton;
    private Button DriverRegisterButton;
    private TextView DriverRegisterLink;
    private TextView DriverStatus;
    private EditText EmailDriver;
    private EditText password_driver;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_login_register);
        mAuth =FirebaseAuth.getInstance();
        DriverLoginButton = (Button) findViewById(R.id.Driver_Login_btn);
        DriverRegisterButton = (Button) findViewById(R.id.driver_register_btn);
        DriverRegisterLink = (TextView) findViewById(R.id.Donthaveanaccount);
        DriverStatus = (TextView) findViewById(R.id.Driver_Login_Textview);
        EmailDriver = (EditText) findViewById(R.id.email_driver);
        password_driver = (EditText) findViewById(R.id.password_driver);
        loadingBar =new ProgressDialog(this);

        DriverRegisterButton.setVisibility(View.INVISIBLE);
        DriverRegisterButton.setEnabled(false);

        DriverRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DriverLoginButton.setVisibility(View.INVISIBLE);
                DriverRegisterLink.setVisibility(View.INVISIBLE);
                DriverStatus.setText("Register Driver");
                DriverRegisterButton.setVisibility(View.VISIBLE);
                DriverRegisterButton.setEnabled(true);
            }
        });

        DriverRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EmailDriver.getText().toString();
                String password = password_driver.getText().toString();
                RegisterDriver(email, password);
            }
        });

        DriverLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = EmailDriver.getText().toString();
                String password = password_driver.getText().toString();
                SignInDriver(email,password);
            }
        });
    }

    private void SignInDriver(String email, String password) {
        if (TextUtils.isEmpty(email)) {

            Toast.makeText(DriverLoginRegisterActivity.this, "Please write Email...", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)) {

            Toast.makeText(DriverLoginRegisterActivity.this, "Please write Password", Toast.LENGTH_SHORT).show();

        } else {
            AppCompatActivity parent = this;
            loadingBar.setTitle("Driver Login");
            loadingBar.setMessage("Please wait,while we are checking your credentials..");
            loadingBar.show();
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver Logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent intent = new Intent(parent, AmbulanceTypeActivity.class);
                                parent.startActivity(intent);
                            }
                            else {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Login Unsuccessful,Please try Again...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }

                        }
                    });


        }
    }

    private void RegisterDriver(String email, String password) {
        if (TextUtils.isEmpty(email)) {

            Toast.makeText(DriverLoginRegisterActivity.this, "Please write Email...", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)) {

            Toast.makeText(DriverLoginRegisterActivity.this, "Please write Password", Toast.LENGTH_SHORT).show();

        } else {
            loadingBar.setTitle("Driver Registration");
            loadingBar.setMessage("Please wait,while we are register your data..");
            loadingBar.show();
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(DriverLoginRegisterActivity.this, "Driver register Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else
                            {
                                Toast.makeText(DriverLoginRegisterActivity.this, "Registration Unsuccessful,Please try Again...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }

                        }
                    });



        }
    }
}

