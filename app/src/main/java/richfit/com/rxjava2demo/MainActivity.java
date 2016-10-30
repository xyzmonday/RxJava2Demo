package richfit.com.rxjava2demo;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import richfit.com.rxjava2demo.activity.Practice10Activity;
import richfit.com.rxjava2demo.activity.Practice11Activity;
import richfit.com.rxjava2demo.activity.Practice12Activity;
import richfit.com.rxjava2demo.activity.Practice13Activity;
import richfit.com.rxjava2demo.activity.Practice1Activity;
import richfit.com.rxjava2demo.activity.Practice2Activity;
import richfit.com.rxjava2demo.activity.Practice3Activity;
import richfit.com.rxjava2demo.activity.Practice4Activity;
import richfit.com.rxjava2demo.activity.Practice5Activity;
import richfit.com.rxjava2demo.activity.Practice6Activity;
import richfit.com.rxjava2demo.activity.Practice7Activity;
import richfit.com.rxjava2demo.activity.Practice8Activity;
import richfit.com.rxjava2demo.activity.Practice9Activity;
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
            R.id.btn10,R.id.btn11,R.id.btn12,R.id.btn13})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn1:
                intent = new Intent(this,Practice1Activity.class);
                break;
            case R.id.btn2:
                intent = new Intent(this,Practice2Activity.class);
                break;
            case R.id.btn3:
                intent = new Intent(this,Practice3Activity.class);
                break;
            case R.id.btn4:
                intent = new Intent(this,Practice4Activity.class);
                break;
            case R.id.btn5:
                intent = new Intent(this,Practice5Activity.class);
                break;
            case R.id.btn6:
                intent = new Intent(this,Practice6Activity.class);
                break;
            case R.id.btn7:
                intent = new Intent(this,Practice7Activity.class);
                break;
            case R.id.btn8:
                intent = new Intent(this,Practice8Activity.class);
                break;
            case R.id.btn9:
                intent = new Intent(this,Practice9Activity.class);
                break;
            case R.id.btn10:
                intent = new Intent(this,Practice10Activity.class);
                break;
            case R.id.btn11:
                intent = new Intent(this,Practice11Activity.class);
                break;
            case R.id.btn12:
                intent = new Intent(this,Practice12Activity.class);
                break;
            case R.id.btn13:
                intent = new Intent(this,Practice13Activity.class);
                break;
        }

        startActivity(intent);
    }
}
