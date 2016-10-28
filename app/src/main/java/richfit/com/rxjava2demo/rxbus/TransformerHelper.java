package richfit.com.rxjava2demo.rxbus;

import android.util.Log;

import org.reactivestreams.Publisher;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import richfit.com.rxjava2demo.retrofit2.bean.Response;
import richfit.com.rxjava2demo.retrofit2.exception.ResponseNullException;
import richfit.com.rxjava2demo.retrofit2.exception.ServerException;
import richfit.com.rxjava2demo.retrofit2.global.Constant;

/**
 * Created by monday on 2016/10/24.
 */

public class TransformerHelper {


    public static <T> FlowableTransformer<T, T> io2main() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Publisher<T> apply(Flowable<T> flowable) {
                return flowable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> FlowableTransformer<Response<T>, T> handleResponse() {
        return new FlowableTransformer<Response<T>, T>() {
            @Override
            public Publisher<T> apply(Flowable<Response<T>> flowable) {
                return flowable
                        .flatMap(new Function<Response<T>, Publisher<T>>() {
                            @Override
                            public Publisher<T> apply(Response<T> t) throws Exception {
                                if (t == null || (t instanceof List && ((List) t).size() == 0)) {
                                    return Flowable.error(new ResponseNullException("response's model is null or response's list size is 0"));
                                } else {
                                    if (Constant.RETURN_SUCCESS_CODE.equals(t.getReturnCode())) {//如果返回时"0000"表示数据请求正常
                                        return Flowable.just(t.getData());
                                    } else {
                                        //如果网络未连接不会调用flatMap,所以网络未连接不会出现ServerException错误
                                        return Flowable.error(new ServerException(t.getReturnCode(), t.getReturnMsg()));//return the response's returnCode and msg
                                    }
                                }
                            }
                        });
            }
        };
    }

}
