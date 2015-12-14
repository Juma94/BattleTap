package DataAccess;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.julab.battletap.BatteltapApplication;

/**
 * Created by user on 13-12-15.
 */

public class Singleton {
    private static Singleton mInstance = null;
    private RequestQueue mRequestQueue;

    private Singleton() {
        mRequestQueue = Volley.newRequestQueue(BatteltapApplication.getAppContext());
    }

    public static Singleton getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton();
        }

        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }
}