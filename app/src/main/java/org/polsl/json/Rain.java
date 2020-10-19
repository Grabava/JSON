package org.polsl.json;

public class Rain
{
    private String h;

    public String get1h ()
    {
        return h;
    }

    public void set1h (String h)
    {
        this.h = h;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [1h = "+h+"]";
    }
}

