package richfit.com.rxjava2demo.activity;

import io.reactivex.processors.PublishProcessor;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Created by monday on 2016/10/25.
 */

public class Practice8Activity extends BasePracticeActivity {


    @Override
    protected void initData() {
        mTvSend.setText(mTvSend.getText() + "\n" + "PublishProcessor同时作为数据源和订阅者,具体注意事项情况请看注释");
        practice8();
    }

    @Override
    protected void initEvent() {

    }

    /**
     * 练习使用Subject。关于Subject请看SimpleRxBus的注释这里不再多说了。
     * 需要注意的是subject.subscribe不能简单传入Subsriber对象，原因是
     * 没有设置request。关于这一点在practice2已经说明了，这里我们采用ResourceSubscriber.
     * 另外官网说BehaviorProcessor and PublishProcessor不需要publish了。
     */
    private void practice8() {

        final PublishProcessor<Object> subject = PublishProcessor.create();

        //如果加上线程切换，那么Subscriber不会收到数据。只会执行onComplete。具体请看SimpleRxBus的注释。
        //简单说就是PublishProcessor是一个热源，只要有onNext就发射不管你有没有订阅。但是由于onNext在主线程
        //当主线程发送数据的时候，子线程还没有起来，所以数据丢失了。而且Hot Observable 不能转换成 Cold Observable
        //所以用Subject的时候要小心。

        subject.subscribe(new WrapperResourceSubscriber("test1"));

        subject.subscribe(new WrapperResourceSubscriber("test2"));

//        SystemClock.sleep(100);加上延迟等待子线程初始化完毕吗，这样就Subscriber就可以收到信号了。
//        或者使用ReplayProcessor页可以

        subject.onNext(1);
        subject.onNext(2);
        subject.onNext(3);
        subject.onComplete();

    }

    private class WrapperResourceSubscriber extends ResourceSubscriber {
        String name;

        public WrapperResourceSubscriber(String name) {
            this.name = name;
        }

        @Override
        public void onNext(Object s) {
            mTvReceiver.setText(mTvReceiver.getText() + "\n"
                    + name + "Subject onNext ; value = " + s);
        }

        @Override
        public void onError(Throwable t) {
            mTvReceiver.setText(mTvReceiver.getText() + "\n"
                    + name + "Subject onError");
        }

        @Override
        public void onComplete() {
            mTvReceiver.setText(mTvReceiver.getText() + "\n"
                    + name + "Subject onComplete");
        }
    }


}
