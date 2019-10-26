package com.example.annuittenrechner;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Verlauf extends AppCompatActivity implements View.OnClickListener {

    private AnnuitaetsparameterDao dao;
    private RecyclerView recyclerView;
    private AnnuitaetsparameterListAdapter adapter;
    Button HStartseiteButton;
    ImageView iVLöschen;
    ImageView iVhelpicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verlauf_display);

        dao = AnnuitaetsparameterRoomDatabase.getDatabase(this).annuitaetsparameterDao();

        recyclerView = findViewById(R.id.annuitaet_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AnnuitaetsparameterListAdapter(dao);
        recyclerView.setAdapter(adapter);
        HStartseiteButton = findViewById(R.id.HStartseiteButton);
        iVhelpicon = findViewById(R.id.iVhelpicon);
        iVLöschen = findViewById(R.id.bLöschen);
        HStartseiteButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new LadeAnnuitaetsparameterTask(dao, adapter).execute();
    }

    @Override
    public void onClick(View view) {

        if (view == HStartseiteButton) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        if (view == iVhelpicon) {
            Intent intent = new Intent(this, Hilfe.class);
            startActivity(intent);
        }
        if (view == iVLöschen) {
            /////////////////////////////////////////////////////////////////////////

        }
    }


    static class LadeAnnuitaetsparameterTask extends AsyncTask<Void, Void, List<Annuitaetsparameter>> {

        private final AnnuitaetsparameterDao dao;
        private final AnnuitaetsparameterListAdapter adapter;

        public LadeAnnuitaetsparameterTask(AnnuitaetsparameterDao dao, AnnuitaetsparameterListAdapter adapter) {
            this.dao = dao;
            this.adapter = adapter;
        }

        @Override
        protected List<Annuitaetsparameter> doInBackground(Void... voids) {
            return dao.getAll();
        }

        @Override
        protected void onPostExecute(List<Annuitaetsparameter> aps) {
            super.onPostExecute(aps);
            adapter.setAps(aps);
        }
    }
}

