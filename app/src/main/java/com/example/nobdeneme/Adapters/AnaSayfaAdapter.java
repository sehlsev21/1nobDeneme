package com.example.nobdeneme.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nobdeneme.MapsActivity;
import com.example.nobdeneme.Models.Eczaneler;
import com.example.nobdeneme.R;

import java.util.ArrayList;

public class AnaSayfaAdapter extends RecyclerView.Adapter<AnaSayfaAdapter.RowHolder> {

    private ArrayList<Eczaneler> eczaneVeriler;
    private Context mContext;

    public AnaSayfaAdapter(ArrayList<Eczaneler> eczaneVeriler, Context mContext) {
        this.eczaneVeriler = eczaneVeriler;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public RowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout,parent,false);
        return new RowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RowHolder holder, int position) {
        Eczaneler eczaneler = eczaneVeriler.get(position);

        holder.textAdress.setText(eczaneler.getAdresi());
        holder.textCity.setText(eczaneler.getSehir() + " - " + eczaneler.getIlce());
        holder.textName.setText(eczaneler.getEczaneAdi());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MapsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("lati",eczaneler.getLatitude());
                bundle.putDouble("longi",eczaneler.getLongitude());
                bundle.putString("eczaneAdi",eczaneler.getEczaneAdi());
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return eczaneVeriler.size();
    }

    public class RowHolder extends RecyclerView.ViewHolder {
        TextView textName, textAdress, textCity;
        CardView card;

        public RowHolder(@NonNull View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.textName);
            textAdress = itemView.findViewById(R.id.textAddress);
            textCity = itemView.findViewById(R.id.textCity);
            card = itemView.findViewById(R.id.card);
        }
    }
}

