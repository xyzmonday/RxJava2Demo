package richfit.com.rxjava2demo.activity;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import richfit.com.rxjava2demo.rxbus.Transformer;

/**
 * Created by monday on 2016/10/25.
 */

public class Practice3Activity extends BasePracticeActivity {

    private List<Integer> mReceivedList;

    @Override
    protected void initData() {
        mTvSend.setText(mTvSend.getText() + "\n" + "发送1-999 一共1000个信号"
        + "采用的被压策略是 BackpressureStrategy.LATEST");

        mReceivedList = new ArrayList<>();

        practice3();
    }

    @Override
    protected void initEvent() {

    }

    /**
     * 测试Flowable的被压。注意ActionX，和FunctionX都支持抛出异常
     */
    private void practice3() {
        Disposable disposable = getFlowableWithBackpressure()
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.newThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        Log.d("yff", integer.toString());
                        mReceivedList.add(integer);
                        Thread.sleep(10);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("yff", throwable.toString());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.d("yff", "complete");
                        Flowable.just(1)
                                .compose(Transformer.io2main())
                                .subscribe(a->mTvReceiver.setText(
                                        mTvReceiver.getText() + "\n"
                                        + mReceivedList
                                ));

                    }
                });
    }

    private Flowable<Integer> getFlowableWithBackpressure() {

        return Flowable.create(new FlowableOnSubscribe<Integer>() {
            @Override
            public void subscribe(FlowableEmitter<Integer> emitter) throws Exception {
                for (int i = 0; i < 1000; i++) {
                    emitter.onNext(i);
                }
                emitter.onComplete();
            }
            /*如果是LAST那么将999发出，然后不再发送。不同的被压策略最终在onNext的结果都不同，只可以自己测试
            * 这里采用BUFFER目的是想测试一下Dispose是否可以成功的取消*/
        }, BackpressureStrategy.LATEST);
    }
}
