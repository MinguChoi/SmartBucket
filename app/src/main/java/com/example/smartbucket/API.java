package com.example.smartbucket;

import com.example.smartbucket.Model.User;

import java.util.HashMap;
import java.util.Map;

public class API {
    // Create a test user in DB
    // it's just for quick start so need to be implemented to allow anyone to register to the DB
    public void createTestUser(){
        // Initialize a testUser
        User testUser = User.getInstance();
        testUser.setName("Test1");
        testUser.setEmail("mingu0629@gmail.com");
        // Map testUser for writing data in DB
        Map<String, Object> update = new HashMap<>();
        update = testUser.toMap();
        // Write data in DB
        Utils.DB_USER.child("1").updateChildren(update);
    }

    // uploading item

}
