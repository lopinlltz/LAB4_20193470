package com.example.lab4_iot;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeoApiService {
    private static final String BASE_URL = "http://api.openweathermap.org";
    private static final String API_KEY = "8dd6fc3be19ceb8601c2c3e811c16cf1";
    private static final int LIMIT = 5;

    private GeolocalizacionService service;

    public GeoApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(GeolocalizacionService.class);
    }

    public void getGeolocalizaciones(String cityName, final GeoCallback callback) {
        Call<List<Geolocalizacion>> call = service.getGeolocalizacion(cityName, LIMIT, API_KEY);
        call.enqueue(new Callback<List<Geolocalizacion>>() {
            @Override
            public void onResponse(Call<List<Geolocalizacion>> call, Response<List<Geolocalizacion>> response) {
                if (response.isSuccessful()) {
                    List<Geolocalizacion> geolocalizaciones = response.body();
                    callback.onSuccess(geolocalizaciones);
                } else {
                    callback.onError("Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Geolocalizacion>> call, Throwable t) {
                callback.onError("Error: " + t.getMessage());
            }
        });
    }

    public interface GeoCallback {
        void onSuccess(List<Geolocalizacion> geolocalizaciones);

        void onError(String message);
    }
}
