package com.example.fiszki;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

import android.database.Cursor;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	ZarzadcaBaza zb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zb = new ZarzadcaBaza(this);
		if(zb.Ilosc_elementow()==0)
			Tworzenie_plikow();
    }
    
	public void Tworzenie_plikow()
	{
		InputStream slowka = getResources().openRawResource(R.raw.slowka);
		Scanner in2=new Scanner(slowka);
			while(in2.hasNextLine())
			{
				zb.dodajSlowo(in2.nextLine(), in2.nextLine());
			}
		zb.dodajDzial("Zakupy i us³ugi", 0, 245);
		zb.dodajDzial("Podró¿owanie i turystyka", 245, 570);
		zb.dodajDzial("Œrodki transportu",245,266);
		zb.dodajDzial("Podró¿owanie",266,384);
		zb.dodajDzial("Podró¿ kolej¹",384,402);
		zb.dodajDzial("Podró¿ statkiem", 402, 410);
		zb.dodajDzial("Podró¿ lotnicza", 410, 433);
		zb.dodajDzial("Podró¿ samochodem, czêœci samochodu",433,476);
		zb.dodajDzial("Zakwaterowanie",476,501);
		zb.dodajDzial("Wakacje i zwiedzanie", 501, 532);
		zb.dodajDzial("Podró¿e zagraniczne",532,545);
		zb.dodajDzial("Wypadki",545,570);
	}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void GoToFiszki(View view)
    {
    	Intent intent = new Intent(this,Fiszki.class);
    	startActivity(intent);
    }
    public void GoToPokolei(View view)
    {
    	Intent intent = new Intent(this,Pokolei.class);
    	startActivity(intent);
    }
    public void GoToUstawenia(View view)
    {
    	Intent intent = new Intent(this,Ustawienia.class);
    	startActivity(intent);
    }
    public void usunBaze(View view){
		if(zb.Usun_baze(this))
		{
	        zb = new ZarzadcaBaza(this);
			if(zb.Ilosc_elementow()==0)
				Tworzenie_plikow(); 
		}
    }
}
