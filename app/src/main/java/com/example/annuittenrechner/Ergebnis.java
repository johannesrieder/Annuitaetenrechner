package com.example.annuittenrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ergebnis extends AppCompatActivity implements View.OnClickListener {

    TextView Ergebnis;
    TextView Darlehenssumme;
    TextView Zinssatz;
    TextView Laufzeit;
    Button NeueBerechnung;
    Button Berechnungsverlauf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ergebnis_display);
        Ergebnis = findViewById(R.id.tVErgebnis);
        Darlehenssumme = findViewById(R.id.tVDarlehenssumme);
        Zinssatz = findViewById(R.id.tVZinssatz);
        Laufzeit = findViewById(R.id.tVLaufzeit);
        NeueBerechnung = findViewById(R.id.bNeueBerechnung);
        Berechnungsverlauf = findViewById(R.id.bBerechnungsverlauf);
        NeueBerechnung.setOnClickListener(this);
        Berechnungsverlauf.setOnClickListener(this);

        Intent intent = getIntent();
        String annuität = intent.getStringExtra("annuität");
        Ergebnis.setText(annuität);
        String darlehenssumme = intent.getStringExtra("darlehenssumme");
        Darlehenssumme.setText(darlehenssumme);
        String zinssatz = intent.getStringExtra("zinssatz");
        Zinssatz.setText(zinssatz);
        String laufzeit = intent.getStringExtra("laufzeit");
        Laufzeit.setText(laufzeit);
    }

    @Override
    public void onClick(View view) {
        if(view == NeueBerechnung){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(view == Berechnungsverlauf){
            Intent intent = new Intent(this, Verlauf.class);
            startActivity(intent);
        }

    }


}
