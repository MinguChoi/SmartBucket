package com.example.smartbucket.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    // Variables
    private static User single_instance = null;
    private String name;
    private String email;
    private String marketID;
    private String cartID;

    // Constructor
    public User() {}

    public User(HashMap<String, Object> map) {
        this.name = map.get("name").toString();
        this.email = map.get("email").toString();
        this.marketID = map.get("marketID").toString();
        this.cartID = map.get("cartID").toString();
    }

    // Get User Instance
    public static User getInstance() {
        if (single_instance == null)
            single_instance = new User();

        return single_instance;
    }

    // Getter
    public String getName() {return name;}
    public String getEmail() {return email;}
    public String getMarketID() {return marketID;}
    public String getCartID() {return cartID;}

    // Setter
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setMarketID(String marketID) { this.marketID = marketID; }
    public void setCartID(String cartID) { this.cartID = cartID; }

    // Custom Method
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("email", email);
        result.put("marketID", marketID);
        result.put("cartID", cartID);

        return result;
    }
}
