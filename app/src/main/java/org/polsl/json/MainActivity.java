package org.polsl.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Forecast pogoda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Connection();
        TextView temperature = (TextView) findViewById(R.id.temperature);
       temperature.setText(pogoda.toString() + " C");

    }

    public Forecast pobierz() {

        URL weatherURL = null;
        try {
            weatherURL = new URL("https://api.openweathermap.org/data/2.5/onecall?lat=50.3&lon=18.68&units=metric&appid=6bda4862dcc638f53d7a4758b0c6efab&lang=pl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection weatherConnection = null;
        try {
            weatherConnection = (HttpURLConnection) weatherURL.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if (weatherConnection.getResponseCode() == 200) {
                InputStreamReader is = new InputStreamReader(weatherConnection.getInputStream());
                Gson gson = new Gson();
                Forecast pogo = gson.fromJson(is, Forecast.class);
                weatherConnection.disconnect();
                return pogo;


            } else {
                // Błąd - można wyświetlić komunikat

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }


    public void Connection()
    {

        AsyncTask.execute(new Runnable() {
            @Override

            public void run() {
                pogoda = pobierz();
                pogoda.toString();
            }
        });
    }

}

