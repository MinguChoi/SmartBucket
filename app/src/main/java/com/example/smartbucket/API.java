package com.example.smartbucket;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.smartbucket.Model.Cart;
import com.example.smartbucket.Model.Item;
import com.example.smartbucket.Model.User;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class API {

    public static FirebaseDatabase database = FirebaseDatabase.getInstance();
    public static DatabaseReference DB_USER = database.getReference("users");
    public static DatabaseReference DB_ITEM = database.getReference("items");
    public static DatabaseReference DB_CART = database.getReference("carts");

    public static FirebaseStorage storage = FirebaseStorage.getInstance();
    public static StorageReference ST_ITEM_IMAGE = storage.getReference().child("item_image");

    // Create a test user in DB
    // it's just for quick start so need to be implemented to allow anyone to register to the DB
    public static void createTestUser(String name, String email) {
        // Initialize a testUser
        User testUser = User.getInstance();
        testUser.setName(name);
        testUser.setEmail(email);
        // Map testUser for writing data in DB
        Map<String, Object> update = testUser.toMap();
        // Write data in DB
        DatabaseReference newUser = API.DB_USER.push();
        newUser.setValue(update);
    }

    // Create a test cart in DB
    public static void createTestCart() {
        Cart testCart = Cart.getInstance();
        testCart.setCartNum("6");
        testCart.addItem("MY3dBDyVQP3UuOjNfGi");
        DatabaseReference newCart = DB_CART.push();
        testCart.setUid(newCart.getKey());
        newCart.setValue(testCart.toMap());
    }

    // Uploading item
    public static void addItemToDB() {
        HashMap<String, Object> tmp = new HashMap<>();
        tmp.put("market", "eMart");
        tmp.put("name", "하양송이");
        tmp.put("price", "600원");
        tmp.put("description", "달콤한 하얀치즈 50g");

        DatabaseReference newItem = DB_ITEM.push();
        newItem.setValue(tmp);
    }

    public static void fetchItems(final OnCompletion completion) {
        // Fetch items based on a cart
        // step 1. get a list of items' uid
        // step 2. get the item data from DB by the uid

        DB_CART.child("-MY3f-2AvlU40qD9PeyM").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Cart cart = new Cart(dataSnapshot.getKey(), (HashMap<String, Object>) dataSnapshot.getValue());
//                Cart cart = new Cart();
//                cart.setUid(dataSnapshot.getKey());
//                HashMap<String, Object> value = (HashMap<String, Object>) dataSnapshot.getValue();
//                cart.setCartNum(value.get("cartNum").toString());
//                cart.setItems(value.get("items"));

                List<String> itemUid = cart.getItems();
                Log.d(Utils.TAG, "total item in cart : " + String.valueOf(itemUid.size()));

                List<Item> items = new ArrayList<>();
                DB_ITEM.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            Log.e("firebase", "Error getting data", task.getException());
                        }
                        else {
                            for(DataSnapshot childSnapshot: task.getResult().getChildren()){
                                Item item = new Item(childSnapshot.getKey(), (HashMap)(childSnapshot.getValue()));
                                for(int i=0; i<itemUid.size() ; i++) {
                                    if(itemUid.get(i) != null && item.getUid().equals(itemUid.get(i))) {
                                        items.add(item);
                                        Log.d(Utils.TAG, "this item is added: " + item.getName());
                                    }
                                }
                            }
                            Log.d(Utils.TAG, "total num of items " + String.valueOf(items.size()));
                            completion.onCompletion(items);
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public static void getImage(String itemName, Context ctx, final OnCompletion completion){
        String itemNameJPG = itemName + ".jpg";

        API.ST_ITEM_IMAGE.child(itemNameJPG).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                completion.onCompletion(uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(ctx, "No Such file or Path found!!", Toast.LENGTH_LONG).show();
            }
        });

//        StorageReference imageRef = API.ST_ITEM_IMAGE.child(itemNameJPG);
//        final long ONE_MEGABYTE = 1024 * 1024;
//        imageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
//            @Override
//            public void onSuccess(byte[] bytes) {
//                Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                completion.onCompletion(bmp);
//                //imageView.setImageBitmap(bmp);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                Toast.makeText(ctx, "No Such file or Path found!!", Toast.LENGTH_LONG).show();
//            }
//        });
    }

}
