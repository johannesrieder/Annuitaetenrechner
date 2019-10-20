package com.example.annuittenrechner;

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

    public int leseZinssatz(){
        int zs = Integer.parseInt(eTZinssatz.getText().toString());
        return zs;
    }

    public int leseLaufzeit(){
        int lz = Integer.parseInt(eTLaufzeit.getText().toString());
        return lz;
    }

    public double berechneAnnuität(double darlehenssumme, int zinssatz, int laufzeit) {
        double annuität = darlehenssumme * (Math.pow(1+zinssatz, laufzeit) * (zinssatz / (Math.pow(1+zinssatz, laufzeit) - 1)));
        return annuität;
    }

    public void onClick(View view){
        double darlehenssumme = leseDarlehenssumme();
        int zinssatz = leseZinssatz();
        int laufzeit = leseLaufzeit();
        double annuität = berechneAnnuität(darlehenssumme, zinssatz, laufzeit);
        setContentView(R.layout.result_display);
        TextView tVErgebnis = findViewById(R.id.tVErgebnis);
        tVErgebnis.setText(Double.toString(annuität));
    }

}
