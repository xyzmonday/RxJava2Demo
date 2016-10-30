package richfit.com.rxjava2demo.model;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.processors.AsyncProcessor;
import io.reactivex.schedulers.Schedulers;

/**
 * 提供屏幕旋转测试需要的model层（或者说获取网络数据）
 * Created by monday on 2016/10/30.
 */

public class RotationPersistModel {

    private static final String FILE_NAME = "update.apk";

    private AsyncProcessor<Integer> mSubject;


    public Flowable<Integer> getRequest() {
        return mSubject;
    }

    /**
     * 模拟获取网络数据
     *
     * @return
     */
    public Flowable<Integer> getDataFromServer() {
        if (mSubject == null) {
            mSubject = AsyncProcessor.create();
        }

        Flowable.intervalRange(1, 50, 0, 1, TimeUnit.SECONDS)
                .map(Long::intValue)
                .subscribeOn(Schedulers.io())
                .subscribe(mSubject);
        return mSubject;
    }
}
