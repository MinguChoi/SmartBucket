package com.example.smartbucket.Model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Item implements Serializable {
    // Variables
    private String uid;
    private String market;
    private String name;
    private String price;
    private String description;

    // Constructor
    public Item() {}

    public Item(String uid, HashMap<String, Object> map) {
        this.uid = uid;
        this.market = (String) map.get("market");
        this.name = (String) map.get("name");
        this.price = (String )map.get("price");
        this.description = (String) map.get("description");
    }

    // Getter
    public String getUid() {return uid;}
    public String getName() {return name;}
    public String getMarket() {return market;}
    public String getPrice() {return price;}
    public String getDescription() {return description;}

    // Setter
    public void setName(String name) {this.name = name;}
    public void setMarket(String market) {this.name = market;}
    public void setPrice(String price) {this.price = price;}
    public void setDescription(String description) {this.description = description;}

    // Custom Method
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("market", market);
        result.put("price", price);
        result.put("description", description);

        return result;
    }
}
