package com.example.abbor.android_lab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class DodajWpis extends AppCompatActivity {

    private int modify_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_wpis);
        ArrayAdapter gatunki = new ArrayAdapter(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                new String[] {"Pies", "Kot", "Rybki"});
        Spinner gatunek = (Spinner) findViewById(R.id.spinner_gatunek);
        gatunek.setAdapter(gatunki);
    }

    public void wyslij(View view) {
        EditText kolor = (EditText) findViewById(R.id.editText_kolor);
        EditText wielkosc = (EditText)findViewById (R.id.editText_wielkosc);
        EditText opis = (EditText) findViewById (R.id.editText_opis);
        Spinner gatunek = (Spinner) findViewById(R.id.spinner_gatunek);
        Animal zwierze = new Animal(
                gatunek.getSelectedItem().toString(),
                kolor.getText().toString(),
                Float.valueOf(wielkosc.getText().toString()),
                opis.getText().toString()
        );
        zwierze.setId(this.modify_id);
        Intent intencja = new Intent();
        intencja.putExtra("nowy", zwierze);
        setResult(RESULT_OK, intencja);
        finish();
    }
}
