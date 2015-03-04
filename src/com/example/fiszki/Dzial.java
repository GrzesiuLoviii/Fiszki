package com.example.fiszki;

public class Dzial {
	
	private String nazwa;
	private int poczatek,
				koniec,
				id;
	private int wlaczony;
	public Dzial(int id, String nazwa, int poczatek, int koniec, int wlaczony)
	{
		this.id = id;
		this.nazwa = nazwa;
		this.poczatek = poczatek;
		this.koniec = koniec;
		this.wlaczony = wlaczony;
	}
	public String getNazwa()
	{
		return nazwa;
	}
	public boolean getWlaczony()
	{
		if(wlaczony==1)
		return true;
		else
			return false;
	}
	public int getPoczatek() {
		// TODO Auto-generated method stub
		return poczatek;
	}
	public int getKoniec() {
		// TODO Auto-generated method stub
		return koniec;
	}
	public int getId() {
		// TODO Auto-generated method stub
		return id;
	}
}
