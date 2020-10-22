package org.polsl.json;


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


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends Activity implements LocationListener, OnMapReadyCallback {

    private Forecast weather;
    private Double latitude;
    private Double longitude;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission(
                Manifest.permission.ACCESS_FINE_LOCATION, 100);
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
        LocationProvider locationProvider = locationManager.getProvider(LocationManager.GPS_PROVIDER);
        Location loc = locationManager.getLastKnownLocation(locationProvider.getName());
        latitude = loc.getLatitude();
        longitude = loc.getLongitude();

        new Connection().execute(weather);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mapView = (MapView) findViewById(R.id.mapView);
        mapView.onCreate(Bundle.EMPTY);
        mapView.getMapAsync(this);
        if(weather.getCurrent() == null){
            Toast.makeText(MainActivity.this, "Unable to get data from server please reload", Toast.LENGTH_SHORT).show();

        }else {
            TextView temperature = (TextView) findViewById(R.id.temperature);
            temperature.setText(this.weather.getCurrent().getTemp() + " °C");
            TextView feel = (TextView) findViewById(R.id.Feels);
            feel.setText(this.weather.getCurrent().getFeels_like() + " °C");
            TextView cloud = (TextView) findViewById(R.id.Cloud);
            cloud.setText(this.weather.getCurrent().getClouds() + " %");
            TextView wind = (TextView) findViewById(R.id.Wind);
            wind.setText(this.weather.getCurrent().getWind_speed() + " km/h");
        }

    }
    public void Draw(View view){
        TextView temperature = (TextView) findViewById(R.id.temperature);
        temperature.setText(this.weather.getCurrent().getTemp() + " °C");
        TextView feel = (TextView) findViewById(R.id.Feels);
        feel.setText(this.weather.getCurrent().getFeels_like() + " °C");
        TextView cloud = (TextView) findViewById(R.id.Cloud);
        cloud.setText(this.weather.getCurrent().getClouds() + " %" );
        TextView wind = (TextView) findViewById(R.id.Wind);
        wind.setText(this.weather.getCurrent().getWind_speed() + " km/h");

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

                Toast.makeText(MainActivity.this, "Unable to connect to server", Toast.LENGTH_SHORT).show();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }


    @Override
    public void onLocationChanged(Location location) {

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

    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.getUiSettings().setAllGesturesEnabled(false);
        LatLng location = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(location).title("Your Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
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
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                } else {


                    Toast.makeText(MainActivity.this, "Permission denied to access your loaclization", Toast.LENGTH_SHORT).show();
                }
                return;
            }


        }
    }

    @Override
    protected void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }



}

