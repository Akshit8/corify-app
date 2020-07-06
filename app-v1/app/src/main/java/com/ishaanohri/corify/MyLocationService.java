package com.ishaanohri.corify;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MyLocationService extends BroadcastReceiver {

    public static final String ACTION_PROCESS_UPDATE = "com.ishaanohri.corify.UPDATE_LOCATION";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://corify.herokuapp.com/")
            .addConverterFactory(ScalarsConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    API api = retrofit.create(API.class);
    Call<String> call;

    @Override
    public void onReceive(Context context, Intent intent) {

        if(intent != null){

            final String action = intent.getAction();
            if(ACTION_PROCESS_UPDATE.equals(action)){

                LocationResult result = LocationResult.extractResult(intent);

                if(result != null)
                {
                    Location location = result.getLastLocation();
                    String location_string = new StringBuilder(""+location.getLatitude())
                            .append("/")
                            .append(location.getLongitude())
                            .toString();
                    String latitude = new StringBuilder(""+location.getLatitude()).toString();
                    String longitude = new StringBuilder(""+location.getLongitude()).toString();

                    try {

                        Maps.getInstance().final_method(location_string);
                        //Toast.makeText(context,location_string,Toast.LENGTH_SHORT).show();
                        Log.i("LOCATION_1",location_string);

                    }catch (Exception e)
                    {
                        //Toast.makeText(context, "catch" +location_string, Toast.LENGTH_SHORT).show();
                        Log.i("LOCATION_2",location_string);

                        call = api.addLocation(latitude,longitude);
                        call.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                Log.i("INFO",response.body());
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Log.i("INFO",t.toString());

                            }
                        });
                    }
                }

            }

        }

    }
}
