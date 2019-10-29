package com.example.annuittenrechner;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AnnuitaetsparameterDao dao;
    private String annuität;
    private String darlehenssumme;
    private String zinssatz;
    private String laufzeit;
    private String kommentar;
    private String strDatum;

    EditText eT_activityMain_darlehenssumme;
    EditText eT_activityMain_zinssatz;
    EditText eT_activityMain_laufzeit;
    EditText eT_activityMain_kommentar;

    protected Button b_activityMain_berechneAnnuitaet;
    protected Button b_activityMain_zeigeVerlauf;

    ImageView iV_activityMain_helpIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = AnnuitaetsparameterRoomDatabase.getDatabase(this).annuitaetsparameterDao();

        eT_activityMain_darlehenssumme = findViewById(R.id.eT_activityMain_darlehenssumme);
        eT_activityMain_zinssatz = findViewById(R.id.eT_activityMain_zinssatz);
        eT_activityMain_laufzeit = findViewById(R.id.eT_activityMain_laufzeit);
        eT_activityMain_kommentar = findViewById(R.id.eT_activityMain_kommentar);

        b_activityMain_berechneAnnuitaet = findViewById(R.id.b_activityMain_berechneAnnuitaet);
        b_activityMain_berechneAnnuitaet.setOnClickListener(this);

        b_activityMain_zeigeVerlauf = findViewById(R.id.b_activityMain_zeigeVerlauf);
        b_activityMain_zeigeVerlauf.setOnClickListener(this);

        iV_activityMain_helpIcon = findViewById(R.id.iV_activityMain_helpIcon);
    }

    public double leseDarlehenssumme() { return Double.parseDouble(eT_activityMain_darlehenssumme.getText().toString()); }

    public double leseZinssatz() { return Double.parseDouble(eT_activityMain_zinssatz.getText().toString())/100; }

    public int leseLaufzeit() { return Integer.parseInt(eT_activityMain_laufzeit.getText().toString()); }

    public double berechneAnnuität(double darlehenssumme, double zinssatz, int laufzeit) {
    double annuität = darlehenssumme * (Math.pow(1 + zinssatz, laufzeit) * (zinssatz / (Math.pow(1 + zinssatz, laufzeit) - 1)));
    annuität = Math.round(annuität * 100.0) / 100.0;
    return annuität;
    }

    public void onClick(View view) {
        if (view == b_activityMain_berechneAnnuitaet) {
            darlehenssumme = eT_activityMain_darlehenssumme.getText().toString();
            zinssatz = eT_activityMain_zinssatz.getText().toString();
            laufzeit = eT_activityMain_laufzeit.getText().toString();
            kommentar = eT_activityMain_kommentar.getText().toString();

            if (!darlehenssumme.equals("") && !zinssatz.equals("") && !laufzeit.equals("") && !darlehenssumme.equals(".") && !zinssatz.equals(".") && ((Double.parseDouble(zinssatz) > 0)) && (Double.parseDouble(laufzeit) > 0)) {
                annuität = Double.toString(berechneAnnuität(leseDarlehenssumme(),leseZinssatz(),leseLaufzeit()));

                Intent intent = new Intent(this, Ergebnis.class);
                intent.putExtra("annuität", annuität);
                intent.putExtra("darlehenssumme", darlehenssumme);
                intent.putExtra("zinssatz", zinssatz);
                intent.putExtra("laufzeit", laufzeit);

                zinssatz = Double.toString(leseZinssatz()*100);

                startActivity(intent);

                Date date = Calendar.getInstance().getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                strDatum = dateFormat.format(date);

                Intent intent2 = new Intent(this, Verlauf.class);
                intent2.putExtra("Datum",strDatum);
                intent2.putExtra("Kommentar", eT_activityMain_kommentar.getText());
                saveAnnuitaetsparameterOnClick(); }

            else if(darlehenssumme.equals("") || zinssatz.equals("") || laufzeit.equals("")) {
                if (eT_activityMain_darlehenssumme.getText().toString().isEmpty()) {
                    eT_activityMain_darlehenssumme.setHintTextColor(Color.RED);
                }
                if (eT_activityMain_zinssatz.getText().toString().isEmpty()) {
                    eT_activityMain_zinssatz.setHintTextColor(Color.RED);
                }
                if (eT_activityMain_laufzeit.getText().toString().isEmpty()) {
                    eT_activityMain_laufzeit.setHintTextColor(Color.RED);
                }
                Toast leerToast =
                        Toast.makeText(this, "Eingaben unvollständig!", Toast.LENGTH_LONG);
                leerToast.show();
            }
            else if(darlehenssumme.equals(".") || zinssatz.equals(".")){
                Toast punktToast =
                        Toast.makeText(this, "Unerlaubte Zeichen!", Toast.LENGTH_LONG);
                punktToast.show();
                if(darlehenssumme.equals(".")){
                    eT_activityMain_darlehenssumme.setText("");
                    eT_activityMain_darlehenssumme.setHintTextColor(Color.RED);
                }
                if(zinssatz.equals(".")){
                    eT_activityMain_zinssatz.setText("");
                    eT_activityMain_zinssatz.setHintTextColor(Color.RED);
                }
            }
            else if((Double.parseDouble(zinssatz) == 0) || (Double.parseDouble(laufzeit) == 0)){
                Toast nullToast =
                        Toast.makeText(this, "Eingabe 0 ist nicht erlaubt!", Toast.LENGTH_LONG);
                nullToast.show();
                if(Double.parseDouble(zinssatz) == 0){
                    eT_activityMain_zinssatz.setText("");
                    eT_activityMain_zinssatz.setHintTextColor(Color.RED);
                }
                if(Double.parseDouble(laufzeit) < 0) {
                    eT_activityMain_laufzeit.setText("");
                    eT_activityMain_laufzeit.setBackgroundColor(Color.RED);
                }

            }
        }
        else if(view == iV_activityMain_helpIcon){
            Intent intent = new Intent(this, Hilfe.class);
            startActivity(intent);
        }
        else if(view == b_activityMain_zeigeVerlauf){
            Intent intent = new Intent(this, Verlauf.class);
            startActivity(intent);
        }
    }

    public void saveAnnuitaetsparameterOnClick(){
        if(!annuität.equals("")){
            new SpeichernTask()
                    .execute(new Annuitaetsparameter(annuität, darlehenssumme, zinssatz, laufzeit, kommentar, strDatum));
        }
    }

    class SpeichernTask extends AsyncTask<Annuitaetsparameter, Void, Void> {
        @Override
        protected Void doInBackground(Annuitaetsparameter... aps) {
            dao.insert(aps[0]);
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid){
            super.onPostExecute(aVoid);
        }
    }
}
