package com.example.solepatrol.ui.login;

/**The LoginUserView class was originally created using the Login Activity that can be automatically generated
 * using Android Studio -> Create Activity -> Create Login
 * */
/**
 * Class exposing authenticated user details to the UI.
 */
class LoggedInUserView {
    private String displayName;

    LoggedInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}