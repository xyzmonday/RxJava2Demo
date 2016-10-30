package richfit.com.rxjava2demo.activity;

import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import richfit.com.rxjava2demo.R;
import richfit.com.rxjava2demo.base.BaseActivity;

/**
 * Created by monday on 2016/10/25.
 */

public abstract class BasePracticeActivity extends BaseActivity{

    @BindView(R.id.tv_send)
    protected TextView mTvSend;

    @BindView(R.id.tv_receiver)
    protected TextView mTvReceiver;

    @BindView(R.id.progress_bar)
    ProgressBar mProgress;

    @BindView(R.id.tv_progress_text)
    TextView mTvProgress;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_base_practice;
    }

    public String[] getDefaultStringArray() {
        String[] strs = new String[5];
        strs[0] = "one";
        strs[1] = "two";
        strs[2] = "three";
        strs[3] = "four";
        strs[4] = "five";
        return strs;
    }

    public int[] getDefaultIntegerArray() {
        int[] nums = new int[10];
        nums[0] = 1;
        nums[1] = 2;
        nums[2] = 3;
        nums[3] = 4;
        nums[4] = 5;
        nums[5] = 6;
        nums[6] = 7;
        nums[7] = 8;
        nums[8] = 9;
        nums[9] = 10;
        return nums;
    }
}
