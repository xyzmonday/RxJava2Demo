package richfit.com.rxjava2demo.rxbus;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Flowable;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.processors.PublishProcessor;

import static richfit.com.rxjava2demo.rxbus.RxManager.BACKPRESSURE_STRATEGY_BUFFER;
import static richfit.com.rxjava2demo.rxbus.RxManager.BACKPRESSURE_STRATEGY_DROP;
import static richfit.com.rxjava2demo.rxbus.RxManager.BACKPRESSURE_STRATEGY_LAST;


/**
 * 采用RxJava实现EventBus（版本1）。该版本加入了tag，
 * 相当于Broadcast的action。
 */
public class RxBus {


    public static RxBus instance;

    private RxBus() {
    }

    public static RxBus getInstance() {
        if (instance == null) {
            instance = new RxBus();
        }
        return instance;
    }

    /*保存事件,这里采用subject,因为它实现了Observable和Subscribe接口。*/
    private ConcurrentHashMap<Object, ArrayList<PublishProcessor>> subjectsMap = new ConcurrentHashMap<>();


    /**
     * 注册事件
     *
     * @param tag:事件集合的tag
     */
    public <T> Flowable<T> register(@NonNull Object tag) {
        /*获取tag对应注册的事件集合*/
        ArrayList<PublishProcessor> subjects = subjectsMap.get(tag);
        if (subjects == null) {
            subjects = new ArrayList<>();
            subjectsMap.put(tag, subjects);
        }
        /*生成事件*/
        PublishProcessor<T> subject;
        //这里采用PublishProcessor，该主题的特点是当有新的订阅者时立即将事件发射出去。
        subjects.add(subject = PublishProcessor.create());
        return subject;
    }


    public <T> Flowable<T> register(@NonNull Object tag, @NonNull Class<T> clazz) {
        /*获取tag对应注册的事件集合*/
        ArrayList<PublishProcessor> subjects = subjectsMap.get(tag);
        if (subjects == null) {
            subjects = new ArrayList<>();
            subjectsMap.put(tag, subjects);
        }
        /*生成事件*/
        PublishProcessor<T> subject;
        //这里采用PublishProcessor，该主题的特点是当有新的订阅者时立即将事件发射出去。
        subjects.add(subject = PublishProcessor.create());
        return subject.ofType(clazz);
    }

    public <T> ConnectableFlowable<T> register(@NonNull Object tag,int backPressureStrategy) {
        /*获取tag对应注册的事件集合*/
        ArrayList<PublishProcessor> subjects = subjectsMap.get(tag);
        if (subjects == null) {
            subjects = new ArrayList<>();
            subjectsMap.put(tag, subjects);
        }
        /*生成事件*/
        PublishProcessor<T> subject;
        ///这里采用PublishProcessor，该主题的特点是当有新的订阅者时立即将事件发射出去。
        subject = PublishProcessor.create();
        subjects.add(subject);
        switch (backPressureStrategy) {
            case BACKPRESSURE_STRATEGY_BUFFER:
                subject.onBackpressureBuffer();
                break;
            case BACKPRESSURE_STRATEGY_DROP:
                subject.onBackpressureDrop();
                break;
            case BACKPRESSURE_STRATEGY_LAST:
                subject.onBackpressureLatest();
                break;
            default:
                subject.onBackpressureBuffer();
                break;
        }
        return subject.publish();
    }
    /**
     * 反注册tag的事件集合
     *
     * @param tag:事件集合的tag
     */
    public void unRegister(@NonNull Object tag) {
        ArrayList<PublishProcessor> subjects = subjectsMap.get(tag);
        if (subjects != null) {
            /*说明已经注册过了*/
            subjectsMap.remove(tag);
        }
    }

    /**
     * 反注册事件集合的一个具体事件
     *
     * @param tag：事件集合的tag
     * @param flowable：需要反注册的具体事件
     * @return
     */
    public RxBus unRegister(@NonNull Object tag, @NonNull Flowable<?> flowable) {
        if (flowable == null) {
            return getInstance();
        }
        ArrayList<PublishProcessor> subjects = subjectsMap.get(tag);
        if (subjects != null) {
            //先取出tag对应的事件集合，然后remove该事件集合的observable事件
            subjects.remove((PublishProcessor<?>) flowable);
            if (isEmpty(subjects)) {
                //如果该事件集合没有事件，移除tag注册的事件
                subjectsMap.remove(tag);
            }
        }
        return getInstance();
    }

    public void post(@NonNull Object event) {
        post(event.getClass().getName(), event);
    }

    /**
     * 发送事件.注意只要注册的tag的事件，那么都能够接收到event事件
     *
     * @param tag
     * @param event:发送的事件
     */
    public void post(@NonNull Object tag, @NonNull Object event) {
        List<PublishProcessor> subjectList = subjectsMap.get(tag);
        if (!isEmpty(subjectList)) {
            for (PublishProcessor subject : subjectList) {
                if (subject != null)
                    subject.onNext(event);
            }
        }
    }

    /**
     * 判断List集合是否为空
     */
    private boolean isEmpty(Collection<PublishProcessor> collection) {
        return null != collection && collection.isEmpty();
    }
}