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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*


        ParseUser.logInInBackground("sourabhuk254", "password", new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(user!=null){
                    Log.i("Login ", "Successful");

                }else{
                    Log.i("Login "," Incorrect password");
                }
            }
        });
        */
        ParseUser.logOut();
        if(ParseUser.getCurrentUser()!=null){
            Log.i("Message", "User is already logged in "+ParseUser.getCurrentUser().getUsername());
        }else{
            Log.i("Message", " Not logged in");
        }
/*


        ParseUser user = new ParseUser();
        user.setUsername("sHreyas");
        user.setPassword("password1");
        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e== null){
                    Log.i("signup","Succerr");

                }else{
                    Log.i("Signup", "Failed");
                }
            }
        });
*/


    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}

