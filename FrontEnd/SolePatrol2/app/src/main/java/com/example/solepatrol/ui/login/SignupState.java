package com.example.solepatrol.ui.login;

import androidx.annotation.Nullable;
/**
 * The SignupState class was based off the LoginResult class that was generated automatically
 *  using Android Studio and later altered to meet the needs of our Sole Patrol app.
 **/

/**
 * Data validation state of the login form.
 */
class SignupState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    private boolean validDate;

    SignupState(@Nullable Integer usernameError, @Nullable Integer passwordError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.validDate = false;
    }

    SignupState(boolean validDate) {
        this.usernameError = null;
        this.passwordError = null;
        this.validDate = validDate;
    }

    @Nullable
    Integer getUsernameError() {
        return usernameError;
    }

    @Nullable
    Integer getPasswordError() {
        return passwordError;
    }

    boolean isValidDate() {
        return validDate;
    }
}