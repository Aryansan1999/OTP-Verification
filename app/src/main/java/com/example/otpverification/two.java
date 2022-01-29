package com.example.otpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class two extends AppCompatActivity {

    private Button btnV;
    String getotpbackend;

    private EditText in1,in2,in3,in4,in5,in6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        getSupportActionBar().hide();
        in1=findViewById(R.id.opt1);
        in2=findViewById(R.id.otp2);
        in3=findViewById(R.id.otp3);
        in4=findViewById(R.id.otp4);
        in5=findViewById(R.id.otp5);
        in6=findViewById(R.id.otp6);

        getotpbackend=getIntent().getStringExtra("backendotp");
        final ProgressBar progessbarVerifyOTP=findViewById(R.id.PBverify);

        btnV=findViewById(R.id.btnVerify);
        TextView textView=findViewById(R.id.show_mobile_number);
        textView.setText(String.format("+91-%s",getIntent().getStringExtra("mobile")));


        btnV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!in1.getText().toString().trim().isEmpty()&&!in2.getText().toString().trim().isEmpty()&&!in3.getText().toString().trim().isEmpty()&&!in4.getText().toString().trim().isEmpty()&&!in5.getText().toString().trim().isEmpty()&&!in6.getText().toString().trim().isEmpty())
                {
                    String entercodeotp=in1.getText().toString()+in2.getText().toString()+in3.getText().toString()+in4.getText().toString()+in5.getText().toString()+in6.getText().toString();
                    if(getotpbackend!=null){

                        progessbarVerifyOTP.setVisibility(View.VISIBLE);
                        btnV.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phoneAuthCredential=PhoneAuthProvider.getCredential(getotpbackend,entercodeotp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progessbarVerifyOTP.setVisibility(View.GONE);
                                btnV.setVisibility(View.VISIBLE);


                                if(task.isSuccessful())
                                {
                                    Intent intent=new Intent((getApplicationContext()),dashboard.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                                else
                                {
                                    Toast.makeText(two.this, "Enter the correct OTP", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else {
                        Toast.makeText(two.this, "Please cheak internet connection", Toast.LENGTH_SHORT).show();
                    }
                   // Toast.makeText(two.this, "OTP verify", Toast.LENGTH_SHORT).show();
                } else
                {
                    Toast.makeText(two.this, "Please Enter all numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberotpmove();



    }

    private void numberotpmove() {

        in1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    in2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        in2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    in3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        in3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    in4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        in4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    in5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        in5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    in6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


}