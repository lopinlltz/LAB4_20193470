package com.example.lab4_iot;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ClimaAdapter extends RecyclerView.Adapter<ClimaAdapter.ViewHolder>{
    private List<Clima> climaList;

    public ClimaAdapter(List<Clima> climaList) {
        this.climaList = climaList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.clima_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Clima clima = climaList.get(position);
        holder.baseTextView.setText(clima.getBase());
        holder.tempminTextView.setText(String.valueOf(clima.getTemp_min()));
        holder.tempmaxTextView.setText(String.valueOf(clima.getTemp_max()));
        holder.tempmaxkTextView.setText(String.valueOf(clima.getTemp_max()));
    }

    @Override
    public int getItemCount() {
        return climaList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView baseTextView;
        TextView tempminTextView;
        TextView tempmaxTextView;
        TextView tempmaxkTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            baseTextView = itemView.findViewById(R.id.nombreBase);
            tempminTextView = itemView.findViewById(R.id.tempMin);
            tempmaxTextView = itemView.findViewById(R.id.tempMax);
            tempmaxkTextView = itemView.findViewById(R.id.tempMaxK);

        }
    }
}
