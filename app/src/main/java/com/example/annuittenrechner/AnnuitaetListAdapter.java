package com.example.annuittenrechner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class AnnuitaetListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Annuitaet> annuitaets = Collections.emptyList();
    AnnuitaetListAdapter(){
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_annuitaet, parent, false);
        return new AnnuitaetViewHolder(view);
    }

    @Override
    public void onBindViewHolder
            (@NonNull RecyclerView.ViewHolder holder, int position){
        TextView annuitaetView = holder.itemView.findViewById(R.id.listitem);
        annuitaetView.setText(annuitaets.get(position).getAnnuitaet());
    }

    @Override
    public int getItemCount() {return annuitaets.size();}

    public void setAnnuitaets(List<Annuitaet> annuitaets){
        this.annuitaets = annuitaets;
        notifyDataSetChanged();
    }
}
