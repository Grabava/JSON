package org.polsl.json;

public class Minutely
{
    private String dt;

    private String precipitation;

    public String getDt ()
    {
        return dt;
    }

    public void setDt (String dt)
    {
        this.dt = dt;
    }

    public String getPrecipitation ()
    {
        return precipitation;
    }

    public void setPrecipitation (String precipitation)
    {
        this.precipitation = precipitation;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dt = "+dt+", precipitation = "+precipitation+"]";
    }
}
