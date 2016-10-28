package richfit.com.rxjava2demo.retrofit2.api;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import richfit.com.rxjava2demo.model.Weather;
import richfit.com.rxjava2demo.retrofit2.bean.Response;

/**
 * Created by wukewei on 16/5/26.
 */
public interface IRequestApi {

    @GET("{fileName}")
    Flowable<Response<Weather>> getWeatherData(@Path("fileName") String fileName);

}
