package richfit.com.rxjava2demo.rxbus;

import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by monday on 2016/10/24.
 */

public class Transformer {

    private final static FlowableTransformer TRANSFORMER = upstream ->
            upstream.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());

    /**
     * io线程-主线程
     */
    public static <T> FlowableTransformer<T, T> io2main() {
        return (FlowableTransformer<T, T>) TRANSFORMER;
    }
}
