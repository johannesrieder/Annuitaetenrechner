package com.example.annuittenrechner;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    Button AnnuitätButton;
    EditText eTDarlehenssumme;
    EditText eTZinssatz;
    EditText eTLaufzeit;
    TextView tVErgebnis;
    TextView tVBetrag;
    TextView tVZinssatz;
    TextView tVLaufzeit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnnuitätButton = findViewById(R.id.AnnuitätButton);
        AnnuitätButton.setOnClickListener(this);
        eTDarlehenssumme = findViewById(R.id.eTDarlehenssumme);
        eTZinssatz = findViewById(R.id.eTZinssatz);
        eTLaufzeit = findViewById(R.id.eTLaufzeit);
    }

    public double leseDarlehenssumme(){
        double ds = Double.parseDouble(eTDarlehenssumme.getText().toString());
        return ds;
    }

    public double leseZinssatz(){
        double zs = Integer.parseInt(eTZinssatz.getText().toString());
        zs = zs/100;
        return zs;
    }

    public int leseLaufzeit(){
        int lz = Integer.parseInt(eTLaufzeit.getText().toString());
        return lz;
    }

    public double berechneAnnuität(double darlehenssumme, double zinssatz, int laufzeit) {
        double annuität = darlehenssumme * (Math.pow(1+zinssatz, laufzeit) * (zinssatz / (Math.pow(1+zinssatz, laufzeit) - 1)));
        annuität = Math.round(annuität*100.0)/100.0;
        return annuität;
    }

    public void onClick(View view){
        double darlehenssumme = leseDarlehenssumme();
        double zinssatz = leseZinssatz();
        int laufzeit = leseLaufzeit();
        if (String.valueOf(darlehenssumme) != "" && String.valueOf(zinssatz) != "" && String.valueOf(laufzeit) != "") {
            double annuität = berechneAnnuität(darlehenssumme, zinssatz, laufzeit);
            setContentView(R.layout.result_display);
            tVBetrag = findViewById(R.id.tVBetrag);
            tVZinssatz = findViewById(R.id.tVZinssatz);
            tVErgebnis = findViewById(R.id.tVErgebnis);
            tVLaufzeit = findViewById(R.id.tVLaufzeit);
            tVErgebnis.setText(Double.toString(annuität));
            tVBetrag.setText(Double.toString(darlehenssumme));
            tVZinssatz.setText(Double.toString(zinssatz * 100));
            tVLaufzeit.setText(Integer.toString(laufzeit));
        } else {
            System.out.println("Die Eingabe ist nicht vollständig!");
            if (String.valueOf(darlehenssumme) == ""){
                eTDarlehenssumme.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            if (String.valueOf(zinssatz) == ""){
                eTZinssatz.setBackgroundColor(Color.parseColor("#FF0000"));
            }
            if (String.valueOf(laufzeit) == ""){
                eTLaufzeit.setBackgroundColor(Color.parseColor("#FF0000"));
            }
        }
    }

}
