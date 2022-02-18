package com.example.solepatrol.ui.login;
/**
 * The SignedupUserView class was based off the LoggedinUserView which created using the Login Activity that can be generated
 *  using Android Studio.
/*
 * Class exposing authenticated user details to the UI.
 The LoginState class was originally created using the Login Activity that can be generated
 *  * using Android Studio.
 *  * */

class SignedupUserView {
    private String displayName;
    //... other data fields that may be accessible to the UI

    SignedupUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}