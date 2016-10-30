package richfit.com.rxjava2demo.retrofit2.http;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;
import richfit.com.rxjava2demo.rxbus.RxManager;

/**
 * 详细的OkIO的源码分析请看我后面的源码分析。简单地说OKIO的思想就是
 * 将数据（字节）采用链表的形式缓存起来，这样读些数据只需要将操作链表即可。
 */
public class ProgressResponseBody extends ResponseBody {
    public interface ProgressListener {
        void update(long bytesRead, long contentLength, boolean done);
    }

    private final ResponseBody responseBody;
    private final RxManager mRxManager;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
        this.mRxManager = RxManager.getInstance();
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(source(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long bytesReaded = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                final long bytesRead = super.read(sink, byteCount);
                // read() returns the number of bytes read, or -1 if this source is exhausted.
                bytesReaded += bytesRead != -1 ? bytesRead : 0;
//                new Handler(Looper.getMainLooper()).post(new Runnable() {
//                    @Override
//                    public void run() {
//                        progressListener.update(totalBytesRead, responseBody.contentLength(), bytesRead == -1);
//                    }
//                });
                mRxManager.post("download_event", new ProgressWrapper(contentLength(), bytesReaded));
                return bytesRead;
            }
        };
    }
}


