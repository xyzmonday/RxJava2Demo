package richfit.com.rxjava2demo.rxbus;

import android.content.Context;

import io.reactivex.subscribers.ResourceSubscriber;
import richfit.com.rxjava2demo.App;
import richfit.com.rxjava2demo.R;
import richfit.com.rxjava2demo.retrofit2.exception.ResponseNullException;
import richfit.com.rxjava2demo.retrofit2.exception.ServerException;
import richfit.com.rxjava2demo.widget.LoadingLayoutHelper;
import richfit.com.rxjava2demo.widget.NetWorkUtils;

/**
 * 封装Flowable的订阅
 * Created by monday on 2016/10/26.
 */
public abstract class RxSubscriber<T> extends ResourceSubscriber<T> {

    private Context mContext;

    private String msg;
    private boolean showDialog = true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = false;
    }

    public RxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
    }

    public RxSubscriber(Context context) {
        this(context, App.getAppContext().getString(R.string.loading), true);
    }

    public RxSubscriber(Context context, boolean showDialog) {
        this(context, App.getAppContext().getString(R.string.loading), showDialog);
    }

    /**
     * 注意onStart方法必须调用supper.onStart.
     */
    @Override
    protected void onStart() {
        super.onStart();
        LoadingLayoutHelper.showDialogForLoading(mContext);
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable t) {
        if (showDialog)
            LoadingLayoutHelper.cancelDialogForLoading();
        /**
         *如果网络未连接不会调用flatMap 所以网络未连接不会出现ServerException错误 {@link Transformer#retrofit()}.
         */
        if (!NetWorkUtils.isNetConnected(mContext)) {
            _onNetWorkError();//network unConnected
        } else {
            if (t instanceof ResponseNullException) {
                _onEmpty();
            } else if (t instanceof ServerException) {
                _onServerError(((ServerException) t).getReturnCode(), t.getMessage());
            } else {
                _onNetWorkError();//UnknownHostException 1：服务器地址错误；2：网络未连接
            }
        }
    }

    @Override
    public void onComplete() {
        if (showDialog)
            LoadingLayoutHelper.cancelDialogForLoading();
        _onComplete();
    }

    public abstract void _onNext(T t);//onNext()

    public abstract void _onEmpty();//返回的response为空，或者List数组size为0

    public abstract void _onNetWorkError();//网络未连接

    public abstract void _onServerError(String returnCode, String returnMsg);//接口调用操作出现异常，比如注册失败（已注册,短信验证码出错,and so on）

    public abstract void _onComplete();//onComplete()

}
