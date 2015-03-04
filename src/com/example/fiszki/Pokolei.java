package com.example.fiszki;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Pokolei extends Activity {
	private int numer_slowka=1;
	int id;
	ZarzadcaBaza zb;
	public void Zmiana_Slowka(int numer)
	{
		TextView wyswietlajace = (TextView)findViewById(R.id.textView1);
		TextView oczekujace = (TextView)findViewById(R.id.textView2);
		Cursor kursor = zb.wyjmijSlowo();
		for(int i=0;i<numer;i++)
		{
			kursor.moveToNext();
		}
		id=kursor.getInt(0);
		wyswietlajace.setText(kursor.getString(2));
		oczekujace.setText(kursor.getString(1));
		TextView liczba = (TextView)findViewById(R.id.textView3);
		int w = zb.Ilosc_umiem();
		int z = zb.Ilosc_elementow();
		String t = w+"/"+z;
		liczba.setText(t);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fiszki);
		zb = new ZarzadcaBaza(this);
		numer_slowka=1;
		Zmiana_Slowka(numer_slowka);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.fiszki, menu);
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
	
	public void WypiszSlowko(View view) throws IOException
	{
		EditText editext = (EditText)findViewById(R.id.editText1);
		TextView textView = (TextView)findViewById(R.id.textView1);
		TextView oczekujace = (TextView)findViewById(R.id.textView2);
		String wpisane = editext.getText().toString();
		String oczekiwane = oczekujace.getText().toString();
		if(oczekiwane.equals(wpisane))
		{
			zb.Umiem(id);
			Zmiana_Slowka(numer_slowka);
		}
		else
		{
			Button sprawdz = (Button)findViewById(R.id.button1);
			Button pamietam = (Button)findViewById(R.id.button2);
			Button niepamietam = (Button)findViewById(R.id.button3);
			
			oczekujace.setVisibility(0);
			sprawdz.setVisibility(4);
			pamietam.setVisibility(0);
			niepamietam.setVisibility(0);
		}
	}
	public void Pamietam(View view) throws IOException
	{	
		Button sprawdz = (Button)findViewById(R.id.button1);
		Button pamietam = (Button)findViewById(R.id.button2);
		Button niepamietam = (Button)findViewById(R.id.button3);
		TextView oczekujace = (TextView)findViewById(R.id.textView2);

		oczekujace.setVisibility(4);
		sprawdz.setVisibility(0);
		pamietam.setVisibility(4);
		niepamietam.setVisibility(4);
		zb.Umiem(id);
		Zmiana_Slowka(numer_slowka);
	}
	public void Nie_pamietam(View view) throws IOException
	{
		Button sprawdz = (Button)findViewById(R.id.button1);
		Button pamietam = (Button)findViewById(R.id.button2);
		Button niepamietam = (Button)findViewById(R.id.button3);
		TextView oczekujace = (TextView)findViewById(R.id.textView2);

		oczekujace.setVisibility(4);
		sprawdz.setVisibility(0);
		pamietam.setVisibility(4);
		niepamietam.setVisibility(4);
		
		zb.Nieumiem(id);
		numer_slowka++;
		Zmiana_Slowka(numer_slowka);
	}


}
