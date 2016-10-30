package richfit.com.rxjava2demo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.ResourceObserver;
import richfit.com.rxjava2demo.App;
import richfit.com.rxjava2demo.R;
import richfit.com.rxjava2demo.fragment.RotationPersist2WorkerFragment;

/**
 * Created by monday on 2016/10/28.
 */

public class Practice13Activity extends AppCompatActivity implements RotationPersist2WorkerFragment.IStreamProcessorListener {

    private static final String RETAINED_FRAGMENT = "retained_fragment"; // Fragment的标签

    @BindView(R.id.main_s_track_leaks)
    Switch mSTrackLeaks;
    @BindView(R.id.main_b_start_button)
    Button mBtnStart;
    @BindView(R.id.main_tv_progress_text)
    TextView mTvProgressText;
    @BindView(R.id.main_pb_progress_bar)
    ProgressBar mPbProgressBar;
    @BindView(R.id.description)
    TextView description;
    Unbinder mUnbinder;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    public final static int MAX_PROGRESS = 50; // 最大点

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice13);
        mUnbinder = ButterKnife.bind(this);
        mPbProgressBar.setMax(MAX_PROGRESS); // 设置进度条最大值

        // Button点击事件
        mBtnStart.setOnClickListener(this::startProgress);

        setBusy(false);
    }

    // 启动ProgressBar
    private void startProgress(View view) {
        setBusy(true); // 设置繁忙
        handleClick();
    }

    private void handleClick() {
        FragmentManager fm = getSupportFragmentManager();
        RotationPersist2WorkerFragment fragment = (RotationPersist2WorkerFragment)
                fm.findFragmentByTag(RETAINED_FRAGMENT);
        if (fragment == null) {
            fragment = new RotationPersist2WorkerFragment();
        }

        if (!fragment.isAdded())
            fm.beginTransaction().add(R.id.container, fragment, RETAINED_FRAGMENT)
                    .commit();

    }

    @Override
    public void setStream(Observable<Integer> observable) {
        ResourceObserver<Integer> observer = observable.
                observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(createSubscriber());
        mCompositeDisposable.add(observer);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
        if (!mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
        RefWatcher refWatcher = App.getRefWatcher();
        refWatcher.watch(this);
    }

    // 创建订阅者
    private ResourceObserver<Integer> createSubscriber() {
        return new ResourceObserver<Integer>() {
            @Override
            public void onComplete() {
                Log.d("yff", "onComplete");
                setBusy(false);
                mPbProgressBar.setProgress(0);
            }

            @Override
            public void onError(Throwable e) {
                setBusy(false);
                mTvProgressText.setText(String.valueOf("Error!"));
            }

            @Override
            public void onNext(Integer aLong) {
                setProgressPercentText(aLong.intValue());
                mPbProgressBar.setProgress(aLong.intValue());
            }
        };
    }

    // 设置进度条的状态
    public void setBusy(boolean busy) {
        if (mPbProgressBar.getProgress() > 0 && mPbProgressBar.getProgress() != mPbProgressBar.getMax()) {
            setProgressPercentText(mPbProgressBar.getProgress());
        } else {
            mTvProgressText.setText(busy ? "繁忙" : "闲置");
        }
        // 设置按钮显示
        mBtnStart.setText(busy ? "繁忙" : "开始");

        // 忙就不可以点击
        mBtnStart.setEnabled(!busy);

    }

    // 设置进度条显示
    public void setProgressPercentText(int value) {
        mTvProgressText.setText(String.valueOf("进度" + value * 100 / MAX_PROGRESS + "%"));
    }

    // 设置进度条的值
    public void setProgressValue(int value) {
        mPbProgressBar.setProgress(value);
    }


}
