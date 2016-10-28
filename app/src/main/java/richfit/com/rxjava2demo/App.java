package richfit.com.rxjava2demo;

import android.app.Application;
import android.content.Context;

import richfit.com.rxjava2demo.retrofit2.api.IRequestApi;
import richfit.com.rxjava2demo.retrofit2.http.RetrofitModule;

/**
 * Created by monday on 2016/10/26.
 */

public class App extends Application{

    private static Context mContext;

    private static IRequestApi mRequestApi;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mRequestApi = RetrofitModule.getRequestApi();
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static IRequestApi getRetrofitApi() {
        return mRequestApi;
    }
}
