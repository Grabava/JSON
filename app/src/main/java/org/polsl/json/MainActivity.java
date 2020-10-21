package org.polsl.json;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends Activity implements LocationListener {

    private Forecast weather;
    private LocationManager locationManager;
    private LocationProvider locationProvider;
    private Double latitude;
    private Double longitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission(
                Manifest.permission.ACCESS_FINE_LOCATION, 100);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
        locationProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
        Location loc = locationManager.getLastKnownLocation(locationProvider.getName());
        latitude = loc.getLatitude();
        longitude = loc.getLongitude();


        new Connection().execute(weather);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    public void Draw(View view){
        TextView temperature = (TextView) findViewById(R.id.temperature);
        temperature.setText(this.weather.getCurrent().getTemp() + " °C");
    }

    public Forecast Download() {

        URL weatherURL = null;
        String adres = "https://api.openweathermap.org/data/2.5/onecall?lat="+latitude+"&lon="+longitude+"&units=metric&appid=2f869119e95ddde7a523b424e2b8e0ea&lang=pl";
        try {
            weatherURL = new URL(adres);
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


    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(
                getBaseContext(),
                "Location changed: Lat: ", Toast.LENGTH_SHORT).show();
         latitude =  location.getLatitude();
         longitude =  location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public class Connection extends AsyncTask<Forecast, Void, Forecast> {

        @Override
        protected Forecast doInBackground(Forecast... forecasts) {
            weather = Download();
            return weather;
        }


    }

    public void checkPermission(String permission, int requestCode)
    {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission)
                == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] { permission },
                    requestCode);
        }
        else {
            Toast.makeText(MainActivity.this,
                    "Permission already granted",
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {


                    Toast.makeText(MainActivity.this, "Permission denied to access your loaclization", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}

