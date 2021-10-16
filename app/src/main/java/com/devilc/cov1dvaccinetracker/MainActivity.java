package com.devilc.cov1dvaccinetracker;


import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.app.DatePickerDialog;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {

    public Button button;
    public Button btnn;
    public Button botn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        botn = (Button) findViewById(R.id.button69) ;
        botn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivitykt.class);
                startActivity(intent);
            }
        });


        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LocActivity.class);
                startActivity(intent);
            }
        });

        Button btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMyCameraActivity();
            }
        });

        btnn = (Button) findViewById(R.id.button4);
        btnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBMain();
            }
        });

    }

    public void DBMain(){
        Intent intent = new Intent(this, DBMain.class);
        startActivity(intent);
    }

    public void openMyCameraActivity() {
        Intent intent = new Intent(this, MyCameraActivity.class);
        startActivity(intent);
    }



}