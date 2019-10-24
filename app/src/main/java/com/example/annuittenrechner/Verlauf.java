package com.example.annuittenrechner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;
public class Verlauf extends AppCompatActivity implements View.OnClickListener {

    private AnnuitaetDao dao;
    private RecyclerView recyclerView;
    private AnnuitaetListAdapter adapter;
    Button HStartseiteButton;
    ImageView iVhelpicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verlauf_display);

        dao = AnnuitaetRoomDatabase.getDatabase(this).annuitaetDao();

        recyclerView = findViewById(R.id.annuitaet_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnnuitaetListAdapter(dao);
        recyclerView.setAdapter(adapter);
        HStartseiteButton = findViewById(R.id.HStartseiteButton);
        iVhelpicon = findViewById(R.id.iVhelpicon);
        HStartseiteButton.setOnClickListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        new LadeAnnuitaetsTask(dao,adapter).execute();
    }

    @Override
    public void onClick(View view) {

        if (view == HStartseiteButton){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }

        if(view == iVhelpicon){
            Intent intent = new Intent(this,Hilfe.class);
            startActivity(intent);
        }


    }

    static class LadeAnnuitaetsTask extends AsyncTask<Void, Void, List<Annuitaet>>{

        private final AnnuitaetDao dao;
        private final AnnuitaetListAdapter adapter;

        public LadeAnnuitaetsTask(AnnuitaetDao dao, AnnuitaetListAdapter adapter) {
            this.dao = dao;
            this.adapter = adapter;
        }

        @Override
        protected List<Annuitaet> doInBackground(Void... voids) {
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<Annuitaet> annuitaets) {
            super.onPostExecute(annuitaets);
            adapter.setAnnuitaets(annuitaets);
        }
    }
}
