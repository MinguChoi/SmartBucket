package com.example.smartbucket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;

public class TestActivity extends AppCompatActivity {

    private ImageView testImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testImage = findViewById(R.id.testImage);
        StorageReference testRef = API.ST_ITEM_IMAGE.child("진라면순한맛.jpg");
        final long ONE_MEGABYTE = 1024 * 1024;
        testRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                testImage.setImageBitmap(bmp);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getApplicationContext(), "No Such file or Path found!!", Toast.LENGTH_LONG).show();
            }
        });

        Button pressBTN = findViewById(R.id.pressBTN);
        pressBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //API.createTestUser("Test", "mingu0629@gmail.com");
                API.addItemToDB();
                //API.createTestCart();
            }
        });
    }
}