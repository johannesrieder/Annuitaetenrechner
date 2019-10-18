package com.example.annuittenrechner;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


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

        int DarlehensSumme;
        int Zinssatz;
        int Laufzeit;

        int Annuität = 0;

    }
    public void onClick(View view){

            if(view == AnnuitätButton){
                int DarlehensSumme = Integer.valueOf(DarlehenssummeEingabe.getText().toString());
                int Zinssatz = Integer.valueOf(ZinssatzEingabe.getText().toString());
                int Laufzeit = Integer.valueOf(LaufzeitEingabe.getText().toString());
                int Annuität = DarlehensSumme * (Zinssatz*(1+Zinssatz)^Laufzeit)/((1+Laufzeit)^Laufzeit-1);
            }
            if(view == BerechnungsverlaufButton){
            setContentView(R.layout.result_display);
            }
    }

}
