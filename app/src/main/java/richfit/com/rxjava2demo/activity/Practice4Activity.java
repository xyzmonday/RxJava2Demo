package richfit.com.rxjava2demo.activity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import richfit.com.rxjava2demo.rxbus.TransformerHelper;

/**
 * Created by monday on 2016/10/25.
 */

public class Practice4Activity extends BasePracticeActivity {


    @Override
    protected void initData() {

        mTvSend.setText(mTvSend.getText() + "\n" + "发送 1-99 一共100个信号，每个信号间隔100ms" +
                ",500ms后取消订阅");

        practice4();
    }

    @Override
    protected void initEvent() {
        Flowable.just(1)
                .delay(500, TimeUnit.MILLISECONDS)
                .compose(TransformerHelper.io2main())
                .subscribe(a -> dispose());
    }

    /**
     * 练习Fowable.subscribeWith方法
     */
    private void practice4() {
       /*订阅Flowable，可以用subscribe()方法，但是该方法如果传入Subscriber对象不再返回
       * Subscription，不也返回Disposable对象，不好控制其订阅关系。为此这里使用
       * SubscribeWith方法，该方法可以返回Subscriber或者Disposable对象。因为一般而言，如果我们
       * 需要控制订阅的生命周期，需要返回Disposable对象*/

        /*注意这里需要在子线程中*/
        ResourceSubscriber<Long> subscriber = Flowable
                .intervalRange(1, 100, 0, 100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<Long>() {
                    @Override
                    public void onNext(Long value) {
                        mTvReceiver.setText(mTvReceiver.getText() + "\n" + "onNext; value = " + value);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {
                        mTvReceiver.setText(mTvReceiver.getText() + "\n" + "onComplete");
                    }
                });

        mCompositeDisposable.add(subscriber);
    }

}
