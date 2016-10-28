package richfit.com.rxjava2demo.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import richfit.com.rxjava2demo.R;
import richfit.com.rxjava2demo.base.BaseActivity;
import richfit.com.rxjava2demo.fragment.BottomFragment;
import richfit.com.rxjava2demo.fragment.TopFragment;

/**
 * Created by monday on 2016/10/25.
 */

public class Practice9Activity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_practice9;
    }

    @Override
    protected void initData() {
        practice9();
    }

    @Override
    protected void initEvent() {

    }

    private void practice9() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.top_container,new TopFragment(),"top_fragment");
        transaction.add(R.id.bottom_container,new BottomFragment(),"bottom_fragment");
        transaction.commit();
    }
}
