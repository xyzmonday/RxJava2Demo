package richfit.com.rxjava2demo.activity;

import io.reactivex.Flowable;
import richfit.com.rxjava2demo.rxbus.TransformerHelper;

/**
 * Created by monday on 2016/10/25.
 */

public class Practice5Activity extends BasePracticeActivity {


    @Override
    protected void initData() {

        mTvSend.setText(mTvSend.getText() + "\n" + "测试Transformer，注意2.x自提供了FlowableTransformer" +
        "注意这里我们在子线程中刷新了UI，具体原因请大家自己查资料，后面会专门写一篇关于子线程刷新UI的Blog，敬请关注!!!");

        practice5();
    }

    @Override
    protected void initEvent() {

    }
    /**
     * 练习线程切换.一般而言我们会使用subscribeOn，observerOn配合来
     * 实现从子线程到主线程的切换。RxJava2.0也可以这样做。在1.x中我们也经常
     * 封装一个Transformer，然后使用Compose优雅地将io->main。当然
     * Transformer的作用不仅仅于此，广义的说它是将一个流装换成另一个流。
     * 这点有点像flatmap操作符。至于他们之间区别和联系请大家查资料，这里仅仅是
     * 更大家探讨RxJava的一些新的用法。
     * 在RxJava2.x中我们发现没有Observable.Transformer了，取而代之的是FlowableTransformer
     */
    private void practice5() {
        Flowable.just(1)
                .doOnNext(integer -> mTvReceiver.setText(mTvReceiver.getText() + "\n" +
                        "doOnNext运行的线程 = " + Thread.currentThread().getName()))
                .compose(TransformerHelper.io2main())
                .subscribe(integer ->  mTvReceiver.setText(mTvReceiver.getText() + "\n" +
                        "onNext运行的线程 = " + Thread.currentThread().getName()));
    }

}
