package com.example.solepatrol.ui.login;

import androidx.annotation.Nullable;

/**
 * Data validation state of the login form.
 */
/**The LoginState class was originally created using the Login Activity that can be automatically generated
 * using Android Studio.
 * */
class LoginState {
    @Nullable
    private Integer usernameError;
    @Nullable
    private Integer passwordError;
    private boolean validDate;

    LoginState(@Nullable Integer usernameError, @Nullable Integer passwordError) {
        this.usernameError = usernameError;
        this.passwordError = passwordError;
        this.validDate = false;
    }

    LoginState(boolean validDate) {
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