package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{

	private String direzioneBloccata;
	private String attrezzoSblocco;
	 
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSblocco) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSblocco = attrezzoSblocco;
	}
	
	@Override
	public boolean isBloccata() {
		return true;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (this.direzioneBloccata.equals(direzione)) {
			if (!this.getAttrezzi().contains(this.getAttrezzo(this.attrezzoSblocco))) {
				return this;
			}
		}
		return super.getStanzaAdiacente(direzione);
		 
	}
	
	@Override
	public String getDescrizione() {
		return this.toString(); 
	}
	
	
	@Override
	public int hashCode() {
		return super.hashCode() + this.attrezzoSblocco.hashCode() + this.direzioneBloccata.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		if(o.getClass() != this.getClass()) return false;
		StanzaBloccata that = (StanzaBloccata) o;
		return this.direzioneBloccata.equals(that.direzioneBloccata) &&
				this.attrezzoSblocco.equals(that.attrezzoSblocco);
	}
	
	
	@Override
	public String toString() {
		return super.toString() + "Direzione bloccata: " + this.direzioneBloccata + "\n";
	}

	
}
