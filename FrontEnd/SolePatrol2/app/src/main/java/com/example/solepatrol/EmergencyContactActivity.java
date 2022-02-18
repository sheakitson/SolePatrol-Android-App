


/*** The Android documentation was used to create the intent used to make the phone call.
Reference: https://developer.android.com/guide/components/intents-common
The Android Volley documentation was used to create the networking requests to communicate with Spring
Reference: https://developer.android.com/training/volley**/

package com.example.solepatrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.solepatrol.ui.login.LoginActivity;

public class EmergencyContactActivity extends AppCompatActivity {

    private Button prevBtn;
    private ImageButton emailBtn;
    private ImageButton phoneBtn;
    private Button logoutButton;
    private String phoneNum;


    /*Once the screen loads, the user's emergency contact number is accessed from the backend in case it is needed.
    This is done using Volley */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_contact);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "http://10.0.2.2:9192/user/phoneNumber";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("Response: ", response.toString());
                        phoneNum = response.toString();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error.Response", error.toString());
            }
        });
        requestQueue.add(stringRequest);

        //When the previous button is clicked, the user is brought to the previous page
        prevBtn = (Button) findViewById(R.id.prevBtn);
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmergencyContactActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //When the previous button is clicked, the intent 'action call' activity is started.
        phoneBtn = (ImageButton) findViewById(R.id.phoneBtn);
        phoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int permissionCheck = ContextCompat.checkSelfPermission(EmergencyContactActivity.this, Manifest.permission.CALL_PHONE);

                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            EmergencyContactActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            123);
                } else {
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + phoneNum)));
                }
            }
        });

        // Once the email btuton is clicked, an email is sent to the user's emergency contact using Volley
        emailBtn = (ImageButton) findViewById(R.id.emailBtn);
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://10.0.2.2:9192/user/sendEmail";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("Response: ", response.toString());
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error.Response", error.toString());

                    }
                });
                requestQueue.add(stringRequest);

            }
        });


        //the user can logout
        logoutButton = (Button) findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://10.0.2.2:9192/user/logout";
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.e("Response: ", response.toString());
                                openLogin();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error.Response", error.toString());

                    }
                });
                requestQueue.add(stringRequest);
            }
        });


    }




    public void openLogin()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}