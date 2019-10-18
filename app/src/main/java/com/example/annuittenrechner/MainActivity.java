package com.example.annuittenrechner;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    private Button AnnuitätButton;
    private Button BerechnungsverlaufButton;
    private EditText DarlehenssummeEingabe;
    private EditText ZinssatzEingabe;
    private EditText LaufzeitEingabe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnnuitätButton = findViewById(R.id.AnnuitätButton);
        BerechnungsverlaufButton = findViewById(R.id.BerechnungsverlaufButton);
        DarlehenssummeEingabe = findViewById(R.id.DarlehenssummeEingabe);
        ZinssatzEingabe = findViewById(R.id.ZinssatzEingabe);
        LaufzeitEingabe = findViewById(R.id.LaufzeitEingabe);

        AnnuitätButton.setOnClickListener(this);
        BerechnungsverlaufButton.setOnClickListener(this);

        int DarlehensSumme = Integer.valueOf(DarlehenssummeEingabe.getText().toString());
        int Zinssatz = Integer.valueOf(ZinssatzEingabe.getText().toString());
        int Laufzeit = Integer.valueOf(LaufzeitEingabe.getText().toString());

    }
}
