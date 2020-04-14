package com.example.vigourmortaltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class PatientHomeScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    TextView textView2, textView1, textView8, textView3, textView4, textView5, textView6;
    ImageView logoofhomescreen, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String user, email, blood, dob, phoneNo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home_screen);

        textView2 = findViewById(R.id.textView2);
        textView1 = findViewById(R.id.textView1);
        textView8 = findViewById(R.id.textView8);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);

        logoofhomescreen = findViewById(R.id.logoofhome);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

        Intent intent = getIntent();
        user = intent.getStringExtra("name");
        email = intent.getStringExtra("email");
        blood = intent.getStringExtra("blood");
        dob = intent.getStringExtra("dob");
        phoneNo = intent.getStringExtra("phoneNo");


    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return false;
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {

        switch (item.getItemId()){
            case R.id.nav_home:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.nav_profile:
                Intent intent = new Intent(PatientHomeScreen.this,PatientProfile.class);
                intent.putExtra("user",user);
                intent.putExtra("email",email);
                intent.putExtra("blood",blood);
                intent.putExtra("dob",dob);
                intent.putExtra("phoneNo",phoneNo);
                startActivity(intent);
                break;
            case R.id.nav_genqr:
                Intent intent1 = new Intent(PatientHomeScreen.this,GenerateQr.class);
                intent1.putExtra("phoneNo",phoneNo);
                intent1.putExtra("user",user);
                intent1.putExtra("email",email);
                intent1.putExtra("blood",blood);
                intent1.putExtra("dob",dob);
                startActivity(intent1);
                break;
            case R.id.nav_about:
                Intent intent2 = new Intent(PatientHomeScreen.this,AboutUs.class);
                intent2.putExtra("phoneNo",phoneNo);
                intent2.putExtra("user",user);
                intent2.putExtra("email",email);
                intent2.putExtra("blood",blood);
                intent2.putExtra("dob",dob);
                startActivity(intent2);
                break;
            case R.id.nav_finddr:
                Intent intent3 = new Intent(PatientHomeScreen.this,FindDoctor.class);
                intent3.putExtra("phoneNo",phoneNo);
                intent3.putExtra("user",user);
                intent3.putExtra("email",email);
                intent3.putExtra("blood",blood);
                intent3.putExtra("dob",dob);
                startActivity(intent3);
                break;
            case R.id.nav_appoint:
                Intent intent5 = new Intent(PatientHomeScreen.this,BookAppoinment.class);
                intent5.putExtra("phoneNo",phoneNo);
                intent5.putExtra("user",user);
                intent5.putExtra("email",email);
                intent5.putExtra("blood",blood);
                intent5.putExtra("dob",dob);
                startActivity(intent5);
                break;
            case R.id.nav_contact:
                Intent intent6 = new Intent(PatientHomeScreen.this,ContactUs.class);
                intent6.putExtra("phoneNo",phoneNo);
                intent6.putExtra("user",user);
                intent6.putExtra("email",email);
                intent6.putExtra("blood",blood);
                intent6.putExtra("dob",dob);
                startActivity(intent6);
                break;
            case R.id.nav_logout:
                Intent intent4 = new Intent(getApplicationContext(),PatientLogin.class);
                startActivity(intent4);
                break;
        }

        drawerLayout = findViewById(R.id.drawerlayout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }


}
