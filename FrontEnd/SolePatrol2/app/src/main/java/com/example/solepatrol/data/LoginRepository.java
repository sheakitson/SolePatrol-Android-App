package com.example.solepatrol.data;

import com.android.volley.RequestQueue;
import com.example.solepatrol.data.model.LoggedInUser;
import com.example.solepatrol.data.model.SignedupUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

/**The LoginRepository class was created using the Login
 * that can be automatically generated
 * using Android Studio -> Create Activity -> Create Login
 * The SignedupRepository was adapted to fit the needs of the SolePatrol App
 **/
public class LoginRepository {

    private static volatile LoginRepository instance;

    private LoginDataSource dataSource;

    private LoggedInUser user = null;

    // private constructor : singleton access
    private LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    //singleton used to get a single instance of the class
    public static LoginRepository getInstance(LoginDataSource dataSource) {
        if (instance == null) {
            instance = new LoginRepository(dataSource);
        }
        return instance;
    }

    //returns the logged in user
    private void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public void login(String username, String password, RequestQueue requestQueue)
    {
        Result<LoggedInUser> result = dataSource.login(username, password,requestQueue);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<LoggedInUser>) result).getData());
        }

    }

}