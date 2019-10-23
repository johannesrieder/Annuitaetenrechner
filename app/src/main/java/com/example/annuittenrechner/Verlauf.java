package com.example.annuittenrechner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.List;

public class Verlauf extends AppCompatActivity {

    private AnnuitaetDao dao;
    private RecyclerView recyclerView;
    private AnnuitaetListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verlauf_display);

        recyclerView = findViewById(R.id.annuitaet_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnnuitaetListAdapter();
        recyclerView.setAdapter(adapter);

        dao = AnnuitaetRoomDatabase.getDatabase(this).annuitaetDao();
    }

    @Override
    protected void onResume(){
        super.onResume();
        new LadeAnnuitaetsTask().execute();
    }

    class LadeAnnuitaetsTask extends AsyncTask<Void, Void, List<Annuitaet>>{

        @Override
        protected List<Annuitaet> doInBackground(Void...voids){
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<Annuitaet> annuitaets){
            super.onPostExecute(annuitaets);
            adapter.setAnnuitaets(annuitaets);
        }
    }
}
