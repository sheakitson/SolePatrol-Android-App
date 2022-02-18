package com.example.solepatrol.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Log;
import android.util.Patterns;

import com.android.volley.RequestQueue;
import com.example.solepatrol.data.LoginRepository;
import com.example.solepatrol.data.Result;
import com.example.solepatrol.data.model.LoggedInUser;
import com.example.solepatrol.R;
import com.example.solepatrol.data.model.SignedupUser;
/**The LoginViewModel class was originally created using the Login Activity that can be automatically generated
 * using Android Studio.
 **/
public class LoginViewModel extends ViewModel {

    private MutableLiveData<LoginState> loginState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginState> getLoginState() {
        return loginState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public boolean login(String username, String password, RequestQueue requestQueue) {
        // can be launched in a separate asynchronous job
        boolean loggedIn = false;
        return loggedIn;
    }

    public void loginDataChanged(String username, String password)
    {
        if (!isUserNameValid(username)) {
            loginState.setValue(new LoginState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginState.setValue(new LoginState(null, R.string.invalid_password));
        } else {
            loginState.setValue(new LoginState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}