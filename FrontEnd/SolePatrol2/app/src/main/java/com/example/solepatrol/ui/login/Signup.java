package com.example.solepatrol.ui.login;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.solepatrol.MainActivity;
import com.example.solepatrol.R;
import com.example.solepatrol.data.model.SignedupUser;
import com.example.solepatrol.databinding.ActivitySignupBinding;

import org.json.JSONArray;

/**
 * Class exposing authenticated user details to the UI.
 The Signup class was based off the LoginActivity class that was generated automatically
 *  * using Android Studio and later altered to meet the needs of our Sole Patrol app.
 *  * */
/**The Android Volley documentation was used to create the networking requests to communicate with Spring
        Reference: https://developer.android.com/training/volley**/

// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
// This Android Model has been modified to fit the needs of the SolePatrol Application

public class Signup extends AppCompatActivity {

    private SignupViewModel signupViewModel;
    private ActivitySignupBinding binding;
    private boolean[] success = {false};
    private Button register;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent();

        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        final EditText usernameEditText = binding.username1;
        final EditText password1EditText = binding.password1;
        final EditText password2EditText = binding.password2;
        final EditText emailEmergencyContactText = binding.emailEmergencyContact;
        final EditText phoneEmergencyContact = binding.phoneEmergencyContact;


        signupViewModel = new ViewModelProvider(this, new SignupViewModelFactory()).get(SignupViewModel.class);
        Button register2 = (Button) findViewById(R.id.register2);
        register2.setEnabled(true);

        signupViewModel.getSignupState().observe(this, new Observer<SignupState>() {
            @Override
            public void onChanged(@Nullable SignupState signupState) {
                if (signupState == null) {
                    return;
                }
                if (signupState.getUsernameError() != null) {
                    usernameEditText.setError(getString(signupState.getUsernameError()));
                }
                if (signupState.getPasswordError() != null) {
                    password1EditText.setError(getString(signupState.getPasswordError()));
                }
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                signupViewModel.signupDataChanged(usernameEditText.getText().toString(),
                        password1EditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        password1EditText.addTextChangedListener(afterTextChangedListener);
        password1EditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    signupViewModel.signup(usernameEditText.getText().toString(), password1EditText.getText().toString(),emailEmergencyContactText.getText().toString(),null);
                }
                return false;
            }
        });



        register = (Button) findViewById(R.id.register2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                String url = "http://10.0.2.2:9192/user/add?name=" + usernameEditText.getText().toString() + "&password1=" + password1EditText.getText().toString()+"&password2=" + password2EditText.getText().toString()+"&emailEmergencyContact="
                        + emailEmergencyContactText.getText().toString() + "&phoneEmergencyContact="+phoneEmergencyContact.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                if(response.equals("true"))
                                {
                                    finish();
                                }
                                else if(response.equals("invalid email"))
                                {
                                    String msg = "Invalid email for emergency contact. Please try again.";
                                    Toast.makeText(Signup.this, msg, Toast.LENGTH_LONG).show();
                                }
                                else if(response.equals("passwords don't match"))
                                {
                                    String msg = "Password doesn't match. Please try again.";
                                    Toast.makeText(Signup.this, msg, Toast.LENGTH_LONG).show();
                                }
                                else if(response.equals("false"))
                                {
                                    String msg = "The username "+ usernameEditText.getText().toString()+" already exists. Please try again";
                                    Toast.makeText(Signup.this, msg, Toast.LENGTH_LONG).show();
                                }
                                Log.e("Response: ",response.toString());
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

}