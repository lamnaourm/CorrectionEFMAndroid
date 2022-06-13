package com.example.correctionefm;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEntreprise extends AppCompatActivity {

    EditText e1, e2, e3;
    Button b1, b2;
    MyDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entreprise);

        db = new MyDataBase(this);
        e1 = findViewById(R.id.ers);
        e2 = findViewById(R.id.ead);
        e3 = findViewById(R.id.eca);
        b1 = findViewById(R.id.addb1);
        b2 = findViewById(R.id.addb2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Entreprise ee = new Entreprise();
                ee.setRs(e1.getText().toString());
                ee.setAdresse(e2.getText().toString());
                ee.setCapital(Double.parseDouble(e3.getText().toString()));

                if(MyDataBase.AddEntreprise(db.getWritableDatabase(),ee)==-1)
                    Toast.makeText(AddEntreprise.this, "Echec Insertion", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddEntreprise.this, "Insertion reussie", Toast.LENGTH_SHORT).show();

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}