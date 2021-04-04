package com.example.otplogin;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyotpActivity extends AppCompatActivity {

    private EditText inputCode1, inputCode2, inputCode3, inputCode4, inputCode5, inputCode6;

    public String verificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);


        TextView textMobile = findViewById(R.id.textMobile);
        textMobile.setText(String.format(

                "+880 -%s", getIntent().getStringExtra("mobile")


        ));

        inputCode1 = findViewById(R.id.inputCode1);
        inputCode2 = findViewById(R.id.inputCode2);
        inputCode3 = findViewById(R.id.inputCode3);
        inputCode4 = findViewById(R.id.inputCode4);
        inputCode5 = findViewById(R.id.inputCode5);
        inputCode6 = findViewById(R.id.inputCode6);

        //this function is very important for Request Focue ,Text Wather function called

        setupOTPInputs();


        final ProgressBar progressBar = findViewById(R.id.progressBar2);
        final Button buttonVerify = findViewById(R.id.buttonVerifyOTP);

        verificationId = getIntent().getStringExtra("verificationId");

        buttonVerify.setOnClickListener(new View.OnClickListener()
        {     // start button
            @Override
            public void onClick(View v) {   // start view click

                if (inputCode1.getText().toString().trim().isEmpty() || inputCode2.getText().toString().trim().isEmpty() || inputCode3.getText().toString().trim().isEmpty()
                        || inputCode4.getText().toString().trim().isEmpty() || inputCode5.getText().toString().trim().isEmpty() || inputCode6.getText().toString().trim().isEmpty())

                {

                    Toast.makeText(VerifyotpActivity.this, "Please Enter Valid Code", Toast.LENGTH_SHORT).show();
                return;


            }

            String codo1 =
                    inputCode1.getText().toString() +
                            inputCode2.getText().toString() +
                            inputCode3.getText().toString() +
                            inputCode4.getText().toString() +
                            inputCode5.getText().toString() +


                            inputCode6.getText().toString();
        if(verificationId != null) {


            progressBar.setVisibility(View.VISIBLE);
            buttonVerify.setVisibility(View.INVISIBLE);

            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                    verificationId, codo1
            );

            FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressBar.setVisibility(View.GONE);
                            buttonVerify.setVisibility(View.VISIBLE);
                            if (task.isSuccessful()) {
                                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

                                startActivity(intent1);
                            } else {

                                Toast.makeText(VerifyotpActivity.this, "the verificatio code invalid", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
        }
            }
        });//end of click view


        findViewById(R.id.textResendOTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                resetOTP();

            }
        });


    }  // end of set on click

    private void resetOTP() {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(


                // Country Code and Phone Number
                "+880" + getIntent().getStringExtra("mobile"),60, TimeUnit.SECONDS,
                VerifyotpActivity.this,


                //call back Function :
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks()


                {   //Start on cll varification function


                    //Verification Complete start
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
                    {



                    }      //Verification Complete End




                    //Verification Verification Failed start
                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e)
                    {


                        Toast.makeText(VerifyotpActivity.this,e.getMessage() , Toast.LENGTH_SHORT).show();
                    }    //Verification Verification Failed End

                    //Start on Code sent function
                    @Override
                    public void onCodeSent(@NonNull String newVerificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {


                   verificationId = newVerificationId;
                        Toast.makeText(VerifyotpActivity.this,"OTP Resend", Toast.LENGTH_SHORT).show();

                    }        //End on Code sent function



                }   //End of call back Function



        );  //End  of Phone Authuntification provider Function



    }

    private void setupOTPInputs() {

        inputCode1.addTextChangedListener(new TextWatcher() {   // 2nd start lisener
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().isEmpty()) {

                    inputCode2.requestFocus();
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });     //End 1ND lISENER

        inputCode2.addTextChangedListener(new TextWatcher() {   // 2nd start lisener
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().isEmpty()) {

                    inputCode3.requestFocus();
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });     //End 2ND lISENER

        inputCode3.addTextChangedListener(new TextWatcher() {   // 3rd start lisener
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().isEmpty()) {

                    inputCode4.requestFocus();
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });     //End 3rd lISENER

        inputCode4.addTextChangedListener(new TextWatcher() {   // 2nd start lisener
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().isEmpty()) {

                    inputCode5.requestFocus();
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });     //End 4th lISENER

        inputCode5.addTextChangedListener(new TextWatcher() {   // 2nd start lisener
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.toString().trim().isEmpty()) {

                    inputCode6.requestFocus();
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });     //End five  lISENER


    }


}