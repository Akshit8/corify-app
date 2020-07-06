package com.ishaanohri.corify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pusher.pushnotifications.PushNotifications;

public class HealthScore extends AppCompatActivity {

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl("https://corify.herokuapp.com/")
            .addConverterFactory(ScalarsConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    API api = retrofit.create(API.class);
    Call<String> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_score);

        final CircularProgressIndicator circularProgressIndicator = findViewById(R.id.circular_progress);
        circularProgressIndicator.setMaxProgress(100);

        float t = UserDetails2.score/UserDetails2.total * 100;
        float a = 100 - Float.parseFloat(UserDetails1.age);

        final float healthScore = (Math.round(t) + (UserDetails3.final_score+10) + Math.round(a)) / 3;

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                circularProgressIndicator.setCurrentProgress(Math.round(healthScore));
                circularProgressIndicator.setProgressTextAdapter(new CircularProgressIndicator.ProgressTextAdapter() {
                    @NonNull
                    @Override
                    public String formatText(double currentProgress) {
                        return (Math.round(healthScore) + "%");
                    }
                });
            }
        }, 100);


        PushNotifications.start(getApplicationContext(), "38c10eae-d5f0-422b-bbcd-53b6f1f33391");
        PushNotifications.addDeviceInterest("hello");

        String id = FirebaseInstanceId.getInstance().getToken();

        Log.i("INFO_ID", id);

        float hscore = Math.round(healthScore);
        //Toast.makeText(this, ""+hscore, Toast.LENGTH_SHORT).show();

        call = api.login(id,Float.toString(hscore));


        findViewById(R.id.nextButton3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.i("INFO",response.body());

                        SharedPreferences sp =getSharedPreferences("checkbox", MODE_PRIVATE);
                        SharedPreferences.Editor et = sp.edit();
                        et.putBoolean("isLogin", true);
                        et.apply();

                        Intent intent = new Intent(HealthScore.this,Maps.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.i("INFO",t.toString());
                    }
                });
            }
        });

    }
}
