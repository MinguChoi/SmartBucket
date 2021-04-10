package com.example.smartbucket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    private ConstraintLayout welcomeLAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeLAY = findViewById(R.id.welcomeLAY);

        welcomeLAY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });
    }
}