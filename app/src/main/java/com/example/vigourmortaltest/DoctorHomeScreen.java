package com.example.vigourmortaltest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class DoctorHomeScreen extends AppCompatActivity {

    CodeScanner codeScanner;
    CodeScannerView codeScannerView;
    TextView resultdata;
    Button getdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_home_screen);


        codeScannerView = findViewById(R.id.scannerView);
        codeScanner = new CodeScanner(this,codeScannerView);
        resultdata = findViewById(R.id.result);
        getdata = findViewById(R.id.getData);



        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        resultdata.setText(result.getText());
                    }
                });
            }
        });
        codeScannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codeScanner.startPreview();
            }
        });

        getdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              isUserExists();
            }
        });

    }

    private void isUserExists() {
        final String userEnteredPhone =  resultdata.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Patients");

        Query checkUser = reference.orderByChild("phone").equalTo(userEnteredPhone);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    String emaiFromDB = dataSnapshot.child(userEnteredPhone).child("email").getValue(String.class);
                    String bloodFromDB = dataSnapshot.child(userEnteredPhone).child("blood").getValue(String.class);
                    String nameFromDB = dataSnapshot.child(userEnteredPhone).child("name").getValue(String.class);
                    String dobFromDB = dataSnapshot.child(userEnteredPhone).child("diplaydate").getValue(String.class);
                    String phoneFromDB = dataSnapshot.child(userEnteredPhone).child("phone").getValue(String.class);


                    Intent intent = new Intent(getApplicationContext(),DoctorUpdatePatient.class);

                   intent.putExtra("name",nameFromDB);
                   intent.putExtra("email",emaiFromDB);
                   intent.putExtra("blood",bloodFromDB);
                   intent.putExtra("dob",dobFromDB);
                   intent.putExtra("phoneNo",phoneFromDB);

                    startActivity(intent);

                }
                else {
                    resultdata.setError("No Such User Exits");
                    resultdata.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        requestForCamera();
    }

    private void requestForCamera() {
        Dexter.withActivity(this).withPermission(Manifest.permission.CAMERA).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse response) {
                codeScanner.startPreview();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse response) {
                Toast.makeText(DoctorHomeScreen.this,"Camera Permission is required",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                token.continuePermissionRequest();

            }
        }).check();



    }
}
