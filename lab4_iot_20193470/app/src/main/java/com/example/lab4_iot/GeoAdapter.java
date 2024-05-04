package com.example.lab4_iot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GeoAdapter extends RecyclerView.Adapter<GeoAdapter.ViewHolder> {
    private List<Geolocalizacion> geolocalizacionList;

    public GeoAdapter(List<Geolocalizacion> geolocalizacionList) {
        this.geolocalizacionList = geolocalizacionList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.geolocalizacion_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Geolocalizacion geolocalizacion = geolocalizacionList.get(position);
        holder.cityNameTextView.setText(geolocalizacion.getName());
        holder.latTextView.setText(String.valueOf(geolocalizacion.getLat()));
        holder.longTextView.setText(String.valueOf(geolocalizacion.getLon()));
    }

    @Override
    public int getItemCount() {
        return geolocalizacionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView cityNameTextView;
        TextView latTextView;
        TextView longTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cityNameTextView = itemView.findViewById(R.id.item_city_name);
            latTextView = itemView.findViewById(R.id.item_lat);
            longTextView = itemView.findViewById(R.id.item_long);
        }
    }
}

