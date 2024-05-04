package com.example.lab4_iot;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppActivity extends AppCompatActivity {
    //private NavController navController;
    private RecyclerView recyclerView;
    private GeoAdapter adapter;
    private GeoApiService geoApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_principal);

        recyclerView = findViewById(R.id.fragmentContainerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        geoApiService = new GeoApiService();
        loadData();
    }

    private void loadData() {
        geoApiService.getGeolocalizaciones("Lima", new GeoApiService.GeoCallback() {
            @Override
            public void onSuccess(List<Geolocalizacion> geolocalizaciones) {
                adapter.setData(geolocalizaciones);
            }

            @Override
            public void onError(String message) {
            }
        });
    }

}
