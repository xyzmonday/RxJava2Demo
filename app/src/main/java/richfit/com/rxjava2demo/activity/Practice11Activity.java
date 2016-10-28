package richfit.com.rxjava2demo.activity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import richfit.com.rxjava2demo.App;
import richfit.com.rxjava2demo.model.Weather;
import richfit.com.rxjava2demo.rxbus.RxSubscriber;
import richfit.com.rxjava2demo.rxbus.TransformerHelper;

/**
 * Created by monday on 2016/10/25.
 */

public class Practice11Activity extends BasePracticeActivity {

    private static final String FILE_NAME = "weather.json";

    @Override
    protected void initData() {
        practiceRetrofitApi();
    }

    @Override
    protected void initEvent() {

    }

    private ResourceSubscriber<Weather> dispose;

    private void practiceRetrofitApi() {

       App.getRetrofitApi().getWeatherData(FILE_NAME)
               .compose(TransformerHelper.handleResponse())
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(new RxSubscriber<Weather>(this) {
                   @Override
                   public void _onNext(Weather weather) {
                       mTvReceiver.setText(mTvReceiver.getText() + "\n" + "服务返回的数据 = " + weather.toString());
                   }

                   @Override
                   public void _onEmpty() {

                   }

                   @Override
                   public void _onNetWorkError() {

                   }

                   @Override
                   public void _onServerError(String returnCode, String returnMsg) {

                   }

                   @Override
                   public void _onComplete() {

                   }
               });
    }

}
