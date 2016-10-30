package richfit.com.rxjava2demo.retrofit2.http;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import richfit.com.rxjava2demo.retrofit2.api.IRequestApi;
import richfit.com.rxjava2demo.retrofit2.api.IRetrofitDownloadApi;
import richfit.com.rxjava2demo.retrofit2.api.IRetrofitUploadApi;
import richfit.com.rxjava2demo.retrofit2.global.Constant;


public class RetrofitModule {

//    public static IRequestApi getRequestApi() {
//
//        File httpCacheDirectory = new File(App.getAppContext().getCacheDir(), "responses");
//        int cacheSize = 10 * 1024 * 1024; // 10 MiB
//        Cache cache = new Cache(httpCacheDirectory, cacheSize);
//
//        OkHttpClient client = new OkHttpClient.Builder()
//                .addInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
//                .cache(cache).build();
//
//        Retrofit gankRetrofit = new Retrofit.Builder()
//                .baseUrl(Constant.BASE_URL)
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build();
//
//        return gankRetrofit.create(IRequestApi.class);
//    }
//
//    //cache
//    static Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = chain -> {
//
//        CacheControl.Builder cacheBuilder = new CacheControl.Builder();
//        cacheBuilder.maxAge(0, TimeUnit.SECONDS);
//        cacheBuilder.maxStale(365, TimeUnit.DAYS);
//        CacheControl cacheControl = cacheBuilder.build();
//
//        Request request = chain.request();
//        if (!NetWorkUtils.isNetConnected(App.getAppContext())) {
//            request = request.newBuilder()
//                    .cacheControl(cacheControl)
//                    .build();
//
//        }
//        Response originalResponse = chain.proceed(request);
//        if (NetWorkUtils.isNetConnected(App.getAppContext())) {
//            int maxAge = 0; // read from cache
//            return originalResponse.newBuilder()
//                    .removeHeader("Pragma")
//                    .header("Cache-Control", "public ,max-age=" + maxAge)
//                    .build();
//        } else {
//            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
//            return originalResponse.newBuilder()
//                    .removeHeader("Pragma")
//                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                    .build();
//        }
//    };


    /**
     * @return 下载文件
     */
    public static IRetrofitDownloadApi getDownloadApi() {
        //打印拦截器
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addNetworkInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());
                        return originalResponse.newBuilder()
                                .body(new ProgressResponseBody(originalResponse.body()))
                                .build();
                    }
                })
                .connectTimeout(15, TimeUnit.SECONDS)//设置请求超时时间
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(IRetrofitDownloadApi.class);
    }

    /**
     * @return
     */
    /**
     * @return 上传文件/图片
     */
    public static IRetrofitUploadApi getUploadApi() {
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)//设置请求超时时间
                .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient)
                .build();
        return retrofit.create(IRetrofitUploadApi.class);
    }

//
    private static IRequestApi request;

    /**
     * @return 一般网络请求 get/post/...
     */
    public static IRequestApi getRequestApi() {
        if (request == null) {
            //拦截器
            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request authorised = request
                            .newBuilder()
                            .header("registeredChannels", "2")//来自1：iOS,2:Android,3:web
                            .build();
                    return chain.proceed(authorised);
                }
            };
            //打印拦截器
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)//添加拦截器
                    .addInterceptor(logging)//添加打印拦截器
                    .connectTimeout(15, TimeUnit.SECONDS)//设置请求超时时间
                    .retryOnConnectionFailure(true)//设置出现错误进行重新连接。
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient)
                    .build();
            request = retrofit.create(IRequestApi.class);
        }
        return request;
    }
}

