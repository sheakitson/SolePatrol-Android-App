package com.example.solepatrol.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.util.Patterns;
import com.android.volley.RequestQueue;
import com.example.solepatrol.data.Result;
import com.example.solepatrol.data.SignedupRepository;
import com.example.solepatrol.R;
import com.example.solepatrol.data.model.SignedupUser;

/**
 * The Signup View model was based off the LoginView Model class that was generated automatically
 *  using Android Studio and later altered to meet the needs of our Sole Patrol app.
 **/
public class SignupViewModel extends ViewModel {

    private MutableLiveData<SignupState> signupState = new MutableLiveData<>();
    private MutableLiveData<SignupResult> signupResult = new MutableLiveData<SignupResult>();
    private SignedupRepository signedupRepository;

    SignupViewModel(SignedupRepository signedupRepository) {
        this.signedupRepository = signedupRepository;
    }

    LiveData<SignupState> getSignupState() {
        return signupState;
    }

    LiveData<SignupResult> getSignupResult() {
        return signupResult;
    }

    public void signup(String username, String password, String email, RequestQueue requestQueue) {
        // can be launched in a separate asynchronous job
        Result<SignedupUser> result = signedupRepository.signup(username, password, requestQueue);
        if (result instanceof Result.Success) {
            SignedupUser data = ((Result.Success<SignedupUser>) result).getData();
            signupResult.setValue(new SignupResult(new SignedupUserView(data.getDisplayName())));
        } else {
            signupResult.setValue(new SignupResult(""));
        }

    }

    public void signupDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            signupState.setValue(new SignupState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            signupState.setValue(new SignupState(null, R.string.invalid_password));
        } else {
            signupState.setValue(new SignupState(true));
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