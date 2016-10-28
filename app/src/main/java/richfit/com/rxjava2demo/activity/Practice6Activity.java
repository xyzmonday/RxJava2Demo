package richfit.com.rxjava2demo.activity;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by monday on 2016/10/25.
 */

public class Practice6Activity extends BasePracticeActivity {


    @Override
    protected void initData() {
        mTvSend.setText(mTvSend.getText() + "\n" + "注意Single和Completable的回调");
        practice6();
    }

    @Override
    protected void initEvent() {

    }
    /**
     * 练习SingleObservable和Completable。在2.x中的数据源包括了
     * Observable,Flowable,SingleObservable,Completable。SingleObservable，MaybeSource等。
     * Single的意思就是说订阅后只能够接收到一次(或者说只能够发射一个数据流);
     * Completable与Single类似，只能接受到完成(onComplete)和错误(onError)
     */
    private void practice6() {
        Single.just(1).subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onSuccess(Integer value) {
                mTvReceiver.setText(mTvReceiver.getText() + "\n"
                +"Single onSuccess; value = " + value);
            }

            @Override
            public void onError(Throwable e) {
            }
        });

        Completable.create(emitter -> emitter.onComplete())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        mTvReceiver.setText(mTvReceiver.getText() + "\n"
                                +"Completable onComplete");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }


}
