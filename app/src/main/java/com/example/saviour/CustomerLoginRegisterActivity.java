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

public class CustomerLoginRegisterActivity extends AppCompatActivity {
    private Button CustomerLoginButton;
    private Button CustomerRegisterButton;
    private TextView CustomerRegisterLink;
    private TextView CustomerStatus;
    private EditText email_customer;
    private EditText password_customer;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_login_register);
        mAuth = FirebaseAuth.getInstance();
        CustomerLoginButton = (Button) findViewById(R.id.Customer_Login_btn);
        CustomerRegisterButton = (Button) findViewById(R.id.Customer_Register_btn);
        CustomerRegisterLink = (TextView) findViewById(R.id.Donthaveanaccount);
        CustomerStatus = (TextView) findViewById(R.id.CustomerLoginTextview);
        email_customer = (EditText) findViewById(R.id.email_customer);
        password_customer = (EditText) findViewById(R.id.password_customer);
        loadingBar = new ProgressDialog(this);

        CustomerRegisterButton.setVisibility(View.INVISIBLE);
        CustomerRegisterButton.setEnabled(false);



        CustomerRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerLoginButton.setVisibility(View.INVISIBLE);
                CustomerRegisterLink.setVisibility(View.INVISIBLE);
                CustomerStatus.setText("Register Customer");
                CustomerRegisterButton.setVisibility(View.VISIBLE);
                CustomerRegisterButton.setEnabled(true);
            }



        });
        CustomerRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_customer.getText().toString();
                String password = password_customer.getText().toString();
                RegisterCustomer(email, password);
            }
        });
        CustomerLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_customer.getText().toString();
                String password = password_customer.getText().toString();
                signInCustomer(email, password);


            }
        });

    }


    private void signInCustomer(String email, String password) {
        if (TextUtils.isEmpty(email)) {

            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write Email...", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)) {

            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write Password", Toast.LENGTH_SHORT).show();

        } else {
            loadingBar.setTitle("Customer login");
            loadingBar.setMessage("Please wait,while we are checking your credentials..");
            loadingBar.show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer Login Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                                Intent AmbulanceTypeIntent = new Intent(CustomerLoginRegisterActivity.this,AmbulanceTypeActivity.class);
                                startActivity(AmbulanceTypeIntent);

                            } else {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Login Unsuccessful,Please try Again...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                            }

                        }
                    });


        }
    }


    private void RegisterCustomer(String email, String password) {
        if (TextUtils.isEmpty(email)) {

            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write Email...", Toast.LENGTH_SHORT).show();
        }
        if (TextUtils.isEmpty(password)) {

            Toast.makeText(CustomerLoginRegisterActivity.this, "Please write Password", Toast.LENGTH_SHORT).show();

        } else {
            loadingBar.setTitle("Customer Registration");
            loadingBar.setMessage("Please wait,while we are register your data..");
            loadingBar.show();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Customer register Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();



                            } else {
                                Toast.makeText(CustomerLoginRegisterActivity.this, "Registration Unsuccessful,Please try Again...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }

                        }
                    });


        }


    }


    }








