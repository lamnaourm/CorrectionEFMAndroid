package com.example.correctionefm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDataBase extends SQLiteOpenHelper {

    static String DB_NAME = "entreprise.db";
    static String TB_NAME = "entreprise.db";
    static String COL1 = "ID";
    static String COL2 = "raisonSociale";
    static String COL3 = "adresse";
    static String COL4 = "capital";

    public MyDataBase(Context c) {
        super(c, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TB_NAME + "(" + COL1 + " interger primary key autoincrement," + COL2 + " text," + COL3 + " text," + COL4 + " double)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE " + TB_NAME;
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }

    public static long AddEntreprise(SQLiteDatabase db, Entreprise e){
        ContentValues ct = new ContentValues();
        ct.put(COL2,e.getRs());
        ct.put(COL3,e.getAdresse());
        ct.put(COL4,e.getCapital());
        return db.insert(TB_NAME,null,ct);
    }

    public static long UpdateEntreprise(SQLiteDatabase db, Entreprise e){
        ContentValues ct = new ContentValues();
        ct.put(COL2,e.getRs());
        ct.put(COL3,e.getAdresse());
        ct.put(COL4,e.getCapital());
        return db.update(TB_NAME,ct,"id="+e.getId(),null);
    }

    public static long DeleteEntreprise(SQLiteDatabase db, int id){
        return db.delete(TB_NAME,"id="+id,null);
    }

    public static ArrayList<Entreprise> getAllEntreprise(SQLiteDatabase db){
        ArrayList<Entreprise> entrs = new ArrayList<>();

        Cursor cur =  db.rawQuery("SELECT * FROM " + TB_NAME,null);

        while(cur.moveToNext()){
            Entreprise e = new Entreprise();
            e.setId(cur.getInt(0));
            e.setRs(cur.getString(1));
            e.setAdresse(cur.getString(2));
            e.setCapital(cur.getDouble(3));
            entrs.add(e);
        }
        return entrs;
    }

    public static Entreprise getOneEntreprise(SQLiteDatabase db, int id){
       Cursor cur =  db.rawQuery("SELECT * FROM " + TB_NAME + " where id="+id,null);

        if(cur.moveToNext()){
            Entreprise e = new Entreprise();
            e.setId(cur.getInt(0));
            e.setRs(cur.getString(1));
            e.setAdresse(cur.getString(2));
            e.setCapital(cur.getDouble(3));
            return e;
        }
        return null;
    }

}
