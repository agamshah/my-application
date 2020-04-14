package com.example.vigourmortaltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class AboutUs extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    String user, dob, email,phoneNo, blood;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_about);

        Intent intent = getIntent();
        user = intent.getStringExtra("user");
        email = intent.getStringExtra("email");
        phoneNo = intent.getStringExtra("phoneNo");
        dob = intent.getStringExtra("dob");
        blood = intent.getStringExtra("blood");
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
                Intent intent = new Intent(AboutUs.this,PatientHomeScreen.class);
                intent.putExtra("phoneNo",phoneNo);
                intent.putExtra("user",user);
                intent.putExtra("email",email);
                intent.putExtra("blood",blood);
                intent.putExtra("dob",dob);
                intent.putExtra("phoneNo",phoneNo);
                startActivity(intent);
                break;
            case R.id.nav_genqr:
                Intent intent1 = new Intent(AboutUs.this,GenerateQr.class);
                intent1.putExtra("phoneNo",phoneNo);
                intent1.putExtra("user",user);
                intent1.putExtra("email",email);
                intent1.putExtra("blood",blood);
                intent1.putExtra("dob",dob);
                intent1.putExtra("phoneNo",phoneNo);
                startActivity(intent1);
                break;
            case R.id.nav_profile:
                Intent intent2 = new Intent(AboutUs.this,PatientProfile.class);
                intent2.putExtra("phoneNo",phoneNo);
                intent2.putExtra("user",user);
                intent2.putExtra("email",email);
                intent2.putExtra("blood",blood);
                intent2.putExtra("dob",dob);
                intent2.putExtra("phoneNo",phoneNo);
                startActivity(intent2);
                break;
            case R.id.nav_finddr:
                Intent intent3 = new Intent(AboutUs.this,FindDoctor.class);
                intent3.putExtra("phoneNo",phoneNo);
                intent3.putExtra("user",user);
                intent3.putExtra("email",email);
                intent3.putExtra("blood",blood);
                intent3.putExtra("dob",dob);
                intent3.putExtra("phoneNo",phoneNo);
                startActivity(intent3);
                break;
            case R.id.nav_appoint:
                Intent intent5 = new Intent(AboutUs.this,BookAppoinment.class);
                intent5.putExtra("phoneNo",phoneNo);
                intent5.putExtra("user",user);
                intent5.putExtra("email",email);
                intent5.putExtra("blood",blood);
                intent5.putExtra("dob",dob);
                intent5.putExtra("phoneNo",phoneNo);
                startActivity(intent5);
                break;
            case R.id.nav_contact:
                Intent intent6 = new Intent(AboutUs.this,ContactUs.class);
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
