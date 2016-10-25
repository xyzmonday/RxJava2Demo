package richfit.com.rxjava2demo.activity;

import android.util.Log;

import java.util.Arrays;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by monday on 2016/10/25.
 */

public class Practice1Activity extends BasePracticeActivity {

    String[] mSendSignals;

    @Override
    protected void initData() {
        mSendSignals = getDefaultStringArray();

        mTvSend.setText(mTvSend.getText() + "\n" + Arrays.asList(mSendSignals));

        practice1();
    }

    @Override
    protected void initEvent() {

    }

    /**
     * 练习Observer-Observable.
     * 注意这里如果getObservable是采用Obsrvable.create方法创建的，
     * 那么onSubscribe方法中的Dispose对象是null。
     */
    private void practice1() {

        Observable<String> observable = getObservable();

        /*看到Observer接口多出了一个onSubscribe方法，注释说它可以用来取消
        * Observable和Observer之间的联系。也就是说Disposable相当于Rx1.x中Subscription的作用*/

        Observer<String> observer = new Observer<String>() {
            private Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                //该方法是第一个调用
                disposable = d;
                Log.d(TAG, "onSubscribe" + d);

                mTvReceiver.setText(mTvReceiver.getText() + "\n" + "onSubscribe方法执行");
            }

            @Override
            public void onNext(String value) {
                if ("four".equals(value)) {
                    if (disposable != null && !disposable.isDisposed()) {
                        disposable.dispose();
                    }
                }
                Log.d(TAG, "onNext = " + value);
                mTvReceiver.setText(mTvReceiver.getText() + "\n" + "onNext; " + "value = " + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "error = " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete");
                mTvReceiver.setText(mTvReceiver.getText() + "\n" + "onComplete");
            }
        };

        /*建立订阅关系，注意这里subscribe方法返回时void*/
        observable.subscribe(observer);

    }


    /**
     * 注意这里如果使用Observable.create方法传递的参数是ObservableOnSubscribe对象。
     * 该接口的subscribe方法的参数是ObservableEmitter。也就是说
     * 它是用来发送被观察者的事件
     */
    private Observable<String> getObservable() {
        return Observable.fromArray(mSendSignals);
    }
}
