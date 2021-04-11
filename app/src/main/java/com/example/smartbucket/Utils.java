package com.example.smartbucket;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Utils {

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference DB_USER = database.getReference("users");
    public static DatabaseReference DB_ITEM = database.getReference("items");

    public static boolean isEmpty(EditText etxt) {
        if(etxt.getText().toString().trim().length()>0)
            return false;

        return true;
    }
    public static void makeToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }
}
