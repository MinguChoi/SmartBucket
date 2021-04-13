package com.example.smartbucket.Model;

import android.util.Log;

import com.example.smartbucket.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Cart {
    // Variables
    private static Cart single_instance = null;
    private String uid;
    private String cartNum;
    private String isTaken = "false";
    private List<String> items;

    // Constructor
    public Cart() {this.items = new ArrayList<>();}

    public Cart(String uid, HashMap<String, Object> map) {
        this.uid = uid;
        this.cartNum = map.get("cartNum").toString();
        this.isTaken = map.get("isTaken").toString();
        if((map.get("items")) instanceof ArrayList) {
            this.items = (ArrayList<String>)map.get("items");
        }
        if((map.get("items")) instanceof HashMap) {
            this.items = new ArrayList<>(((HashMap<String, String>)map.get("items")).values());
        }
    }

    // Get Cart Instance
    public static Cart getInstance() {
        if (single_instance == null)
            single_instance = new Cart();

        return single_instance;
    }

    // Getter
    public String getUid() {return uid;}
    public String getCartNum() {return cartNum;}
    public String getIsTaken() {return isTaken;}
    public List<String> getItems() {return items;}

    // Setter
    public void setUid(String uid) { this.uid = uid; }
    public void setCartNum(String cartNum) { this.cartNum = cartNum; }
    public void setIsTaken(String isTaken) { this.isTaken = isTaken; }
    public void setItems(Object items) {
        if (items != null) {
            this.items = (ArrayList<String>)items;
            //HashMap<String, String> map = (HashMap<String, String>) items;
            //this.items = new ArrayList<>(map.values());
        }
    }

    // Add item
    public void addItem(String item_id) {
        if (this.items == null) {
            this.items = new ArrayList<String>();
        }
        this.items.add(item_id);
    }

    // Delete item
    public void deleteItem(String item_id) {
        this.items.remove(item_id);
    }

    // Custom Method
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("cartNum", cartNum);
        result.put("isTaken", isTaken);
        result.put("items", items);

        return result;
    }
}
