package richfit.com.rxjava2demo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by monday on 2016/10/23.
 */

public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG = "yff";

    Unbinder mUnBinder;

    protected CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        if (layoutId > 0)
            setContentView(getLayoutId());
        mCompositeDisposable = new CompositeDisposable();
        mUnBinder = ButterKnife.bind(this);
        initData();
        initEvent();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
        dispose();
    }

    protected void dispose() {
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initData();

    protected abstract void initEvent();
}
