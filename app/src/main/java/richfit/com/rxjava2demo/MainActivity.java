package richfit.com.rxjava2demo;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import richfit.com.rxjava2demo.activity.Practice1Activity;
import richfit.com.rxjava2demo.activity.Practice2Activity;
import richfit.com.rxjava2demo.base.BaseActivity;

public class MainActivity extends BaseActivity {


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @OnClick({R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,
            R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,
            R.id.btn10})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn1:
                intent = new Intent(this,Practice1Activity.class);
                break;
            case R.id.btn2:
                intent = new Intent(this,Practice2Activity.class);
                break;
        }

        startActivity(intent);
    }
}
