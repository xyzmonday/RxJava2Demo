package richfit.com.rxjava2demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.processors.PublishProcessor;
import richfit.com.rxjava2demo.activity.Practice13Activity;

public class RotationPersist2WorkerFragment
        extends Fragment {

    private IStreamProcessorListener mProcessedFragment;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private PublishProcessor<Integer> intStream = PublishProcessor.create();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof IStreamProcessorListener) {
            mProcessedFragment = (IStreamProcessorListener) context;
        }

        if (mProcessedFragment == null) {
            throw new ClassCastException("We did not find a master who can understand us :(");
        }
    }

    /**
     * This method will only be called once when the retained Fragment is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retain this fragment across configuration changes.
        setRetainInstance(true);

       Flowable.interval(1, TimeUnit.SECONDS)
                .map(Long::intValue)
                .take(Practice13Activity.MAX_PROGRESS)
                .subscribeWith(intStream);
        mCompositeDisposable.add(intStream.publish().connect());
    }

    /**
     * The Worker fragment has started doing it's thing
     */
    @Override
    public void onResume() {
        super.onResume();
        mProcessedFragment.setStream(intStream.toObservable());
    }

    /**
     * Set the callback to null so we don't accidentally leak the
     * Activity instance.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mProcessedFragment = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.isDisposed();
    }

    public interface IStreamProcessorListener {
        void setStream(Observable<Integer> intStream);
    }
}