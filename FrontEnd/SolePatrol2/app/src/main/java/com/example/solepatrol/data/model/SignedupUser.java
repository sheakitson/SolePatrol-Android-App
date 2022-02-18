package com.example.solepatrol.data.model;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class SignedupUser {

    private String username;
    private String displayName;

    public SignedupUser(String username, String displayName) {
        this.username = username;
        this.displayName = displayName;
    }

    public String getUserId() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }
}