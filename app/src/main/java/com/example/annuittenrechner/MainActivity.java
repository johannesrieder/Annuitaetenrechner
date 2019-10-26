package com.example.annuittenrechner;

import java.util.Calendar;
import java.util.Date;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AnnuitaetDao dao;
    private String annuität = "";

    EditText eTDarlehenssumme;
    EditText eTZinssatz;
    EditText eTLaufzeit;
    EditText eTKommentar;

    protected Button ZSAnnuitätButton;
    protected Button ZVerlaufButton;

    ImageView iVhelpicon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = AnnuitaetRoomDatabase.getDatabase(this).annuitaetDao();

        eTDarlehenssumme = findViewById(R.id.eTDarlehenssumme);
        eTZinssatz = findViewById(R.id.eTZinssatz);
        eTLaufzeit = findViewById(R.id.eTLaufzeit);
        eTKommentar = findViewById(R.id.eTKommentar);

        ZSAnnuitätButton = findViewById(R.id.AnnuitätButton);
        ZSAnnuitätButton.setOnClickListener(this);
        ZVerlaufButton = findViewById(R.id.BerechnungsverlaufButton);
        ZVerlaufButton.setOnClickListener(this);
        iVhelpicon = findViewById(R.id.iVhelpicon);
    }

    public double leseDarlehenssumme() { return Double.parseDouble(eTDarlehenssumme.getText().toString()); }

    public double leseZinssatz() { return Double.parseDouble(eTZinssatz.getText().toString())/100; }

    public int leseLaufzeit() { return Integer.parseInt(eTLaufzeit.getText().toString()); }

    public double berechneAnnuität(double darlehenssumme, double zinssatz, int laufzeit) {
        double annuität = darlehenssumme * (Math.pow(1 + zinssatz, laufzeit) * (zinssatz / (Math.pow(1 + zinssatz, laufzeit) - 1)));
        annuität = Math.round(annuität * 100.0) / 100.0;
        return annuität;
    }

    public void onClick(View view) {
        if (view == ZSAnnuitätButton) {
            if (!eTDarlehenssumme.getText().toString().isEmpty() && !eTZinssatz.getText().toString().isEmpty() && !eTLaufzeit.getText().toString().isEmpty()) {
                Date datum = Calendar.getInstance().getTime();
                annuität = Double.toString(berechneAnnuität(leseDarlehenssumme(),leseZinssatz(),leseLaufzeit()));

                Intent intent = new Intent(this, Ergebnis.class);
                intent.putExtra("annuität", annuität);
                String darlehenssumme = Double.toString(leseDarlehenssumme());
                intent.putExtra("darlehenssumme", darlehenssumme);
                String zinssatz = Double.toString(leseZinssatz()*100);
                intent.putExtra("zinssatz", zinssatz);
                String laufzeit = Double.toString(leseLaufzeit());
                intent.putExtra("laufzeit", laufzeit);

                startActivity(intent);

                Intent intent2 = new Intent(this, Verlauf.class);
                intent2.putExtra("Datum",datum);
                intent2.putExtra("Kommentar",eTKommentar.getText());

                saveAnnuitaetOnClick();

            } else {
                if (eTDarlehenssumme.getText().toString().isEmpty()) {
                    eTDarlehenssumme.setHintTextColor(Color.RED);
                }
                if (eTZinssatz.getText().toString().isEmpty()) {
                    eTZinssatz.setHintTextColor(Color.RED);
                }
                if (eTLaufzeit.getText().toString().isEmpty()) {
                    eTLaufzeit.setHintTextColor(Color.RED);
                }
                Toast laufzeitToast =
                        Toast.makeText(this, "Eingaben unvollständig!", Toast.LENGTH_LONG);
                laufzeitToast.show();
            }

        } else if(view == iVhelpicon){
            Intent intent = new Intent(this, Hilfe.class);
            startActivity(intent);
        }
        else if(view == ZVerlaufButton){
            Intent intent = new Intent(this, Verlauf.class);
            startActivity(intent);
        }
    }

    public void saveAnnuitaetOnClick() {
        if(!annuität.equals("")){
            new SpeichernTask()
                    .execute(new Annuitaet(annuität));
        }
    }

    class SpeichernTask extends AsyncTask<Annuitaet, Void, Void>{
        @Override
        protected Void doInBackground(Annuitaet... annuitaets) {
            dao.insert(annuitaets[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){

            super.onPostExecute(aVoid);
        }
    }
}