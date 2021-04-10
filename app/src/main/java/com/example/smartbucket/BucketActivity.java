package com.example.smartbucket;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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
        // Get Items from the server in real time but manually typed for testing
        Item a = new Item();
        a.setName("진라면");
        a.setDescription("120G 5개입");
        a.setPrice("2750원");
        Item b = new Item();
        b.setName("예감");
        b.setDescription("치즈맛");
        b.setPrice("1200원");
        Item c = new Item();
        c.setName("서울우유");
        c.setDescription("1.25L");
        c.setPrice("2200원");
        items = new ArrayList<>();
        items.add(a);
        items.add(b);
        items.add(c);

        adapter = new ItemAdapter(items, BucketActivity.this);
        itemListView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }

    public void showDialog() {
        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        finishBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }
}