package richfit.com.rxjava2demo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import richfit.com.rxjava2demo.R;
import richfit.com.rxjava2demo.rxbus.RxManager;

/**
 * Created by monday on 2016/10/25.
 */

public class TopFragment2 extends Fragment {

    @BindView(R.id.btn_send)
    Button mBtnSend;

    Unbinder mUnbinder;

    RxManager mRxManager;

    int clickNum = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRxManager = RxManager.getInstance();
    }

    @OnClick(R.id.btn_send)
    public void onClick(View view) {
        mRxManager.post("click event",new EmitterEvent("I am from top fragment2#" + (clickNum++)));
    }


    public static class EmitterEvent {
        public String clickEvent;

        public EmitterEvent(String clickEvent) {
            this.clickEvent = clickEvent;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
