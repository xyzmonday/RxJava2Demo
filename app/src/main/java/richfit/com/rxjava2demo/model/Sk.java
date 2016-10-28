package richfit.com.rxjava2demo.model;

public class Sk {
    private String humidity;

    private String temp;

    private String time;

    private String wind_direction;

    private String wind_strength;

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getHumidity() {
        return this.humidity;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemp() {
        return this.temp;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    public void setWind_direction(String wind_direction) {
        this.wind_direction = wind_direction;
    }

    public String getWind_direction() {
        return this.wind_direction;
    }

    public void setWind_strength(String wind_strength) {
        this.wind_strength = wind_strength;
    }

    public String getWind_strength() {
        return this.wind_strength;
    }

    @Override
    public String toString() {
        return "Sk{" +
                "humidity='" + humidity + '\'' +
                ", temp='" + temp + '\'' +
                ", time='" + time + '\'' +
                ", wind_direction='" + wind_direction + '\'' +
                ", wind_strength='" + wind_strength + '\'' +
                '}';
    }
}