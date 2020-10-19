package org.polsl.json;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

final public class Forecast
{
    private Current current;

    private String timezone;

    private String timezone_offset;

    private Daily[] daily;

    private String lon;

    private Hourly[] hourly;

    private Minutely[] minutely;

    private String lat;

    public Current getCurrent ()
    {
        return current;
    }

    public void setCurrent (Current current)
    {
        this.current = current;
    }

    public String getTimezone ()
    {
        return timezone;
    }

    public void setTimezone (String timezone)
    {
        this.timezone = timezone;
    }

    public String getTimezone_offset ()
    {
        return timezone_offset;
    }

    public void setTimezone_offset (String timezone_offset)
    {
        this.timezone_offset = timezone_offset;
    }

    public Daily[] getDaily ()
    {
        return daily;
    }

    public void setDaily (Daily[] daily)
    {
        this.daily = daily;
    }

    public String getLon ()
    {
        return lon;
    }

    public void setLon (String lon)
    {
        this.lon = lon;
    }

    public Hourly[] getHourly ()
    {
        return hourly;
    }

    public void setHourly (Hourly[] hourly)
    {
        this.hourly = hourly;
    }

    public Minutely[] getMinutely ()
    {
        return minutely;
    }

    public void setMinutely (Minutely[] minutely)
    {
        this.minutely = minutely;
    }

    public String getLat ()
    {
        return lat;
    }

    public void setLat (String lat)
    {
        this.lat = lat;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [current = "+current+", timezone = "+timezone+", timezone_offset = "+timezone_offset+", daily = "+daily+", lon = "+lon+", hourly = "+hourly+", minutely = "+minutely+", lat = "+lat+"]";
    }



}
