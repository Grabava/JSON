package org.polsl.json;

public class Hourly
{
    private String dt;

    private String pop;

    private String temp;

    private String wind_deg;

    private String visibility;

    private String dew_point;

    private Weather[] weather;

    private String humidity;

    private String wind_speed;

    private String pressure;

    private String clouds;

    private String feels_like;

    public String getDt ()
    {
        return dt;
    }

    public void setDt (String dt)
    {
        this.dt = dt;
    }

    public String getPop ()
    {
        return pop;
    }

    public void setPop (String pop)
    {
        this.pop = pop;
    }

    public String getTemp ()
    {
        return temp;
    }

    public void setTemp (String temp)
    {
        this.temp = temp;
    }

    public String getWind_deg ()
    {
        return wind_deg;
    }

    public void setWind_deg (String wind_deg)
    {
        this.wind_deg = wind_deg;
    }

    public String getVisibility ()
    {
        return visibility;
    }

    public void setVisibility (String visibility)
    {
        this.visibility = visibility;
    }

    public String getDew_point ()
    {
        return dew_point;
    }

    public void setDew_point (String dew_point)
    {
        this.dew_point = dew_point;
    }

    public Weather[] getWeather ()
    {
        return weather;
    }

    public void setWeather (Weather[] weather)
    {
        this.weather = weather;
    }

    public String getHumidity ()
    {
        return humidity;
    }

    public void setHumidity (String humidity)
    {
        this.humidity = humidity;
    }

    public String getWind_speed ()
    {
        return wind_speed;
    }

    public void setWind_speed (String wind_speed)
    {
        this.wind_speed = wind_speed;
    }

    public String getPressure ()
    {
        return pressure;
    }

    public void setPressure (String pressure)
    {
        this.pressure = pressure;
    }

    public String getClouds ()
    {
        return clouds;
    }

    public void setClouds (String clouds)
    {
        this.clouds = clouds;
    }

    public String getFeels_like ()
    {
        return feels_like;
    }

    public void setFeels_like (String feels_like)
    {
        this.feels_like = feels_like;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dt = "+dt+", pop = "+pop+", temp = "+temp+", wind_deg = "+wind_deg+", visibility = "+visibility+", dew_point = "+dew_point+", weather = "+weather+", humidity = "+humidity+", wind_speed = "+wind_speed+", pressure = "+pressure+", clouds = "+clouds+", feels_like = "+feels_like+"]";
    }
}
