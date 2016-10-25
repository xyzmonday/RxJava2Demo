package richfit.com.rxjava2demo.activity;

import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Arrays;

import io.reactivex.Flowable;

/**
 * Created by monday on 2016/10/25.
 */

public class Practice2Activity extends BasePracticeActivity {

    String[] mSendSignals;

    @Override
    protected void initData() {

        mSendSignals = getDefaultStringArray();

        mTvSend.setText(mTvSend.getText() + "\n" + Arrays.asList(mSendSignals));

        practice2();
    }

    @Override
    protected void initEvent() {

    }

    /**
     * 练习Flowable-Subscriber.
     * Flowable是RxJava2.x中新增的类，专门用于应对背压（Backpressure）问题，但这并不是RxJava2.x中新引入的概念。
     * 所谓背压，即生产者的速度大于消费者的速度带来的问题，比如在Android中常见的点击事件，点击过快则会造成点击两次的效果。
     * 先看一个简单的例子
     */
    private void practice2() {
        getRangeFlowable().subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                //这里相比于Rx1.x多出了一个onSubscribe方法.cancel直接可以取消本次订阅，request方法设置
                //该流可以请求的次数。该流发送了1-10 9个数，如果设置request(2)那么onNext只能接受到两个数字
                //当然如果你什么也不做，那么恭喜你，不会打印任何东西。
                s.request(3);
            }

            @Override
            public void onNext(String value) {
                mTvReceiver.setText(mTvReceiver.getText() + "\n" + "onNext; " + "value = " + value);
            }

            @Override
            public void onError(Throwable t) {
                Log.d("yff", "onError = " + t.getMessage());
            }

            @Override
            public void onComplete() {
                mTvReceiver.setText(mTvReceiver.getText() + "\n" + "onComplete");
            }
        });

    }

    private Flowable<String> getRangeFlowable() {
        return Flowable.fromArray(mSendSignals);
    }
}
