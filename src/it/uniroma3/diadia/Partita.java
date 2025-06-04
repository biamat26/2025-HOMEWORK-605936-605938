package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	

	private Stanza stanzaCorrente;
	private Stanza stanzaVincente;
	private boolean finita;
	private Giocatore giocatore;
	private Labirinto labirinto;
	
	

	public Partita(){
		
		this.setGiocatore(new Giocatore());
		this.setLabirinto(new Labirinto());
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.stanzaVincente = this.labirinto.getStanzaVincente();
		this.finita = false;
	}

	
	public Giocatore getGiocatore() {
		return giocatore;
	}

	public void setGiocatore(Giocatore giocatore) {
		this.giocatore = giocatore;
	}
  
	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public void setFinita() {
		this.finita = true;
	}
	
	public boolean getFinita() {
		return this.finita;
	}
	
	public Labirinto getLabirinto() {
		return labirinto;
	}

	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	
	public boolean vinta() {
		return this.getStanzaCorrente() == this.getStanzaVincente();
	}

	public boolean persa() {
		return (giocatore.getCfu() == 0);
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || persa();
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	
	@Override
	public String toString() {
		return stanzaCorrente.toString() + this.giocatore.toString();
	}

	

	
}
