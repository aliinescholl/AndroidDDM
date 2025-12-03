package com.example.app;

import android.content.Context;;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class PlanetAdapter extends ArrayAdapter<Planet> {
    Context mContext;
    Integer mResource;
    public PlanetAdapter(@NonNull Context context, int resource, @NonNull List<Planet> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        listItemView = layoutInflater.inflate(mResource, parent, false);

        Planet planet = getItem(position);

        ImageView img = listItemView.findViewById(R.id.imagemPlaneta);
        TextView txt = listItemView.findViewById(R.id.nomePlaneta);
        img.setImageResource(planet.image);
        txt.setText(planet.nome);

        return listItemView;
    }
}
