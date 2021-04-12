package com.example.smartbucket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smartbucket.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class BucketActivity extends AppCompatActivity {

    private TextView placeTXT, cartTXT;
    private ListView itemListView;
    private ItemAdapter adapter;
    private List<Item> items;
    private Button payBTN, cancelBTN, finishBTN;
    private Dialog confirmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bucket);

        init();
        displayListView();

        payBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
    }

    public void init() {
        Intent intent = getIntent();

        placeTXT = findViewById(R.id.place_bucket_TXT);
        cartTXT = findViewById(R.id.cart_bucket_TXT);
        itemListView = findViewById(R.id.items_bucket_ListView);
        payBTN = findViewById(R.id.pay_bucket_BTN);

        confirmDialog = new Dialog(this);
        confirmDialog.setContentView(R.layout.custom_dialog);
        cancelBTN = (Button) confirmDialog.findViewById(R.id.cancel_dialog_BTN);
        finishBTN = (Button) confirmDialog.findViewById(R.id.finish_dialog_BTN);

        placeTXT.setText(intent.getStringExtra("place"));
        cartTXT.setText(intent.getStringExtra("cart"));
    }

    public void displayListView() {
        API.fetchItems(new OnCompletion() {
            @Override
            public void onCompletion(Object object) {
                adapter = new ItemAdapter((ArrayList<Item>) object, BucketActivity.this);
                itemListView.setAdapter(adapter);
            }
        });
    }

    public void showDialog() {
        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
        finishBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog.dismiss();
            }
        });
        confirmDialog.show();
    }
}