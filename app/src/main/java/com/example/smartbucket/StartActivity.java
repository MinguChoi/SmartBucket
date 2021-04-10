package com.example.smartbucket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

public class StartActivity extends AppCompatActivity {

    private Spinner cartSpinner;
    private ImageButton cartBTN;
    private EditText placeETXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        init();

        cartBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.isEmpty(placeETXT)) {
                    Utils.makeToast(StartActivity.this, "쇼핑 장소를 선택해주세요!");
                } else {
                    Intent intent = new Intent(StartActivity.this, BucketActivity.class);
                    intent.putExtra("place", placeETXT.getText().toString());
                    intent.putExtra("cart", cartSpinner.getSelectedItem().toString());
                    startActivity(intent);
                }
            }
        });
    }

    public void init(){
        cartSpinner = findViewById(R.id.cartSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.carts_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cartSpinner.setAdapter(adapter);

        cartBTN = findViewById(R.id.cart_start_BTN);

        placeETXT = findViewById(R.id.place_start_ETXT);
    }
}