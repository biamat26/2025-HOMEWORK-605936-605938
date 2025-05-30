package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	
	private Stanza uscita;
	private Stanza entrata;
	private Map<String, Stanza> stanze;
	private Stanza ultimaStanzaAggiunta;
	
	
	public LabirintoBuilder() {
		stanze = new HashMap<>();
	}
	
	public LabirintoBuilder addStanzaIniziale(String nomeEntrata) {
		Stanza entrata = new Stanza(nomeEntrata);
		stanze.put(nomeEntrata, entrata);
		this.entrata = entrata;
		this.ultimaStanzaAggiunta = entrata;
		return this;
	}	
	
	public LabirintoBuilder addStanzaVincente(String nomeUscita) {
		Stanza uscita= new Stanza(nomeUscita);
		stanze.put(nomeUscita, uscita);
		this.uscita = uscita;
		this.ultimaStanzaAggiunta = uscita;
		return this;
		
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String nomeStanzaPartenza, String nomeStanzaDestinazione, String direzione) {
		Stanza stanzaPartenza = new Stanza(nomeStanzaPartenza);
		Stanza stanzaDestinazione = new Stanza(nomeStanzaDestinazione);
		stanzaPartenza.impostaStanzaAdiacente(direzione, stanzaDestinazione);
		return this;
	}
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza stanza = new Stanza(nomeStanza);
		stanze.put(nomeStanza, stanza);
		this.ultimaStanzaAggiunta = stanza;
		return this;
	}
	
	
	public Labirinto getLabrinto() {
		return new Labirinto(this.entrata, this.uscita, this.stanze);
	}
}