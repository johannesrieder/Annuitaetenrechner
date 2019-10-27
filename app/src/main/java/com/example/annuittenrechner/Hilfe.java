package com.example.annuittenrechner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Hilfe extends AppCompatActivity implements View.OnClickListener {

    Button HStartseiteButton;
    Button SVerlaufButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hilfe_display);
        HStartseiteButton = findViewById(R.id.b_verlauf_startseite);
        SVerlaufButton2 = findViewById(R.id.SVerlaufButton2);

        HStartseiteButton.setOnClickListener(this);
        SVerlaufButton2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view == HStartseiteButton){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if (view == SVerlaufButton2){
            Intent intent = new Intent(this, Verlauf.class);
            startActivity(intent);
        }
    }
}
