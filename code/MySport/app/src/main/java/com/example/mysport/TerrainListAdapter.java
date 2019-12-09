package com.example.mysport;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class TerrainListAdapter extends BaseAdapter {

    private Context context;
    private List<TypeTerrain> terrainList;

    public TerrainListAdapter(Context context, List<TypeTerrain> terrainList) {
        this.context = context;
        this.terrainList = terrainList;
    }

    @Override
    public int getCount() {
        return terrainList.size();
    }

    @Override
    public Object getItem(int position) {
        return terrainList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context,R.layout.activity_terrain_list_adapter,null);
        TextView terrainNom = (TextView) v.findViewById(R.id.terrain_name);
        TextView terrainCapacity = (TextView) v.findViewById(R.id.terrain_capacity);
        TextView terrainAdresse = (TextView) v.findViewById(R.id.terrain_adresse);

        terrainNom.setText(terrainList.get(position).getNom());
        terrainCapacity.setText(String.valueOf(terrainList.get(position).getCapacity()) + " places disponible.");
        terrainAdresse.setText(terrainList.get(position).getAdresse());

        v.setTag(terrainList.get(position).getId());

        return v;
    }
}
