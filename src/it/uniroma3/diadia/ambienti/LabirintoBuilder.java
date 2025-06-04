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
	
	public Map<String, Stanza> getListaStanze(){
		return this.stanze;
	}
	
	
	private LabirintoBuilder add(Stanza stanza) {
		
		stanze.put(stanza.getNome(), stanza);
		this.ultimaStanzaAggiunta = stanza;
		
		return this;
	}
	
	public LabirintoBuilder addStanza(String nomeStanza) {
		Stanza stanza = new Stanza(nomeStanza);
		return add(stanza);
	}
	
	public LabirintoBuilder addStanzaIniziale(String nomeEntrata) {
		
		Stanza entrata = new Stanza(nomeEntrata);
		this.entrata = entrata;
		
		return add(entrata);
	}	
	
	public LabirintoBuilder addStanzaVincente(String nomeUscita) {
		Stanza uscita= new Stanza(nomeUscita);
		this.uscita = uscita;
		return add(uscita);	
	}
	
	public LabirintoBuilder addStanzaBuia(String nomeStanzaBuia, String nomeAttrezzoLuminoso) {
		Stanza stanzaBuia = new StanzaBuia(nomeStanzaBuia, nomeAttrezzoLuminoso);
		
		return add(stanzaBuia);
	}
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanzaBloccata, String direzioneBloccata, String attrezzoSblocco) {
		Stanza stanzaBloccata = new StanzaBloccata(nomeStanzaBloccata, direzioneBloccata, attrezzoSblocco);
		return add(stanzaBloccata);
	}
	
	public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int soglia) {
		Stanza stanzaMagica = new StanzaMagica(nomeStanzaMagica, soglia);
		return add(stanzaMagica);
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
		this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
		return this;
	}

	public LabirintoBuilder addAdiacenza(String nomeStanzaPartenza, String nomeStanzaDestinazione, String direzione) {
		
		Stanza stanzaPartenza = this.stanze.get(nomeStanzaPartenza);
		
		Stanza stanzaDestinazione = this.stanze.get(nomeStanzaDestinazione);
		
		if(stanzaPartenza == null || stanzaDestinazione == null) {
			return this;
		}
		
		stanzaPartenza.impostaStanzaAdiacente(direzione, stanzaDestinazione);
		
		return this;
	}
	
	
	
	
	public Labirinto getLabirinto() {
		return new Labirinto(this.entrata, this.uscita, this.stanze);
	}
}