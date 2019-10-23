package com.example.annuittenrechner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Hilfe extends AppCompatActivity implements View.OnClickListener {


    Button bStartseite;
    Button BerechnungsverlaufButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hilfe_display);
        bStartseite = findViewById(R.id.bStartseite);
        BerechnungsverlaufButton2 = findViewById(R.id.BerechnungsverlaufButton2);

        bStartseite.setOnClickListener(this);
        BerechnungsverlaufButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == bStartseite){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        if (view == BerechnungsverlaufButton2){
            Intent intent = new Intent(this,Verlauf.class);
            startActivity(intent);
        }
    }
}
