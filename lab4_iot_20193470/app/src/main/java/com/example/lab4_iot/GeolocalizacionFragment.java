package com.example.lab4_iot;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeolocalizacionFragment extends Fragment {
    private RecyclerView recyclerView;
    private GeoAdapter adapter;
    private List<Geolocalizacion> geolocalizacionList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.geolocalizacion_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_geolocalizacion);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        geolocalizacionList = new ArrayList<>();
        adapter = new GeoAdapter(geolocalizacionList);
        recyclerView.setAdapter(adapter);

        Button buscarButton = view.findViewById(R.id.buttonBuscar);
        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ciudadEditText = view.findViewById(R.id.ediTextCity);
                String ciudad = ciudadEditText.getText().toString().trim();
                if (!ciudad.isEmpty()) {
                    obtenerDatosDeApi(ciudad);
                } else {
                    Toast.makeText(getContext(), "Por favor ingrese el nombre de una ciudad", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void obtenerDatosDeApi(String ciudad) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GeolocalizacionService service = retrofit.create(GeolocalizacionService.class);

        service.getGeolocalizacion(ciudad, 1, "8dd6fc3be19ceb8601c2c3e811c16cf1").enqueue(new Callback<List<Geolocalizacion>>() {
            @Override
            public void onResponse(Call<List<Geolocalizacion>> call, Response<List<Geolocalizacion>> response) {
                if (response.isSuccessful()) {
                    List<Geolocalizacion> geolocalizacionList = response.body();
                    actualizarRecyclerView(geolocalizacionList);
                } else {
                    Toast.makeText(getContext(), "Error al obtener los datos de la API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Geolocalizacion>> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizarRecyclerView(List<Geolocalizacion> geolocalizacionList) {
        geolocalizacionList.clear();
        geolocalizacionList.addAll(geolocalizacionList);
        adapter.notifyDataSetChanged();
    }
}
