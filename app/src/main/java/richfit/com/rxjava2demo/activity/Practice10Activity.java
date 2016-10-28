package richfit.com.rxjava2demo.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import richfit.com.rxjava2demo.R;
import richfit.com.rxjava2demo.base.BaseActivity;
import richfit.com.rxjava2demo.fragment.BottomFragment2;
import richfit.com.rxjava2demo.fragment.TopFragment2;

/**
 * Created by monday on 2016/10/25.
 */

public class Practice10Activity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_practice9;
    }

    @Override
    protected void initData() {
        practice10();
    }

    @Override
    protected void initEvent() {

    }

    private void practice10() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.top_container,new TopFragment2(),"top_fragment2");
        transaction.add(R.id.bottom_container,new BottomFragment2(),"bottom_fragment2");
        transaction.commit();
    }

}
