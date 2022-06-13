package com.example.correctionefm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class ListEntreprises extends AppCompatActivity {

    ListView lst;
    MyDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_entreprises);

        db = new MyDataBase(this);
        lst = findViewById(R.id.lst);
        ArrayList<Entreprise> entrs = MyDataBase.getAllEntreprise(db.getReadableDatabase());

        ArrayList<HashMap<String,Object>> list_ent = new ArrayList<>();

        for(Entreprise e : entrs){
            HashMap<String, Object> item = new HashMap<>();
            item.put("rs",e.getRs());
            item.put("ca",e.getCapital());
            list_ent.add(item);
        }

        String[] from = {"rs","ca"};
        int[] to = {R.id.itemrs, R.id.itemcapital};

        SimpleAdapter ad = new SimpleAdapter(this, list_ent, R.layout.item_entreprise,from,to);
        lst.setAdapter(ad);



    }
}