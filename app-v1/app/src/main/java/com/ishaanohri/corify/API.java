package com.ishaanohri.corify;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {

    @POST("login/{fcm}/{healthScore}")
    Call<String> login(
            @Path("fcm") String id,
            @Path("healthScore") String healthScore
    );
    @GET("getAllCityData")
    Call<Response> getAllCity();

    @POST("addLocation/{lat}/{lng}")
    Call<String> addLocation(
            @Path("lat") String lat,
            @Path("lng") String lng
    );

}
