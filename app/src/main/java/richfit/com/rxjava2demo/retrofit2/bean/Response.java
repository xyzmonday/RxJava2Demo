package richfit.com.rxjava2demo.retrofit2.bean;

/**
 * 服务返回的json数据，假设为一下形式：
 * {"returnCode": "S", "returnMsg": "success", "data":[{...}, {...}], "currentPage": 1, "pageSize": 20, "maxCount": 2, "maxPage": 1}
 * 所以这样封装的实体类如下
 * @param <T>
 */
public class Response<T> {
    private String returnCode;
    private String returnMsg;
    private T data;

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMsg() {
        return returnMsg;
    }

    public void setReturnMsg(String returnMsg) {
        this.returnMsg = returnMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "returnCode=" + returnCode +
                ", returnMsg='" + returnMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
