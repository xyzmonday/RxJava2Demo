package richfit.com.rxjava2demo.activity;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import io.reactivex.functions.Consumer;
import io.reactivex.subscribers.ResourceSubscriber;
import okhttp3.ResponseBody;
import richfit.com.rxjava2demo.retrofit2.http.ProgressWrapper;
import richfit.com.rxjava2demo.retrofit2.http.RetrofitModule;
import richfit.com.rxjava2demo.rxbus.RxManager;
import richfit.com.rxjava2demo.rxbus.TransformerHelper;

/**
 * Created by monday on 2016/10/25.
 */

public class Practice12Activity extends BasePracticeActivity {

    private static final String FILE_NAME = "update.apk";

    RxManager mRxManager;

    @Override
    protected void initData() {
        mProgress.setVisibility(View.VISIBLE);
        mTvProgress.setVisibility(View.VISIBLE);
        mProgress.setMax(100); // 设置进度条最大值
        mRxManager = RxManager.getInstance();
        practiceRetrofitApi();
    }

    @Override
    protected void initEvent() {
        /**注意这里必须使用被压*/
        mRxManager.register("download_event", (Consumer<ProgressWrapper>) progressWrapper -> {
            mProgress.setProgress((int) (((double) progressWrapper.getProgress() * 100 /
                    (double)progressWrapper.getTotalLength())));

            mTvProgress.setText("完成百分比 = " + ((double) progressWrapper.getProgress() * 100 /
                            (double)progressWrapper.getTotalLength()) + "%");
        }, RxManager.BACKPRESSURE_STRATEGY_BUFFER);
    }


    private void practiceRetrofitApi() {

        ResourceSubscriber<ResponseBody> dispose = RetrofitModule
                .getDownloadApi()
                .downLoadApk(FILE_NAME)
                .compose(TransformerHelper.io2main())
                .subscribeWith(new ResourceSubscriber<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody responseBody) {

                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.d("yff", "error = " + t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(Practice12Activity.this,
                                "下载完成", Toast.LENGTH_SHORT).show();
                    }
                });

        mCompositeDisposable.add(dispose);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRxManager.unRegister();
    }
}
