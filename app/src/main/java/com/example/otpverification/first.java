package com.example.otpverification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class first extends AppCompatActivity {

    EditText enternumber;
    Button getoptbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        enternumber=findViewById(R.id.enter_mobile_number);
        getoptbutton=findViewById(R.id.btnSubmit);

        ProgressBar progressBar=findViewById(R.id.PBsubmit);

        getoptbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!enternumber.getText().toString().trim().isEmpty()) {
                    if ((enternumber.getText().toString().trim()).length() == 10) {

                        progressBar.setVisibility(View.VISIBLE);
                        getoptbutton.setVisibility(View.INVISIBLE);


                        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + enternumber.getText().toString(), 60, TimeUnit.SECONDS, first.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                getoptbutton.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                getoptbutton.setVisibility(View.VISIBLE);

                                Toast.makeText(first.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                      //  super.onCodeSent(s, forceResendingToken);
                                        progressBar.setVisibility(View.GONE);
                                        getoptbutton.setVisibility(View.VISIBLE);

                                        Intent intent = new Intent(getApplicationContext(), two.class);
                                        intent.putExtra("mobile", enternumber.getText().toString());
                                        intent.putExtra("backendotp",backendotp);

                                        startActivity(intent);
                                    }

                                }
                        );


                    } else {
                        Toast.makeText(first.this, "Please Enter correct Phone number", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(first.this, "Enter Phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}