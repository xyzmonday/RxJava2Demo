package richfit.com.rxjava2demo.rxbus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * 管理RxBus防止内存泄露
 * Created by monday on 2016/9/30.
 */

public class RxManager {

    public static final int BACKPRESSURE_STRATEGY_BUFFER = 0;
    public static final int BACKPRESSURE_STRATEGY_DROP = 1;
    public static final int BACKPRESSURE_STRATEGY_LAST = 2;


    private static RxManager instance;

    private RxManager() {
    }

    public static RxManager getInstance() {
        if (instance == null) {
            instance = new RxManager();
        }
        return instance;
    }


    public RxBus mRxBus = RxBus.getInstance();

    //管理rxbus订阅
    private Map<Object, Flowable<?>> mFlowables = new HashMap<>();

    /**
     * RxBus注入监听。当RxBus发送事件时，回到action1方法。
     *
     * @param eventName
     * @param action1
     */
    public <T> void register(String eventName, Consumer<T> action1) {
        Flowable<T> flowable = mRxBus.register(eventName);
        mFlowables.put(eventName, flowable);
        /*订阅管理*/
        flowable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1, throwable -> {
                });
    }

    /**
     * 注册RxBus.
     * 这里直接将observable订阅到action1上。并且将subscription
     * 添加到mCompositeSubscription中，方便统一的取消订阅。
     *
     * @param tag
     * @param action1:onNext回调事件
     */
    public <T> void register(Object tag, Consumer<T> action1) {
        Flowable<T> flowable = mRxBus.register(tag);
        mFlowables.put(tag, flowable);

        flowable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1, throwable -> {
                });
    }

    public <T> void register(Object tag, Consumer<T> action1, int backPressureStrategy) {
        Flowable<T> flowable = mRxBus.register(tag);
        mFlowables.put(tag, flowable);
        Flowable<T> tmp  = null;
        switch (backPressureStrategy) {
            case BACKPRESSURE_STRATEGY_BUFFER:
                tmp = flowable.onBackpressureBuffer();
                break;
            case BACKPRESSURE_STRATEGY_DROP:
                tmp =  flowable.onBackpressureDrop();
                break;
            case BACKPRESSURE_STRATEGY_LAST:
                tmp = flowable.onBackpressureLatest();
                break;
            default:
                tmp =  flowable.onBackpressureBuffer();
                break;
        }
        tmp.observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1, throwable -> {
                });
    }

    /**
     * 取消注册在所有RxBus的订阅者，以及Observables 和 Subscribers的订阅者
     */
    public void unRegister() {
        // 移除rxbus观察
        for (Map.Entry<Object, Flowable<?>> entry : mFlowables.entrySet()) {
            mRxBus.unRegister(entry.getKey(), entry.getValue());
        }
    }

    /**
     * 发送事件
     *
     * @param tag
     * @param event
     */
    public void post(Object tag, Object event) {
        mRxBus.post(tag, event);
    }

    public void post(Flowable<Object> flowable, BackpressureStrategy backpressureStrategy) {

    }

    public void post(Object event) {
        post(event.getClass().getName(), event);
    }
}