package com.android.covid19;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;

public class App extends Application {
    final String TAG = this.getClass().getName();
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );
        Log.d(TAG, "Parse executed");
    }
}
