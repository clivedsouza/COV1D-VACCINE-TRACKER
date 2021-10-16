package com.devilc.cov1dvaccinetracker;


import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

public class MyCameraActivity extends Activity
    {
        private static final int CAMERA_REQUEST = 1888;
        private ImageView imageView;
        private TextView tv1,tv2,tv3,tv4;
        private static final int MY_CAMERA_PERMISSION_CODE = 100;
        private DBHelper DB;

        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.pofile);
            DB = new DBHelper(this);
            tv1 = findViewById(R.id.textView);
            tv2 = findViewById(R.id.textView2);
            tv3 = findViewById(R.id.textView3);

            init();
            this.imageView = (ImageView)this.findViewById(R.id.imageView1);
            Button photoButton = (Button) this.findViewById(R.id.button1);
            photoButton.setOnClickListener(new View.OnClickListener()
            {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(View v)
                {
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                    {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                    }
                    else
                    {
                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
                    }
                }
            });
        }

        public void init() {
            Cursor res = DB.getdata();
            if(res.getCount()==0){
                Toast.makeText(MyCameraActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while(res.moveToNext()){
                res.moveToLast();
//                buffer.append("Name :"+res.getString(0)+"\n");
//                buffer.append("Contact :"+res.getString(1)+"\n");
//                buffer.append("Date of Birth :"+res.getString(2)+"\n\n");
                tv1.setText("Name :"+res.getString(0).toString());
                tv2.setText("Contact :"+res.getString(1).toString());
                tv3.setText("Date of Birth :"+res.getString(2).toString());

            }
        }


        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
        {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == MY_CAMERA_PERMISSION_CODE)
            {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
                else
                {
                    Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
                }
            }
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data)
        {
            if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
            {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);
            }
        }
    }



