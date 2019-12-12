package com.example.mysport.POJO;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mysport.R;

import java.util.List;

public class AnnonceListAdapter extends BaseAdapter {

    private Context context;
    private List<Annonce> annonceList;

    public AnnonceListAdapter(Context context, List<Annonce> annonceList) {
        this.context = context;
        this.annonceList = annonceList;
    }

    @Override
    public int getCount() {
        return annonceList.size();
    }

    @Override
    public Object getItem(int position) {
        return annonceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, R.layout.activity_terrain_list_adapter,null);
        TextView terrainNom = v.findViewById(R.id.terrain_name);
        TextView terrainCapacity = v.findViewById(R.id.terrain_capacity);
        TextView terrainAdresse = v.findViewById(R.id.terrain_adresse);
        TextView date = v.findViewById(R.id.date);
        TextView typeSport = v.findViewById(R.id.type_terrain);

        terrainNom.setText(annonceList.get(position).terrain.getNom());
        terrainCapacity.setText((annonceList.get(position).terrain.getCapacity()) + " places disponible.");
        terrainAdresse.setText(annonceList.get(position).terrain.getAdresse() + " " + annonceList.get(position).terrain.getCodePostal());
        date.setText(annonceList.get(position).getDateDisponible().toString());
        typeSport.setText(annonceList.get(position).terrain.getTypeSport());


        v.setTag(annonceList.get(position).terrain.getId());

        return v;
    }
}
