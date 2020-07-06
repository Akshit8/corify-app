package com.ishaanohri.corify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Maps extends FragmentActivity implements OnMapReadyCallback {

    static Maps instance;
    LocationRequest locationRequest;
    FusedLocationProviderClient fusedLocationProviderClient;

    private ArrayList<String> cityList = new ArrayList<>();
    private ArrayList<String> latList = new ArrayList<>();
    private ArrayList<String> lonList = new ArrayList<>();
    private ArrayList<String> infoList = new ArrayList();

    private static Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://corify.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson));

    private static Retrofit retrofit = builder.build();


    public static Maps getInstance()
    {
        return instance;
    }

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);

        mapFragment.getMapAsync(this);

        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        updateLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
    }

    private void updateLocation() {
        buildLocationRequest();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,getPendingIntent());
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(this,MyLocationService.class);
        intent.setAction(MyLocationService.ACTION_PROCESS_UPDATE);
        return PendingIntent.getBroadcast(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10f);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        map.setInfoWindowAdapter(new CustomInfoWindowAdapter(Maps.this));

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.map_style));

            if (!success) {
                Log.e("INFO", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("INFO", "Can't find style. Error: ", e);
        }

        API api = retrofit.create(API.class);
        Call<Response> call;

        call = api.getAllCity();

        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {

                List list = response.body().getResponse();
                for(Object item : list)
                {
                    LinkedTreeMap<Object, Object> linkedTreeMap = (LinkedTreeMap) item;

                    String city = linkedTreeMap.get("city").toString();
                    String latitude = linkedTreeMap.get("latitude").toString();
                    String longitude = linkedTreeMap.get("longitude").toString();
                    String info = linkedTreeMap.get("info").toString();

                    cityList.add(city);
                    latList.add(latitude);
                    lonList.add(longitude);
                    infoList.add(info);

                }
                Log.i("INFO",cityList.get(0));

                for(int i=0;i<cityList.size();i++)
                {
                    Log.i("INFO","marker plotting.....");
                    map.addMarker(new MarkerOptions()
                            .position(new LatLng(Double.parseDouble(latList.get(i)),Double.parseDouble(lonList.get(i))))
                            .title(cityList.get(i))
                            .snippet(infoList.get(i))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
                }

            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {
                Log.i("INFO",t.toString());
            }
        });


//        LatLng delhi = new LatLng(28.527582,77.0688992);
//        LatLng delhi = new LatLng(Double.parseDouble(latList.get(0)),77.0688992);
//        LatLng mumbai = new LatLng(19.0825223,72.7410996);
//        LatLng chennai = new LatLng(13.0478223,80.0689249);
//
//
//        map.addMarker(new MarkerOptions()
//                .position(delhi)
//                .title("DELHI")
//                .snippet("20 cases of corona virus found\n90 quarantined")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
//        map.addMarker(new MarkerOptions()
//                .position(mumbai)
//                .title("MUMBAI")
//                .snippet("20 cases of corona virus found\n90 quarantined")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));
//        map.addMarker(new MarkerOptions()
//                .position(chennai)
//                .title("CHENNAI")
//                .snippet("20 cases of corona virus found\n90 quarantined")
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker)));

        LatLng india = new LatLng(20.0111576,79.4374869);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(india,4.5f));

        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent intent = new Intent(Maps.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void final_method(final String s){

        Maps.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Maps.this, "ui"+s, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
