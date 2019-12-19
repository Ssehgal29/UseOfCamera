package com.example.karan.useofcamera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
ImageButton i1,i2;
ImageView iv;
Bitmap bmp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        i1=findViewById(R.id.imageButton);
        i2=findViewById(R.id.imageButton2);
        iv=findViewById(R.id.imageView);

        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//size of imae should b low
                startActivityForResult(intent,777);// 1. parameter intent 2. request code that can be any no.
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getApplicationContext().setWallpaper(bmp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==777 && resultCode==RESULT_OK && data!=null)
        {
            Bundle b = data.getExtras();
            bmp = (Bitmap) b.get("data");
            iv.setImageBitmap(bmp);
        }
        else
        {
            Toast.makeText(this, "Try Again Bitch", Toast.LENGTH_SHORT).show();
        }
    }

}
