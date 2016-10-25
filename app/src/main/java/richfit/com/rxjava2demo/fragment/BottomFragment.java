package richfit.com.rxjava2demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import richfit.com.rxjava2demo.R;
import richfit.com.rxjava2demo.rxbus.SimpleRxBus;

/**
 * Created by monday on 2016/10/25.
 */

public class BottomFragment extends Fragment {

    @BindView(R.id.tv_receiver)
    TextView mTvReceiver;

    Unbinder mUnbinder;

    SimpleRxBus mRxBus;

    CompositeDisposable mCompositeDiposable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCompositeDiposable = new CompositeDisposable();
        mRxBus = SimpleRxBus.getInstance();


//        ConnectableFlowable<TopFragment.EmitterEvent> connectableFlowable = mRxBus.toFlowable(TopFragment.EmitterEvent.class).publish();


        Disposable subscribe =  mRxBus.toFlowable(TopFragment.EmitterEvent.class)
                .filter(event -> !TextUtils.isEmpty(event.clickEvent))
                .subscribe(event -> mTvReceiver.setText(event.clickEvent));

        mCompositeDiposable.add(subscribe);
//        mCompositeDiposable.add(connectableFlowable.connect());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        if(!mCompositeDiposable.isDisposed()) {
            mCompositeDiposable.dispose();
        }
    }
}
