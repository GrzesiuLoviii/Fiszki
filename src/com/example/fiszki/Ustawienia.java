package com.example.fiszki;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.zip.Inflater;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Ustawienia extends Activity {


	private List<Dzial> dzialy = new ArrayList<Dzial>();

	ZarzadcaBaza zb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ustawienia);
	
		zb = new ZarzadcaBaza(this);
		Cursor kursor = zb.getDzialy();
		while(kursor.moveToNext())
		{
			int id = kursor.getInt(0);
			String nazwa = kursor.getString(1);
			int poczatek = kursor.getInt(2);
			int koniec = kursor.getInt(3);
			int wlaczony = kursor.getInt(4);
			dzialy.add(new Dzial(id,nazwa,poczatek,koniec,wlaczony));
		}
		
		
		
		widokListy();
		wybranieElementuZListy();
	}
    private void widokListy() {
         ArrayAdapter<Dzial> adapter = new MyListAdapter(this, dzialy);
        ListView lista = (ListView)findViewById(R.id.listView1);
        lista.setAdapter(adapter);
    }
    private class MyListAdapter extends ArrayAdapter{
    	private LayoutInflater inflater;
        public MyListAdapter(Activity activity, List<Dzial> dzialy){
        	super(activity, R.layout.activity_wiersz, dzialy);
            inflater = activity.getWindow().getLayoutInflater();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View elementWidoku = convertView;

            if(elementWidoku == null)
                elementWidoku = getLayoutInflater().inflate(R.layout.activity_wiersz, parent, false);

            Dzial obecnaButelka = dzialy.get(position);
            //Ustawienie miejsca produkcji butelki w elemencie listy
            TextView widokdzialu = (TextView)elementWidoku.findViewById(R.id.textView1);
            
            if(obecnaButelka.getWlaczony()==true)
            	widokdzialu.setText(obecnaButelka.getNazwa()+"     ON");
            else
            	widokdzialu.setText(obecnaButelka.getNazwa()+"     OFF");
			return elementWidoku;

        }
}
    private void wybranieElementuZListy() {
        ListView lista =(ListView)findViewById(R.id.listView1);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
              Dzial kliknietyDzial = dzialy.get(position);
              String nazwa = kliknietyDzial.getNazwa();
              Toast.makeText(Ustawienia.this, "Wybrano "+nazwa, Toast.LENGTH_SHORT).show();
              int poczatek = kliknietyDzial.getPoczatek();
              int koniec = kliknietyDzial.getKoniec();
              boolean wlaczony = kliknietyDzial.getWlaczony();
              if(wlaczony==true)
              {
            	 
              	zb.wylaczDzial(kliknietyDzial.getId());
              	zb.wylaczSlowa(poczatek,koniec);
              }
              else
              {
            	zb.wlaczDzial(kliknietyDzial.getId());
            	zb.wlaczSlowa(poczatek,koniec);
              }
              
              //Wyświetlenie nowego Activity dla wciśniętej butelki
              wyswietlButelke();
			}
		});
        

    }
    private void wyswietlButelke() {
    	Intent intent = getIntent();
        
        startActivity(intent);
        finish();
    }

 }