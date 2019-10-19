package com.example.annuittenrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private Button AnnuitätButton;
    private EditText eTDarlehenssumme;
    private EditText eTZinssatz;
    private EditText eTLaufzeit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnnuitätButton = findViewById(R.id.AnnuitätButton);
        AnnuitätButton.setOnClickListener(this);
    }

    protected double leseDarlehenssumme(){
        double ds = Double.parseDouble(eTDarlehenssumme.getText().toString());
        return ds;
    }

    protected int leseZinssatz(){
        int zs = Integer.parseInt(eTZinssatz.getText().toString());
        return zs;
    }

    protected int leseLaufzeit(){
        int lz = Integer.parseInt(eTLaufzeit.getText().toString());
        return lz;
    }

    protected double berechneAnnuität(double darlehenssumme, int zinssatz, int laufzeit) {
        double annuität = darlehenssumme * (Math.pow(1+zinssatz, laufzeit) * (zinssatz / (Math.pow(1+zinssatz, laufzeit) - 1)));
        return annuität;
    }

    public void onClick(View view){
        double darlehenssumme = leseDarlehenssumme();
        int zinssatz = leseZinssatz();
        int laufzeit = leseLaufzeit();
        double annuität = berechneAnnuität(darlehenssumme, zinssatz, laufzeit);
        setContentView(R.layout.result_display);
        TextView tVErgebnis = (TextView) findViewById(R.id.tVErgebnis);
        tVErgebnis.setText(Double.toString(annuität));
    }

}
