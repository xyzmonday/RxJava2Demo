package richfit.com.rxjava2demo;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import richfit.com.rxjava2demo.model.RotationPersistModel;
import richfit.com.rxjava2demo.retrofit2.api.IRequestApi;
import richfit.com.rxjava2demo.retrofit2.http.RetrofitModule;

/**
 * Created by monday on 2016/10/26.
 */

public class App extends Application{

    private static Context mContext;

    private static IRequestApi mRequestApi;

    private static RefWatcher refWatcher;

    private static RotationPersistModel mRotationPersistModel;


    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        mRequestApi = RetrofitModule.getRequestApi();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        refWatcher = LeakCanary.install(this);
        mRotationPersistModel = new RotationPersistModel();
    }

    public static Context getAppContext() {
        return mContext;
    }

    public static IRequestApi getRetrofitApi() {
        return mRequestApi;
    }

    public static RefWatcher getRefWatcher() {
        return refWatcher;
    }

    public static RotationPersistModel getRotationPersistModel() {
        return mRotationPersistModel;
    }

}
