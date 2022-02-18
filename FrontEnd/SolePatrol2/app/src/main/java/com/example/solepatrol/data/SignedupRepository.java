package com.example.solepatrol.data;

import com.android.volley.RequestQueue;
import com.example.solepatrol.data.model.SignedupUser;
/**The SignedupRepository class was based off the loginRepository class created using the Login Activity
 * that can be automatically generated
 * using Android Studio -> Create Activity -> Create Login
 * The SignedupRepository was adapted to fit the needs of the SolePatrol App
 **/
public class SignedupRepository {
    private static volatile SignedupRepository instance;

    private SignupDataSource datasource;

    private SignedupUser user = null;


    private SignedupRepository(SignupDataSource dataSource) {
        this.datasource = datasource;
    }

    public static SignedupRepository getInstance(SignupDataSource dataSource) {

        if (instance == null) {
            instance = new SignedupRepository(dataSource);
        }
        return instance;
    }


    //frontend signup functionality
    public Result<SignedupUser> signup(String username, String password, RequestQueue requestQueue) {
        // handle login

            Result<SignedupUser> result = datasource.signup(username, password, requestQueue);
            if (result instanceof Result.Success) {
                setSignedupUser(((Result.Success<SignedupUser>) result).getData());
            }
        return result;
    }

    //sets the signed up user
    private void setSignedupUser(SignedupUser user) {
        this.user = user;
    }

}
