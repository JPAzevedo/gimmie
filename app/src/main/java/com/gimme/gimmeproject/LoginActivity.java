package com.gimme.gimmeproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gimme.gimmeproject.webservices.AppLogin;
import com.gimme.gimmeproject.entities.LoginEntity;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by joaop on 14/10/2018.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String EMAIL = "email";
    private CallbackManager callbackManager;
    private SharedPreferences preferences;
    private LoginButton loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        setContentView(R.layout.activity_login);
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);

        callbackManager = CallbackManager.Factory.create();
        loginButton.setReadPermissions(Arrays.asList(EMAIL));
            // If you are using in a fragment, call loginButton.setFragment(this);


        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        preferences = getSharedPreferences("user_login",MODE_PRIVATE);
        if(preferences.getBoolean("login",false)){
            Intent mIntent = new Intent(this,MainActivity.class);
            startActivity(mIntent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        Log.d("Token","Token: "+accessToken.getToken());
        Log.d("Token","UserID: "+accessToken.getUserId());
        Log.d("Token","AppID: "+accessToken.getApplicationId());

        //LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"));
         executeRequest(accessToken);
    }

    private void executeRequest(AccessToken accessToken){
        BackgroundTask task = new BackgroundTask();
        task.execute(new AccessToken[]{accessToken});
    }

    public void manageResult(String data) {
            Log.d("Token","Data: "+data);
            if(data != null){
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("login",true);
                editor.commit();
                Intent mIntent = new Intent(this,MainActivity.class);
                startActivity(mIntent);
            }
    }



    private class BackgroundTask extends AsyncTask<AccessToken,Void,String> {

        @Override
        protected String doInBackground(AccessToken... arg) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                String loginData = mapper.writeValueAsString(new LoginEntity(arg[0].getToken(),arg[0].getUserId(),arg[0].getApplicationId()));
                return AppLogin.getInstance().post(Configuration.APP_LOCAL_URL + "/facebook",loginData);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            manageResult(result);
        }
    }
}
