package com.example.annuittenrechner;

import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

class AnnuitaetViewHolder extends RecyclerView.ViewHolder {
    AnnuitaetViewHolder(@NonNull View itemView){
        super(itemView);
    }
}

public class AnnuitaetListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Annuitaet> annuitaets = Collections.emptyList();
    private final AnnuitaetDao dao;

    public AnnuitaetListAdapter(AnnuitaetDao dao){
        this.dao = dao;
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
        annuitaetView.setOnClickListener((view) ->{
         new DeleteAnnuitaetTask(dao,this).execute(annuitaets.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return annuitaets.size();}

    public void setAnnuitaets(List<Annuitaet> annuitaets){
        this.annuitaets = annuitaets;
        notifyDataSetChanged();
    }
    static class DeleteAnnuitaetTask extends AsyncTask<Annuitaet, Void, List<Annuitaet>> {

        private final AnnuitaetDao dao;
        private final AnnuitaetListAdapter adapter;

        public DeleteAnnuitaetTask(AnnuitaetDao dao, AnnuitaetListAdapter adapter) {
            this.dao = dao;
            this.adapter = adapter;
        }

        @Override
        protected List<Annuitaet> doInBackground(Annuitaet... annuitaets) {
            dao.delete(annuitaets[0]);
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<Annuitaet> annuitaets) {
            super.onPostExecute(annuitaets);
            adapter.setAnnuitaets(annuitaets);
        }
    }


}
