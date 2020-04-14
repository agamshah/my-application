package com.example.vigourmortaltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class DoctorRegistration extends AppCompatActivity {

    TextView logo_name,logo_name1;
    TextInputLayout regName, regEmail, regPhone, regPassword;
    public static final String TAG = "DoctorRegistration";
    Button regBtn;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListner;
    Spinner spinnerDepart;
    String gender="";
    RadioGroup mGender;
    String strGender;
    RadioButton mGenderoption;
    TextView alreadyLogin;

    private Boolean validateName(){
        String val = regName.getEditText().getText().toString();
        if(val.isEmpty()){
            regName.setError("Field Cannot Be Empty");
            return false;
        }
        else{
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail(){
        String val = regEmail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(val.isEmpty()){
            regEmail.setError("Field Cannot Be Empty");
            return false;
        }
        else if(!val.matches(emailPattern)){
            regEmail.setError("Invalid Email Address");
            return true;
        }
        else{
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatephone(){
        String val = regPhone.getEditText().getText().toString();
        if(val.isEmpty()){
            regPhone.setError("Field cannot be empty");
            return false;
        }
        else if(val.length()>10){
            regPhone.setError("Please Enter 10 Digits Only");
            return false;
        }
        else if(val.length()<10){
            regPhone.setError("Please Enter 10 Digits Only");
            return false;
        }
        else{
            regPhone.setError(null);
            regPhone.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatepassword(){
        String val = regPassword.getEditText().getText().toString();
        String passwordverification = ("^" +
                "(?=.*[0-9])"+          //at least 1 digit
                "(?=.*[a-z])"+          //at least 1 lower case letter
                "(?=.*[A-Z])"+          //at least 1 upper case letter
                "(?=.*[@#$%^&+=])"+     //at least 1 special character
                "(?=\\S+$)"+            //no white spaces
                ".{6,15}"+                //at least 6 characters
                "$");

        if(val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(passwordverification)){
            regPassword.setError("Password Must be more than 6 character and should contain at least 1 digit, " +
                    "1 lower case letter, 1 upper case letter & 1 special character");
            return false;
        }
        else{
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }

    }

    private Boolean validatedob(){
        String val = mDisplayDate.getText().toString();
        if(val.isEmpty()){
            mDisplayDate.setError("Please Select appropriate date");
            return false;
        }
        else{ ;
            mDisplayDate.setError(null);
            return true;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_registration);

        logo_name = findViewById(R.id.logo_name);
        logo_name1 = findViewById(R.id.logo_name1);
        regName = findViewById(R.id.regname);
        regEmail = findViewById(R.id.regemail);
        regPhone = findViewById(R.id.regphone);
        regPassword = findViewById(R.id.Password);
        regBtn = findViewById(R.id.reg_btn);
        spinnerDepart = findViewById(R.id.SpinnerCanva);
        mDisplayDate = findViewById(R.id.tvDate);
        alreadyLogin = findViewById(R.id.alreadytext);

        FirebaseApp.initializeApp(this);

        alreadyLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),DoctorLogin.class));

            }
        });


        mGender = findViewById(R.id.rg_Gender);

        mGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                mGenderoption = mGender.findViewById(i);
                switch (i){
                    case R.id.Male:
                        strGender = mGenderoption.getText().toString();
                        break;
                    case R.id.Female:
                        strGender = mGenderoption.getText().toString();
                        break;
                    default:
                }
            }
        });

        FirebaseApp.initializeApp(DoctorRegistration.this);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference doctorref = FirebaseDatabase.getInstance().getReference("Doctor");
                doctorref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int childCount = (int) dataSnapshot.getChildrenCount();
                        Log.d("children count",String.valueOf(childCount));

                        if(!validateName() | !validateEmail() | !validatephone() | !validatepassword() | !validatedob()){
                            return;
                        }
                        final String phone = regPhone.getEditText().getText().toString();

                        if(!dataSnapshot.child(phone).exists())
                        {
                            final String name = regName.getEditText().getText().toString();
                            final String email = regEmail.getEditText().getText().toString();
                            final String password = regPassword.getEditText().getText().toString();

                            final String displaydate = mDisplayDate.getText().toString();
                            String depart = spinnerDepart.getSelectedItem().toString();


                            DoctorUserHelperClass doctor = new DoctorUserHelperClass(name,email,phone,password,displaydate,depart,strGender);

                            doctorref.child(phone).setValue(doctor);
                            Log.d("name",name);
                            Toast.makeText(getApplicationContext(), "Doctor Registered Succesfully Please login In",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(),DoctorLogin.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Oops Enter User is already registered ",Toast.LENGTH_LONG).show();
                        }


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

        mDisplayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();

                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(DoctorRegistration.this,
                        android.R.style.Theme_WallpaperSettings, mDateSetListner,day,month,year);

                dialog.getDatePicker().setMaxDate(System.currentTimeMillis() );
                dialog.show();
            }
        });

        mDateSetListner = new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                Log.d(TAG,"OnDateSet: yy/mm/dd" +year+ "/" +month+ "/" +dayOfMonth);

                String date = dayOfMonth + "/" + month + "/" +year;
                mDisplayDate.setText(date);
            }
        };




    }
}
