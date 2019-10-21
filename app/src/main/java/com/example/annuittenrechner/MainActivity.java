package com.example.annuittenrechner;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Button AnnuitätButton;
    Button BerechnungsverlaufButton;
    EditText eTDarlehenssumme;
    EditText eTZinssatz;
    EditText eTLaufzeit;
    TextView tVErgebnis;
    TextView tVBetrag;
    TextView tVZinssatz;
    TextView tVLaufzeit;
    ImageView iVhelpicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnnuitätButton = findViewById(R.id.AnnuitätButton);
        AnnuitätButton.setOnClickListener(this);
        BerechnungsverlaufButton = findViewById(R.id.BerechnungsverlaufButton);
        BerechnungsverlaufButton.setOnClickListener(this);
        eTDarlehenssumme = findViewById(R.id.eTDarlehenssumme);
        eTZinssatz = findViewById(R.id.eTZinssatz);
        eTLaufzeit = findViewById(R.id.eTLaufzeit);
        iVhelpicon = findViewById(R.id.iVhelpicon);
    }


    public double leseDarlehenssumme() {
        double ds = Double.parseDouble(eTDarlehenssumme.getText().toString());
        return ds;
    }

    public double leseZinssatz() {
        double zs = Double.parseDouble(eTZinssatz.getText().toString());
        zs = zs / 100;
        return zs;
    }

    public int leseLaufzeit() {
        int lz = Integer.parseInt(eTLaufzeit.getText().toString());
        return lz;
    }

    public double berechneAnnuität(double darlehenssumme, double zinssatz, int laufzeit) {
        double annuität = darlehenssumme * (Math.pow(1 + zinssatz, laufzeit) * (zinssatz / (Math.pow(1 + zinssatz, laufzeit) - 1)));
        annuität = Math.round(annuität * 100.0) / 100.0;
        return annuität;
    }

    public void onClick(View view) {

        if (view == AnnuitätButton) {
            if (eTDarlehenssumme.getText().toString().isEmpty() == false && eTZinssatz.getText().toString().isEmpty() == false && eTLaufzeit.getText().toString().isEmpty() == false) {
                double darlehenssumme = leseDarlehenssumme();
                double zinssatz = leseZinssatz();
                int laufzeit = leseLaufzeit();
                double annuität = berechneAnnuität(darlehenssumme, zinssatz, laufzeit);
                setContentView(R.layout.result_display);
                tVBetrag = findViewById(R.id.tVDarlehenssumme);
                tVZinssatz = findViewById(R.id.tVZinssatz);
                tVErgebnis = findViewById(R.id.tVErgebnis);
                tVLaufzeit = findViewById(R.id.tVLaufzeit);
                tVErgebnis.setText(Double.toString(annuität));
                tVBetrag.setText(Double.toString(darlehenssumme));
                tVZinssatz.setText(Double.toString(zinssatz * 100));
                tVLaufzeit.setText(Integer.toString(laufzeit));
                System.out.println("Die Eingabe ist vollständig!");
            } else {
                System.out.println("Die Eingabe ist nicht vollständig!");
                if (eTDarlehenssumme.getText().toString().isEmpty() == true) {
                    eTDarlehenssumme.setHintTextColor(Color.RED);
                }
                if (eTZinssatz.getText().toString().isEmpty() == true) {
                    eTZinssatz.setHintTextColor(Color.RED);
                }
                if (eTLaufzeit.getText().toString().isEmpty() == true) {
                    eTLaufzeit.setHintTextColor(Color.RED);

                }
                Toast laufzeitToast =
                        Toast.makeText(this, "Eingaben unvollständig!", Toast.LENGTH_LONG);
                laufzeitToast.show();
                return;
            }
        } if(view == BerechnungsverlaufButton){
            setContentView(R.layout.verlauf_display);
        }
        if(view == iVhelpicon){
            setContentView(R.layout.hilfe_display);
        }
    }
}
