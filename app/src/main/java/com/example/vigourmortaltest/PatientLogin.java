package com.example.vigourmortaltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PatientLogin extends AppCompatActivity {
    TextView registertext, hello, welcome, signin;
    TextInputLayout phone,password;
    Button login;

    private Boolean validatePhone(){
        String val = phone.getEditText().getText().toString();
        if(val.isEmpty()){
            phone.setError("Please Enter Phone Number");
            return false;
        }
        else if(val.length()>10 ){
            phone.setError("Please Enter 10 Digits Only");
            return false;
        }
        else if(val.length()<10 ){
            phone.setError("Please Enter 10 Digits Only");
            return false;
        }
        else{
            phone.setError(null);
            phone.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validatePassword(){
        String val = password.getEditText().getText().toString();
        String passwordverification = ("^" +
                "(?=.*[0-9])"+          //at least 1 digit
                "(?=.*[a-z])"+          //at least 1 lower case letter
                "(?=.*[A-Z])"+          //at least 1 upper case letter
                "(?=.*[@#$%^&+=])"+     //at least 1 special character
                "(?=\\S+$)"+            //no white spaces
                ".{6,15}"+                //at least 6 characters
                "$");

        if(val.isEmpty()){
            password.setError("Field Cannot be Empty");
            return false;
        }
        else if(!val.matches(passwordverification)){
            password.setError("Password Must be more than 6 character and should contain at least 1 digit, " +
                    "1 lower case letter, 1 upper case letter & 1 special character");
            return false;
        }
        else
        {
            password.setError(null);
            password.setErrorEnabled(false);
            return true;
        }

    }

    public void loginUser(){
        if(!validatePhone() | !validatePassword()){
            return;
        }
        else{
            isUser();
        }
    }
    private void isUser() {
        final String userEnteredPhone =  phone.getEditText().getText().toString();
        final String userEnteredPassword =  password.getEditText().getText().toString();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Patients");

        Query checkUser = reference.orderByChild("phone").equalTo(userEnteredPhone);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    phone.setError(null);
                    phone.setErrorEnabled(false);

                    String passwordFromDB = dataSnapshot.child(userEnteredPhone).child("password").getValue(String.class);

                    if(passwordFromDB.equals(userEnteredPassword)){

                        password.setError(null);
                        password.setErrorEnabled(false);

                        String emaiFromDB = dataSnapshot.child(userEnteredPhone).child("email").getValue(String.class);
                        String bloodFromDB = dataSnapshot.child(userEnteredPhone).child("blood").getValue(String.class);
                        String nameFromDB = dataSnapshot.child(userEnteredPhone).child("name").getValue(String.class);
                        String dobFromDB = dataSnapshot.child(userEnteredPhone).child("diplaydate").getValue(String.class);
                        String phoneFromDB = dataSnapshot.child(userEnteredPhone).child("phone").getValue(String.class);


                        Intent intent = new Intent(getApplicationContext(),PatientHomeScreen.class);

                        intent.putExtra("name",nameFromDB);
                        intent.putExtra("email",emaiFromDB);
                        intent.putExtra("blood",bloodFromDB);
                        intent.putExtra("dob",dobFromDB);
                        intent.putExtra("phoneNo",phoneFromDB);

                        startActivity(intent);

                    }
                    else{
                        password.setError("Please Enter a Valid Password");
                        password.requestFocus();
                    }
                }
                else {
                    phone.setError("No Such User Exits");
                    password.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);

        registertext = findViewById(R.id.logintextfornewuser);
        phone  = findViewById(R.id.Patientphone);
        password = findViewById(R.id.patientpassword);
        login =  findViewById(R.id.PatientBtn);
        hello = findViewById(R.id.logintext);
        welcome = findViewById(R.id.logintext2);
        signin = findViewById(R.id.signinline);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        registertext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),PatientRegistration.class));
            }
        });



    }
}
