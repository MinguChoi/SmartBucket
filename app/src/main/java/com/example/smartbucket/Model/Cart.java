package com.example.smartbucket.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart {
    // Variables
    private static Cart single_instance = null;
    private String uid;
    private String cartNum;
    private List<String> items;

    // Constructor
    public Cart() {this.items = new ArrayList<>();}

    public Cart(String uid, HashMap<String, Object> map) {
        this.uid = uid;
        this.cartNum = map.get("cartNum").toString();
        this.items = new ArrayList<>((ArrayList<String>) map.get("items"));

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
    public List<String> getItems() {return items;}

    // Setter
    public void setUid(String uid) { this.uid = uid; }
    public void setCartNum(String cartNum) { this.cartNum = cartNum; }
    public void setItems(List<String> items) {
        if (items != null) {
            this.items = items;
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
        result.put("items", items);

        return result;
    }
}
