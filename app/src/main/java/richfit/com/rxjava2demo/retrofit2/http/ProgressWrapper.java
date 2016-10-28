package richfit.com.rxjava2demo.retrofit2.http;

public class ProgressWrapper {
    private long totalLength;
    private long progress;

    public ProgressWrapper(long totalLength, long progress) {
        this.totalLength = totalLength;
        this.progress = progress;
    }

    public long getTotalLength() {
        return totalLength;
    }

    public void setTotalLength(long totalLength) {
        this.totalLength = totalLength;
    }

    public long getProgress() {
        return progress;
    }

    public void setProgress(long progress) {
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "ProgressWrapper{" +
                "totalLength=" + totalLength +
                ", progress=" + progress +
                '}';
    }
}