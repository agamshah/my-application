package com.example.vigourmortaltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class PatientProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView nametext, emailtext, phonetext, bloodtext, birthtext;
    public static final String TAG = "PatientProfile";
    TextInputLayout fullname,email1,PhoneNo;
    Spinner spinnerBlood;
    TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListner;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    String user, dob, email,phoneNo, blood;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        nametext = findViewById(R.id.nametext);
        emailtext = findViewById(R.id.emailtext);
        phonetext = findViewById(R.id.phonetext);
        bloodtext = findViewById(R.id.bloodtext);
        birthtext = findViewById(R.id.birthtext);
        spinnerBlood = findViewById(R.id.SpinnerCanvas);
        mDisplayDate = findViewById(R.id.tvDate);


        fullname = findViewById(R.id.fullname);
        email1 = findViewById(R.id.email);
        PhoneNo = findViewById(R.id.PhoneNo);


        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_profile);

        ShowAllUserData();

        mDisplayDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Calendar cal = Calendar.getInstance();

                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                DatePickerDialog dialog = new DatePickerDialog(PatientProfile.this,
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

    private void ShowAllUserData() {
        Intent intent = getIntent();
         user = intent.getStringExtra("user");
         email = intent.getStringExtra("email");
         phoneNo = intent.getStringExtra("phoneNo");
         dob = intent.getStringExtra("dob");
         blood = intent.getStringExtra("blood");

        fullname.getEditText().setText(user);
        email1.getEditText().setText(email);
        PhoneNo.getEditText().setText(phoneNo);
        mDisplayDate.setText(dob);

    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.nav_home:
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent intent = new Intent(PatientProfile.this,PatientHomeScreen.class);
                intent.putExtra("phoneNo",phoneNo);
                intent.putExtra("user",user);
                intent.putExtra("email",email);
                intent.putExtra("blood",blood);
                intent.putExtra("dob",dob);
                intent.putExtra("phoneNo",phoneNo);
                startActivity(intent);
                break;
            case R.id.nav_genqr:
                Intent intent1 = new Intent(PatientProfile.this,GenerateQr.class);
                intent1.putExtra("phoneNo",phoneNo);
                intent1.putExtra("user",user);
                intent1.putExtra("email",email);
                intent1.putExtra("blood",blood);
                intent1.putExtra("dob",dob);
                intent1.putExtra("phoneNo",phoneNo);
                startActivity(intent1);
                break;
            case R.id.nav_about:
                Intent intent2 = new Intent(PatientProfile.this,AboutUs.class);
                intent2.putExtra("phoneNo",phoneNo);
                intent2.putExtra("user",user);
                intent2.putExtra("email",email);
                intent2.putExtra("blood",blood);
                intent2.putExtra("dob",dob);
                intent2.putExtra("phoneNo",phoneNo);
                startActivity(intent2);
                break;
            case R.id.nav_finddr:
                Intent intent3 = new Intent(PatientProfile.this,FindDoctor.class);
                intent3.putExtra("phoneNo",phoneNo);
                intent3.putExtra("user",user);
                intent3.putExtra("email",email);
                intent3.putExtra("blood",blood);
                intent3.putExtra("dob",dob);
                intent3.putExtra("phoneNo",phoneNo);
                startActivity(intent3);
                break;
            case R.id.nav_appoint:
                Intent intent5 = new Intent(PatientProfile.this,BookAppoinment.class);
                intent5.putExtra("phoneNo",phoneNo);
                intent5.putExtra("user",user);
                intent5.putExtra("email",email);
                intent5.putExtra("blood",blood);
                intent5.putExtra("dob",dob);
                intent5.putExtra("phoneNo",phoneNo);
                startActivity(intent5);
                break;
            case R.id.nav_contact:
                Intent intent6 = new Intent(PatientProfile.this,ContactUs.class);
                intent6.putExtra("phoneNo",phoneNo);
                intent6.putExtra("user",user);
                intent6.putExtra("email",email);
                intent6.putExtra("blood",blood);
                intent6.putExtra("dob",dob);
                intent6.putExtra("phoneNo",phoneNo);
                startActivity(intent6);
                break;
            case R.id.nav_logout:
                Intent intent4 = new Intent(getApplicationContext(),PatientLogin.class);
                startActivity(intent4);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



}
