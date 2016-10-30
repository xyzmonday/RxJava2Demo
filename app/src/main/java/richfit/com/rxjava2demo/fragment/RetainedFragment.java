package richfit.com.rxjava2demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import io.reactivex.Flowable;
import io.reactivex.processors.AsyncProcessor;

/**
 * Created by monday on 2016/10/28.
 */

public class RetainedFragment extends Fragment{

    private Flowable<Integer> mFlowable; // 被观察者\
    private AsyncProcessor<Integer> mSubject; // 主题

    private boolean mBusy; // 是否繁忙

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public Flowable<Integer> getFlowable() {
        return mFlowable;
    }

    public void setFlowable(Flowable<Integer> flowable) {
        this.mFlowable = flowable;
    }

    public AsyncProcessor<Integer> getSubject() {
        return mSubject;
    }

    public void setSubject(AsyncProcessor<Integer> subject) {
        this.mSubject = subject;
    }

    public boolean isBusy() {
        return mBusy;
    }

    public void setBusy(boolean busy) {
        mBusy = busy;
    }
}
