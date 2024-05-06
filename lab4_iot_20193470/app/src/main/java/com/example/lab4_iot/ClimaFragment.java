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

public class ClimaFragment extends Fragment {
    private RecyclerView recyclerView;
    private ClimaAdapter adapter;
    private List<Clima> climaList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.clima_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_clima);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        climaList = new ArrayList<>();
        adapter = new ClimaAdapter(climaList);
        recyclerView.setAdapter(adapter);

        Button buscarButton = view.findViewById(R.id.buttonBuscar);
        buscarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText latitudEditText = view.findViewById(R.id.latitudEditText);
                EditText longitudEditText = view.findViewById(R.id.longitudEditText);

                String latitudStr = latitudEditText.getText().toString().trim();
                String longitudStr = longitudEditText.getText().toString().trim();

                if (!latitudStr.isEmpty() && !longitudStr.isEmpty()) {
                    double latitud = Double.parseDouble(latitudStr);
                    double longitud = Double.parseDouble(longitudStr);
                    obtenerDatosDeApi(latitud, longitud);
                } else {
                    Toast.makeText(getContext(), "Por favor ingrese la latitud y longitud", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void obtenerDatosDeApi(double latitud, double longitud) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClimaService service = retrofit.create(ClimaService.class);


        service.getClima(latitud, longitud, "792edf06f1f5ebcaf43632b55d8b03fe").enqueue(new Callback<Clima>() {
            @Override
            public void onResponse(Call<Clima> call, Response<Clima> response) {
                if (response.isSuccessful()) {
                    Clima clima = response.body();
                    actualizarRecyclerView(clima);
                } else {
                    Toast.makeText(getContext(), "Error al obtener los datos del clima", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Clima> call, Throwable t) {
                Toast.makeText(getContext(), "Error de red: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void actualizarRecyclerView(Clima clima) {
        climaList.add(clima);
        adapter.notifyDataSetChanged();
    }

}
