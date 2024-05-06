package com.example.lab4_iot;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeolocalizacionService {
    @GET("geo/1.0/direct")
    Call<List<Geolocalizacion>> getGeolocalizacion(@Query("q") String cityName, @Query("limit") int limit, @Query("appid") String apiKey);
}

