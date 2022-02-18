package com.example.solepatrol.data;
/**The SignupDataSource class was based off the Login Activity that can be automatically generated
 * using Android Studio -> Create Activity -> Create Login
 * It was adapted to fit the needs of the Sole Patrol App*/
import com.android.volley.RequestQueue;
import com.example.solepatrol.data.model.SignedupUser;

import org.json.JSONArray;

import java.io.IOException;

public class SignupDataSource {

    public Result<SignedupUser> signup(String username, String password, RequestQueue requestQueue) {

        final SignedupUser[] user = new SignedupUser[1];
        final boolean[] success = {false};
        if (requestQueue != null) {

        }
        if (success[0] == true) {
            return new Result.Success<>(user[0]);
        } else {
            return new Result.Error(new IOException("Error logging in"));
        }

    }
}
