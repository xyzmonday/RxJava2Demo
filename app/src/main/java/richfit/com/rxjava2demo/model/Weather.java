package richfit.com.rxjava2demo.model;

import java.util.List;

/**
 * Created by monday on 2016/10/27.
 */

public class Weather {

    private List<Future> future ;

    private Sk sk;

    private Today today;

    public void setFuture(List<Future> future){
        this.future = future;
    }
    public List<Future> getFuture(){
        return this.future;
    }
    public void setSk(Sk sk){
        this.sk = sk;
    }
    public Sk getSk(){
        return this.sk;
    }
    public void setToday(Today today){
        this.today = today;
    }
    public Today getToday(){
        return this.today;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "future=" + future +
                ", sk=" + sk +
                ", today=" + today +
                '}';
    }
}
