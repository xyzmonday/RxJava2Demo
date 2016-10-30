package richfit.com.rxjava2demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by monday on 2016/10/29.
 */

public class RotationPersistFragment extends Fragment {

    private static final String REQUEST_PENDING = "requestPending";

    private IProcessStreamListener mProcessorStreamListener;

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (IProcessStreamListener.class.isInstance(context)) {
            mProcessorStreamListener = (IProcessStreamListener) context;
        }
        if (mProcessorStreamListener == null) {
            throw new ClassCastException("不能发现子类继承了");
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && savedInstanceState.getBoolean(REQUEST_PENDING,false)) {
            //如果有未完成的请求


        }
    }


    public interface IProcessStreamListener {
        void _onNext(int value);

        void _onComplete();

        void _onError(String message);
    }
}
