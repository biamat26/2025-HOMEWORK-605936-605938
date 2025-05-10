package it.uniroma3.diadia.giocatore;

public class Giocatore {
	final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;
	
	public Giocatore() {
		setCfu(CFU_INIZIALI);
		this.setBorsa(new Borsa());
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
