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
	public Stanza getStanzaAdiacente(String direzione) {
		 if(this.direzioneBloccata.equals(direzione)) {
			 for(Attrezzo a : this.getAttrezzi()) {
				 if(a != null && attrezzoSblocco.equals(a.getNome())) {
					 return super.getStanzaAdiacente(direzione);
				 }
			 }
			 return this;
		 }else {
			 return super.getStanzaAdiacente(direzione); 
		 }
		 
	}
	
	@Override
	public String getDescrizione() {
		return super.getDescrizione() + "Direzione bloccata: " + this.direzioneBloccata + "\n"; 
	}

}
