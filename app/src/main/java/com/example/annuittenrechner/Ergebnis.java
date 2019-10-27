package com.example.annuittenrechner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Ergebnis  extends AppCompatActivity implements View.OnClickListener {

    TextView Ergebnis;
    TextView Darlehenssumme;
    TextView Zinssatz;
    TextView Laufzeit;
    Button NeueBerechnung;
    Button Berechnungsverlauf;
    ImageView iV_activityErgebnis_helpIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ergebnis_display);

        NeueBerechnung = findViewById(R.id.b_ergebnis_neueBerechnung);
        NeueBerechnung.setOnClickListener(this);
        Berechnungsverlauf = findViewById(R.id.b_ergebnis_zeigeVerlauf);
        Berechnungsverlauf.setOnClickListener((view) -> {
            Intent intent = new Intent(this, Verlauf.class);
            startActivity(intent);
        });

        Ergebnis = findViewById(R.id.tV_ergebnis_ergebnis);
        Darlehenssumme = findViewById(R.id.tV_ergebnis_darlehenssumme);
        Zinssatz = findViewById(R.id.tV_ergebnis_zinssatz);
        Laufzeit = findViewById(R.id.tV_ergebnis_laufzeit);
        iV_activityErgebnis_helpIcon = findViewById(R.id.iV_activityErgebnis_helpIcon);

        Intent intent = getIntent();
        String annuität = intent.getStringExtra("annuität");
        Ergebnis.setText(annuität+"€");
        String darlehenssumme = intent.getStringExtra("darlehenssumme");
        Darlehenssumme.setText("Darlehenssumme: "+darlehenssumme+"€");
        String zinssatz = intent.getStringExtra("zinssatz");
        Zinssatz.setText("Zinssatz: "+zinssatz+"%");
        String laufzeit = intent.getStringExtra("laufzeit");
        Laufzeit.setText("Laufzeit: "+laufzeit+" Jahre");
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
        if(view == iV_activityErgebnis_helpIcon){
            Intent intent = new Intent(this,Hilfe.class);
            startActivity(intent);
        }

    }


}
