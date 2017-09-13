/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView ChangeSignUpModeTextVieW;
    Boolean signUpModeActive= true;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.ChangeSignupModetextView) {
            Log.d("App info", "Change signup mode");
            Button signupButton =(Button)findViewById(R.id.signUpButton2);
            if (signUpModeActive) {

               signUpModeActive=false;
                signupButton.setText("Login");
                ChangeSignUpModeTextVieW .setText("Or, Signup");
            }else{
                signUpModeActive=true;
                signupButton.setText("Sign up");
                ChangeSignUpModeTextVieW .setText("Or, Login");
            }
        }
    }

    public  void signUp(View view){
        EditText usernameEditText=(EditText)findViewById(R.id.UsernameEditText);
        EditText passwordEditText=(EditText)findViewById(R.id.PasswordEdittext);

        if(usernameEditText.getText().toString().matches("")||passwordEditText.getText().toString().matches("")){
            Toast.makeText(MainActivity.this,"Enter username/password",Toast.LENGTH_LONG).show();
        }else{
            if(signUpModeActive) {
                ParseUser user = new ParseUser();
                user.setUsername(usernameEditText.getText().toString());
                user.setPassword(passwordEditText.getText().toString());
                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Log.i("Sign up", "Success");
                        } else {
                            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }else {
                ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if(user!=null){
                            Log.i("Login:", "Successful");
                        }else{
                            Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChangeSignUpModeTextVieW =(TextView) findViewById(R.id.ChangeSignupModetextView);
        ChangeSignUpModeTextVieW.setOnClickListener(this);


    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}

