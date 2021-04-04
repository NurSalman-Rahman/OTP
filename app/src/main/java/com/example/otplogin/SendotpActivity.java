package com.example.otplogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class SendotpActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_o_t_p);
        final EditText inputMobile = findViewById(R.id.inputMobie);
        Button buttonGetOTP = findViewById(R.id.buttonGetOTP);


        final ProgressBar progressBar = findViewById(R.id.progressBar1);

        //Buttopm clickable satrt

        buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {      // Start of button on clik (View v)

                if (inputMobile.getText().toString().trim().isEmpty()) {

                    Toast.makeText(SendotpActivity.this, "Please Enter Mobile", Toast.LENGTH_SHORT).show();

                    return;
                }


                progressBar.setVisibility(View.VISIBLE);
                buttonGetOTP.setVisibility(View.INVISIBLE);

                //Start on Phone Authuntification provider Function

                PhoneAuthProvider.getInstance().verifyPhoneNumber(


                        // Country Code and Phone Number
                        "+880" + inputMobile.getText().toString(), 60, TimeUnit.SECONDS,
                        SendotpActivity.this,


                        //call back Function :
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {   //Start on cll varification function


                            //Verification Complete start
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                progressBar.setVisibility(View.GONE);
                                buttonGetOTP.setVisibility(View.VISIBLE);

                            }      //Verification Complete End


                            //Verification Verification Failed start
                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                progressBar.setVisibility(View.GONE);
                                buttonGetOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(SendotpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }    //Verification Verification Failed End

                            //Start on Code sent function
                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                progressBar.setVisibility(View.GONE);
                                buttonGetOTP.setVisibility(View.VISIBLE);

                                Intent intent = new Intent(getApplicationContext(), VerifyotpActivity.class);
                                //this line get intent ,stringextara use String format (verify otp)
                                intent.putExtra("mobile", inputMobile.getText().toString());
                                intent.putExtra("verificationId", verificationId);
                                startActivity(intent);

                            }        //End on Code sent function


                        }   //End of call back Function


                );  //End  of Phone Authuntification provider Function


            }   // end of button on clik (View v)

        });                //End of button on clickLisiner


    }  // end of on create method


}  // End of Send Otp class
