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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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


public class MainActivity extends AppCompatActivity {


    public  void signUp(View view){
        EditText usernameEditText=(EditText)findViewById(R.id.UsernameEditText);
        EditText passwordEditText=(EditText)findViewById(R.id.PasswordEdittext);

        if(usernameEditText.getText().toString().matches("")||passwordEditText.getText().toString().matches("")){
            Toast.makeText(MainActivity.this,"Enter username/password",Toast.LENGTH_LONG).show();
        }else{
            ParseUser user=new ParseUser();
            user.setUsername(usernameEditText.getText().toString());
            user.setPassword(passwordEditText.getText().toString());
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                   if(e==null){
                       Log.i("Sign up", "Success");
                   }else{
                       Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                   }
                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}

