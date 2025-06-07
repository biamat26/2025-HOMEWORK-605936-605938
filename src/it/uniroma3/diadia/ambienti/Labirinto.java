package it.uniroma3.diadia.ambienti;

import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	
	/**
	 * Classe che si occupa della topologia del labirinto
	 * Attributi:
	 * stanzaIniziale => indica la stanza dove il giocatore inizia la partita
	 * stanzaVincente=> indica la stanza dove il giocatore deve arrivare per vincere
	 * 
	 * La creazione del labirinto è affidata al metodo creaLabirinto
	 * 
	 */
	
	private Stanza stanzaIniziale;
	private Stanza stanzaVincente;
	private Map<String, Stanza> stanze;
	
	public Labirinto(Stanza stanzaIniziale, Stanza stanzaVincente, Map<String, Stanza> stanze) {
		this.stanzaIniziale = stanzaIniziale;
		this.stanzaVincente = stanzaVincente;
		this.stanze = stanze;
	}
	
	public Labirinto() {
		creaLabirinto();
	} 
	
	public Stanza getStanzaIniziale() {
		return stanzaIniziale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public Stanza getStanzaVincente() {
		return stanzaVincente;
	}

	public void setStanzaVincente(Stanza stanzaVincente) {
		this.stanzaVincente = stanzaVincente;
	}

	public Map<String, Stanza> getStanze() {
		return stanze;
	}

	public void setStanze(Map<String, Stanza> stanze) {
		this.stanze = stanze;
	}

	private void creaLabirinto(){
		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna", 3);
		Attrezzo osso = new Attrezzo("osso", 1);
		Attrezzo lancia = new Attrezzo("lancia", 4);
		Attrezzo scudo = new Attrezzo("scudo", 7);
		Attrezzo chiave = new Attrezzo("chiave", 1);
		
    	
		/* crea stanze del labirinto */
		Stanza atrio = new StanzaBloccata("Atrio", Direzione.NORD, "chiave");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new StanzaBuia("Aula N10", "lanterna");
		Stanza laboratorio = new StanzaMagica("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente(Direzione.NORD, biblioteca);
		atrio.impostaStanzaAdiacente(Direzione.EST, aulaN11);
		atrio.impostaStanzaAdiacente(Direzione.SUD, aulaN10);
		atrio.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);
		aulaN11.impostaStanzaAdiacente(Direzione.EST, laboratorio);
		aulaN10.impostaStanzaAdiacente(Direzione.EST, aulaN11);
		aulaN10.impostaStanzaAdiacente(Direzione.OVEST, laboratorio);


        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);
		atrio.addAttrezzo(lancia);
		atrio.addAttrezzo(scudo);
		aulaN11.addAttrezzo(chiave);

		// il gioco comincia nell'atrio
        this.setStanzaIniziale(atrio);  
		this.setStanzaVincente(biblioteca);
	}	
}