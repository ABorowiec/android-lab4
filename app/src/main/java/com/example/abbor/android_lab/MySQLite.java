package com.example.abbor.android_lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by abbor on 17.06.2017.
 */

public class MySQLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    public MySQLite(Context context) {
        super(context, "animalsDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE =
                "create table animalsDB " +
                        "(_id integer primary key autoincrement," +
                        "gatunek text not null," +
                        "kolor text not null," +
                        "wielkosc real not null," +
                        "opis text not null);";
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS animalsDB");
        onCreate(db);
    }

    public void dodaj(Animal zwierz){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("gatunek", zwierz.getGatunek());
        values.put("kolor", zwierz.getKolor());
        values.put("wielkosc", zwierz.getWielkosc());
        values.put("opis", zwierz.getOpis());
        db.insert("animalsDB", null, values);
        db.close();
    }

    public void usun(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("animalsDB", "_id = ?", new String[] { id });
        db.close();
    }

    public int aktualizuj(Animal zwierz) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("gatunek", zwierz.getGatunek());
        values.put("kolor", zwierz.getKolor());
        values.put("wielkosc", zwierz.getWielkosc());
        values.put("opis", zwierz.getOpis());
        int i = db.update("animalsDB", values, "_id =?",
                new String[]{String.valueOf(zwierz.getId())});
        db.close();
        return i;
    }

    public Animal pobierz(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("animalsDB",
                new String[] { "_id",
                        "gatunek", "kolor", "wielkosc", "opis" }, // b.column names
                " _id = ?", // c. selections
                new String[] {String.valueOf(id) }, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();
        Animal zwierz = new
                Animal(cursor.getString(1), cursor.getString(2),
                cursor.getFloat(3), cursor.getString(4));
        zwierz.setId(Integer.parseInt(cursor.getString(0))
        );
        return zwierz;
    }

    public Cursor lista(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("Select * from animalsDB",null);
    }
}
