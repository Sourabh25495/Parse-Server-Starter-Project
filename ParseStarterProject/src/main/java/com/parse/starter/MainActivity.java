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
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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


public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    TextView ChangeSignUpModeTextVieW;
    Boolean signUpModeActive= true;
    EditText passwordEditText;

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if(i==keyEvent.KEYCODE_ENTER&& keyEvent.getAction()==keyEvent.ACTION_DOWN){
            signUp(view);
        }
        return false;
    }

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
        }else if(view.getId()==R.id.backRelativeLayout||view.getId()==R.id.LogoimageView){


            InputMethodManager inputMethodManager=(InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);


        }
    }

    public  void signUp(View view){
        EditText usernameEditText=(EditText)findViewById(R.id.UsernameEditText);


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

        RelativeLayout backgroundRelativeLayout=(RelativeLayout)findViewById(R.id.backRelativeLayout);
        ImageView logoImageView=(ImageView)findViewById(R.id.LogoimageView);
        backgroundRelativeLayout.setOnClickListener(this);
        logoImageView.setOnClickListener(this);



        passwordEditText=(EditText)findViewById(R.id.PasswordEdittext);
        passwordEditText.setOnKeyListener(this);



    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }


}

