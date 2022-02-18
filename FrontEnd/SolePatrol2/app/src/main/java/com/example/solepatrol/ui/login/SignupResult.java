package com.example.solepatrol.ui.login;

import androidx.annotation.Nullable;
/**
 * The SignupResult class was based off the LoginResult class that was generated automatically
 *  using Android Studio and later altered to meet the needs of our Sole Patrol app.
 **/

/**
 * Authentication result : success (user details) or error message.
 */
class SignupResult {
    @Nullable
    private SignedupUserView success;
    @Nullable
    private Integer error;

    SignupResult(@Nullable Integer error) {
        this.error = error;
    }

    SignupResult(@Nullable SignedupUserView success) {
        this.success = success;
    }

    public SignupResult(String s) {
    }

    @Nullable
    SignedupUserView getSuccess() {
        return success;
    }

    @Nullable
    Integer getError() {
        return error;
    }
}