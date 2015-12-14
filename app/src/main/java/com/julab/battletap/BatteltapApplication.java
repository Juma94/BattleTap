package com.julab.battletap;

import android.app.Application;
import android.content.Context;

/**
 * Created by user on 13-12-15.
 */
public class BatteltapApplication extends Application {
    private static BatteltapApplication mInstance;

    private boolean isLoggedin;

    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
    }

    public static BatteltapApplication getInstance(){
        return mInstance;
    }

    public static Context getAppContext(){
        return mInstance.getApplicationContext();
    }

    public void setIsLoggedIn(boolean value) {
        this.isLoggedin = value;
    }

    public boolean getIsLoggedIn() {
        return this.isLoggedin;
    }
}
