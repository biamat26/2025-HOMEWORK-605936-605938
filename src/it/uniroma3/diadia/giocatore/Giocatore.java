package it.uniroma3.diadia.giocatore;

public class Giocatore {
	final private int CFU_INIZIALI = 20;
	private int cfu;
	
	public Giocatore() {
		setCfu(CFU_INIZIALI);
	}

	public int getCfu() {
		return cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;
	}
	
	public int getCFU_INIZIALI() {
		return this.CFU_INIZIALI;
	}
	
	public void mossa() {
		this.cfu--;	//se fai una mossa ti levo un cfu
	}

	public String toString() {
		return "\nCFU: " + this.getCfu();
	}
}
