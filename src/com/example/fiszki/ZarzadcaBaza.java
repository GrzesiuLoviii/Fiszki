package com.example.fiszki;

import java.util.Random;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ZarzadcaBaza extends SQLiteOpenHelper{
	public ZarzadcaBaza(Context context)
	{
		super(context, "slowka.db", null, 2);
		// TODO Auto-generated constructor stub
	}
	public boolean Usun_baze(Context context)
	{
		if(context.deleteDatabase(getDatabaseName()))
			return true;
		else
			return false;
	}

	@Override
	public void onCreate(SQLiteDatabase db){
		String sql = "create table slowka( id integer primary key autoincrement, angielskie text,polskie text, umiem integer, wlaczony integer);";
		db.execSQL(sql);
		String sql2 = "create table dzialy(id integer primary key autoincrement, nazwa text, poczatek integer, koniec integer, wlaczony integer);";
		db.execSQL(sql2);
		// TODO Auto-generated method stub
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public void dodajSlowo(String angielskie, String polskie)
	{
		SQLiteDatabase db = getWritableDatabase();
		ContentValues wartosc = new ContentValues();
		wartosc.put("angielskie", angielskie);
		wartosc.put("polskie", polskie);
		wartosc.put("umiem", 0);
		wartosc.put("wlaczony", 1);
		db.insertOrThrow("slowka", null, wartosc);
	}
	public void dodajDzial(String nazwa, int poczatek, int koniec)
	{
		SQLiteDatabase db = getWritableDatabase();
		ContentValues wartosc = new ContentValues();
		wartosc.put("nazwa", nazwa);
		wartosc.put("poczatek", poczatek);
		wartosc.put("koniec",koniec);
		wartosc.put("wlaczony", 1);
		db.insertOrThrow("dzialy", null, wartosc);
	}
	public void wylaczDzial(int id)
	{
		String sql="UPDATE dzialy SET wlaczony=0 WHERE id="+id;
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);
	}
	public void wlaczDzial(int id)
	{
		String sql="UPDATE dzialy SET wlaczony=1 WHERE id="+id;
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);
	}
	public void wylaczSlowa(int poczatek, int koniec)
	{
		for(int i=poczatek;i<koniec;i++)
		{
		String sql="UPDATE slowka SET wlaczony=0 WHERE id="+i;
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);
		}
	}
	public void wlaczSlowa(int poczatek, int koniec)
	{
		for(int i=poczatek;i<koniec;i++)
		{
		String sql="UPDATE slowka SET wlaczony=1 WHERE id="+i;
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);
		}
	}
	public Cursor wyjmijSlowo()
	{
		String[] kolumny = {"id","angielskie","polskie"};
		SQLiteDatabase db = getReadableDatabase();
		String[] argument={"0","1"};
		Cursor kursor = db.query("slowka", kolumny, "umiem=? AND wlaczony=?", argument, null, null, null);
		return kursor;
	}
	public Cursor getDzialy()
	{
		String[] kolumny = {"id","nazwa","poczatek","koniec","wlaczony"};
		SQLiteDatabase db = getReadableDatabase();
		Cursor kursor = db.query("dzialy", kolumny,null,null ,null, null, null);
		return kursor;
	}
	public Cursor wylosujSlowo()
	{
		Cursor kursor;
		SQLiteDatabase db = getReadableDatabase();
		String[] kolumny = {"id","angielskie","polskie"};
		Random losowanie = new Random();
		losowanie.setSeed(System.currentTimeMillis());
		if(losowanie.nextInt(10)>1)
		{
			String[] argument={"0","1"};
			kursor = db.query("slowka", kolumny, "umiem=? AND wlaczony=?", argument, null, null, null);
			return kursor;
		}
		else
		{	
			String[] argument={"1","1"};
			kursor = db.query("slowka", kolumny, "umiem=? AND wlaczony=?", argument, null, null, null);
			return kursor;
		}
		
		
	}
	public void Umiem(int numer)
	{
		String sql="UPDATE slowka SET umiem=1 WHERE id="+numer;
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);
	}
	public void Nieumiem(int numer)
	{
		String sql="UPDATE slowka SET umiem=0 WHERE id="+numer;
		SQLiteDatabase db = getWritableDatabase();
		db.execSQL(sql);
	}
	public int Ilosc_elementow()
	{
		SQLiteDatabase db = getReadableDatabase();
		String[] kolumny = {"id"};
		String[] argument={"1"};
		Cursor kursor = db.query("slowka", kolumny, "wlaczony=?", argument, null, null, null);
		return kursor.getCount();
	}
	public int Ilosc_umiem()
	{
		SQLiteDatabase db = getReadableDatabase();
		String[] kolumny = {"id"};
		String[] arg = {"1","1"};
		Cursor kursor = db.query("slowka", kolumny, "umiem=? AND wlaczony=?", arg, null, null, null, null);
		return kursor.getCount();
	}
	
}
