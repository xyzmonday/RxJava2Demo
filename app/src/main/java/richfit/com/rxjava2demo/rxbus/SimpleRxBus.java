package richfit.com.rxjava2demo.rxbus;

import io.reactivex.Flowable;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.subjects.PublishSubject;

/**
 * 用RxJava2.0实现一个简单的EventBus.
 * 注意AsyncSubject,BehaviorSubject,PublishSubject,ReplaySubject,
 * 在2.x中属于Observable家族，不支持被压。而AsyncProcessor，BehaviorProcessor,
 * PublishProcessor,ReplayProcess支持被压。
 * 至于上面4中Subject的意义和使用注意事项我推进两篇文章:
 * 1. http://blog.csdn.net/jdsjlzx/article/details/51502781
 *
 * 2. http://blog.csdn.net/PrototypeZ/article/details/51113828
 * Created by monday on 2016/10/24.
 */

public class SimpleRxBus {

    private static SimpleRxBus instance;

    /*注释说是Thread-safe但不是serialized的*/
    private final PublishProcessor<Object> mPublishProcessor;

    // PublishSubject只会把在订阅发生的时间点之后的数据发射给观察者
    public SimpleRxBus() {
        PublishSubject.create();
        mPublishProcessor = PublishProcessor.create();
    }

    // 单例RxBus
    public static SimpleRxBus getInstance() {
        if (instance == null) {
            synchronized (SimpleRxBus.class) {
                if (instance == null) {
                    instance = new SimpleRxBus();
                }
            }
        }
        return instance;
    }

    // 发送一个新的事件
    public void post(Object o) {
        mPublishProcessor.onNext(o);
    }

    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Flowable<T> toFlowable(Class<T> eventType) {
        return mPublishProcessor.ofType(eventType);
    }

    /**
     * 注意由于Subject同时实现了Observable和Observer两个接口，所以可以转换
     * @return
     */
    public Flowable<Object> toFlowable() {
        return mPublishProcessor;
    }

    public boolean hasSubscribers() {
        return mPublishProcessor.hasSubscribers();
    }

}
