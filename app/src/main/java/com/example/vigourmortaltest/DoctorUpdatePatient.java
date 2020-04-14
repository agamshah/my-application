package com.example.vigourmortaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class DoctorUpdatePatient extends AppCompatActivity {

    EditText textforname;
    String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_update_patient);

        textforname = findViewById(R.id.editextname);


        ShowAllUserData();



    }

    private void ShowAllUserData() {
        Intent intent = getIntent();
        user = intent.getStringExtra("name");

        textforname.setText(user);

    }
}
