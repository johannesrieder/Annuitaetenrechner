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

public class AnnuitaetsparameterListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Annuitaetsparameter> aps = Collections.emptyList();
    private final AnnuitaetsparameterDao dao;

    public AnnuitaetsparameterListAdapter(AnnuitaetsparameterDao dao){
        this.dao = dao;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder
            (@NonNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem_annuitaet, parent, false);
        return new AnnuitaetsparameterViewHolder(view);
    }

    @Override
    public void onBindViewHolder
            (@NonNull RecyclerView.ViewHolder holder, int position){
        TextView annuitaetView = holder.itemView.findViewById(R.id.listitem);
        TextView darlehenView = holder.itemView.findViewById(R.id.listitem2);
        TextView zinsView = holder.itemView.findViewById(R.id.listitem3);
        TextView laufzeitView = holder.itemView.findViewById(R.id.listitem4);


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        annuitaetView.setText(aps.get(position).getAnnuität());
        annuitaetView.setOnClickListener((view) ->{
            new DeleteAnnuitaetsparameterTask(dao,this).execute(aps.get(position));
        });
        darlehenView.setText(aps.get(position).getDarlehenssumme());
        zinsView.setText(aps.get(position).getZinssatz());
        laufzeitView.setText(aps.get(position).getLaufzeit());
    }

    @Override
    public int getItemCount() {
        return aps.size();}

    public void setAps(List<Annuitaetsparameter> aps){
        this.aps = aps;
        notifyDataSetChanged();
    }
    static class DeleteAnnuitaetsparameterTask extends AsyncTask<Annuitaetsparameter, Void, List<Annuitaetsparameter>> {

        private final AnnuitaetsparameterDao dao;
        private final AnnuitaetsparameterListAdapter adapter;

        public DeleteAnnuitaetsparameterTask(AnnuitaetsparameterDao dao, AnnuitaetsparameterListAdapter adapter) {
            this.dao = dao;
            this.adapter = adapter;
        }

        @Override
        protected List<Annuitaetsparameter> doInBackground(Annuitaetsparameter... aps) {
            dao.delete(aps[0]);
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<Annuitaetsparameter> aps) {
            super.onPostExecute(aps);
            adapter.setAps(aps);
        }
    }


}