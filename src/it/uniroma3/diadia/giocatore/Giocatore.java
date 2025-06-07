package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.Configurazione;

public class Giocatore {

	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		setCfu(Configurazione.getCfuIniziali());
		this.setBorsa(new Borsa());
	}

	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	public void mossa() {
		this.cfu--;	//se fai una mossa ti levo un cfu
	}
	
	public Borsa getBorsa() {
		return borsa;
	}

	public void setBorsa(Borsa borsa) {
		this.borsa = borsa;
	}

	public String toString() {
		return this.borsa.toString() + "\nCFU: " + this.getCfu() ;
	}

	
}
