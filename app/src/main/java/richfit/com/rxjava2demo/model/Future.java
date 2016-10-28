package richfit.com.rxjava2demo.model;

public class Future {
    private String date;

    private String temperature;

    private String weather;

    private Weather_id weather_id;

    private String week;

    private String wind;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWeather() {
        return this.weather;
    }

    public void setWeather_id(Weather_id weather_id) {
        this.weather_id = weather_id;
    }

    public Weather_id getWeather_id() {
        return this.weather_id;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getWeek() {
        return this.week;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getWind() {
        return this.wind;
    }

    @Override
    public String toString() {
        return "Future{" +
                "date='" + date + '\'' +
                ", temperature='" + temperature + '\'' +
                ", weather='" + weather + '\'' +
                ", weather_id=" + weather_id +
                ", week='" + week + '\'' +
                ", wind='" + wind + '\'' +
                '}';
    }
}