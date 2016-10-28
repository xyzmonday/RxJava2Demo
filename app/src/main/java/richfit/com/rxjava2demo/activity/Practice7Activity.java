package richfit.com.rxjava2demo.activity;

import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeOnSubscribe;
import io.reactivex.disposables.Disposable;

/**
 * Created by monday on 2016/10/25.
 */

public class Practice7Activity extends BasePracticeActivity {


    @Override
    protected void initData() {
        mTvSend.setText(mTvSend.getText() + "\n" + "Maybe作为数据源，发送0,1两个信号");
        practice7();
    }

    @Override
    protected void initEvent() {

    }
    /**
     * 练习Maybe。Maybe同时包括Single和Completable，这个数据源可能发射
     * 0或者1，以及一个错误的信号。由于它只能发射一个元素所以没有被压。另外
     * 如果有一个信号，那么onSuccess()被回调，而onComplete不会回调
     */
    private void practice7() {
        Maybe<Integer> source = Maybe.create(new MaybeOnSubscribe<Integer>() {
            @Override
            public void subscribe(MaybeEmitter<Integer> emitter) throws Exception {
                emitter.onSuccess(0);
                emitter.onSuccess(1);
                emitter.onComplete();
            }
        });
        source.subscribe(new MaybeObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer value) {
                mTvReceiver.setText(mTvReceiver.getText() + "\n"
                        +"Maybe onSuccess; value = " + value);

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                mTvReceiver.setText(mTvReceiver.getText() + "\n"
                        +"Maybe onComplete");
            }
        });
    }

}
