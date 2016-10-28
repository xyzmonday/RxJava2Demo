package richfit.com.rxjava2demo.model;

public class Today {
    private String city;

    private String comfort_index;

    private String date_y;

    private String dressing_advice;

    private String dressing_index;

    private String drying_index;

    private String exercise_index;

    private String temperature;

    private String travel_index;

    private String uv_index;

    private String wash_index;

    private String weather;

    private Weather_id weather_id;

    private String week;

    private String wind;

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setComfort_index(String comfort_index) {
        this.comfort_index = comfort_index;
    }

    public String getComfort_index() {
        return this.comfort_index;
    }

    public void setDate_y(String date_y) {
        this.date_y = date_y;
    }

    public String getDate_y() {
        return this.date_y;
    }

    public void setDressing_advice(String dressing_advice) {
        this.dressing_advice = dressing_advice;
    }

    public String getDressing_advice() {
        return this.dressing_advice;
    }

    public void setDressing_index(String dressing_index) {
        this.dressing_index = dressing_index;
    }

    public String getDressing_index() {
        return this.dressing_index;
    }

    public void setDrying_index(String drying_index) {
        this.drying_index = drying_index;
    }

    public String getDrying_index() {
        return this.drying_index;
    }

    public void setExercise_index(String exercise_index) {
        this.exercise_index = exercise_index;
    }

    public String getExercise_index() {
        return this.exercise_index;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setTravel_index(String travel_index) {
        this.travel_index = travel_index;
    }

    public String getTravel_index() {
        return this.travel_index;
    }

    public void setUv_index(String uv_index) {
        this.uv_index = uv_index;
    }

    public String getUv_index() {
        return this.uv_index;
    }

    public void setWash_index(String wash_index) {
        this.wash_index = wash_index;
    }

    public String getWash_index() {
        return this.wash_index;
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
        return "Today{" +
                "city='" + city + '\'' +
                ", comfort_index='" + comfort_index + '\'' +
                ", date_y='" + date_y + '\'' +
                ", dressing_advice='" + dressing_advice + '\'' +
                ", dressing_index='" + dressing_index + '\'' +
                ", drying_index='" + drying_index + '\'' +
                ", exercise_index='" + exercise_index + '\'' +
                ", temperature='" + temperature + '\'' +
                ", travel_index='" + travel_index + '\'' +
                ", uv_index='" + uv_index + '\'' +
                ", wash_index='" + wash_index + '\'' +
                ", weather='" + weather + '\'' +
                ", weather_id=" + weather_id +
                ", week='" + week + '\'' +
                ", wind='" + wind + '\'' +
                '}';
    }
}