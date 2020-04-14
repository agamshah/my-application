package com.example.vigourmortaltest;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputLayout;

public class GenerateQr extends AppCompatActivity {

    TextInputLayout getInput;
    Button generatebtn;
    ImageView qrImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_qr);

        getInput = findViewById(R.id.generateQr);
        generatebtn = findViewById(R.id.GenerateBut);
        qrImage = findViewById(R.id.qrImage);

        Intent intent = getIntent();
        String user_phoneNo = intent.getStringExtra("phoneNo");

        getInput.getEditText().setText(user_phoneNo);

        generatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = getInput.getEditText().getText().toString();
                QRGEncoder qrgEncoder = new QRGEncoder(data,null, QRGContents.Type.PHONE,1000);
                try {
                    // Getting QR-Code as Bitmap
                    Bitmap qrbits = qrgEncoder.getBitmap();
                    // Setting Bitmap to ImageView
                    qrImage.setImageBitmap(qrbits);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
