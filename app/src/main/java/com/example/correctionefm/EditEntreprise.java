package com.example.correctionefm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditEntreprise extends AppCompatActivity {

    Spinner sp;
    EditText e1, e2, e3;
    Button b1, b2;
    MyDataBase db;
    ArrayList<Entreprise> entrps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entreprise);

        db = new MyDataBase(this);
        sp = findViewById(R.id.sp);
        e1 = findViewById(R.id.editrs);
        e2 = findViewById(R.id.editad);
        e3 = findViewById(R.id.editca);
        b1 = findViewById(R.id.editb1);
        b2 = findViewById(R.id.editb2);

        entrps = MyDataBase.getAllEntreprise(db.getReadableDatabase());
        ArrayList<String> ids = new ArrayList<>();

        for(Entreprise ee : entrps)
            ids.add(String.valueOf(ee.getId()));

        ArrayAdapter ad = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,ids);
        sp.setAdapter(ad);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Entreprise e = entrps.get(i);

                e1.setText(e.getRs());
                e2.setText(e.getAdresse());
                e3.setText(String.valueOf(e.getCapital()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(EditEntreprise.this);
                alert.setTitle("Modifier une entreprise");
                alert.setMessage("Voulez modifier cette entreprise ?");

                alert.setPositiveButton("Modifier", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Entreprise e = new Entreprise();
                        e.setId(Integer.valueOf(sp.getSelectedItem().toString()));
                        e.setRs(e1.getText().toString());
                        e.setAdresse(e2.getText().toString());
                        e.setCapital(Double.parseDouble(e3.getText().toString()));

                        if(MyDataBase.UpdateEntreprise(db.getWritableDatabase(),e)==-1)
                            Toast.makeText(EditEntreprise.this, "Echec Modification", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(EditEntreprise.this, "Modification reussie", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EditEntreprise.this, "Operation annulee", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(EditEntreprise.this);
                alert.setTitle("Supprimer une entreprise");
                alert.setMessage("Voulez supprimer cette entreprise ?");

                alert.setPositiveButton("Supprimer", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int id = Integer.valueOf(sp.getSelectedItem().toString());
                        if(MyDataBase.DeleteEntreprise(db.getWritableDatabase(),id)==-1)
                            Toast.makeText(EditEntreprise.this, "Echec Suppression", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(EditEntreprise.this, "Suppression reussie", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(EditEntreprise.this, "Operation annulee", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();
            }
        });
    }
}