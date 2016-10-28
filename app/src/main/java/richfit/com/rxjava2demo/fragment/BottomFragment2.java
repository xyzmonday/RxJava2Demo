package richfit.com.rxjava2demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.functions.Consumer;
import richfit.com.rxjava2demo.R;
import richfit.com.rxjava2demo.rxbus.RxManager;

/**
 * Created by monday on 2016/10/25.
 */

public class BottomFragment2 extends Fragment {

    @BindView(R.id.tv_receiver)
    TextView mTvReceiver;

    Unbinder mUnbinder;

    RxManager mRxManager;

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
        mRxManager = RxManager.getInstance();
        mRxManager.register("click event", (Consumer<TopFragment2.EmitterEvent>) emitterEvent -> {
            mTvReceiver.setText(emitterEvent.clickEvent);
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
        mRxManager.unRegister();
    }
}
