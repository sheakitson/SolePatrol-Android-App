package com.example.solepatrol.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.solepatrol.data.model.LoggedInUser;

import org.json.JSONArray;

import java.io.IOException;
/**The LoginDataSource class was created using the Login Activity
 * that can be automatically generated
 * using Android Studio -> Create Activity -> Create Login
 **/

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password, RequestQueue requestQueue) {

        final LoggedInUser[] user = new LoggedInUser[1];
        final boolean[] success = {false};

        if (success[0] == true) {
            return new Result.Success<>(user[0]);
        } else {
            return new Result.Error(new IOException("Error logging in"));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}