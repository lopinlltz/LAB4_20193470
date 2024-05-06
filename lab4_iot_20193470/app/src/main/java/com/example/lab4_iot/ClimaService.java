package com.example.lab4_iot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClimaService {
    @GET("data/2.5/weather")
    Call<Clima> getClima(@Query("lat") double latitud, @Query("lon") double longitud, @Query("appid") String apiKey);}
