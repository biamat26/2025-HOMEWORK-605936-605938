package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

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
		if(Direzione.valueOf(direzioneBloccata.toUpperCase()) == null) throw new IllegalArgumentException("Direzione inesistente");
		Stanza stanzaBloccata = new StanzaBloccata(nomeStanzaBloccata, Direzione.valueOf(direzioneBloccata.toUpperCase()), attrezzoSblocco);
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

	public LabirintoBuilder addAdiacenza(String nomeStanzaPartenza, String nomeStanzaDestinazione, String nomeDirezione) {
		Stanza stanzaPartenza = this.stanze.get(nomeStanzaPartenza);
		Stanza stanzaDestinazione = this.stanze.get(nomeStanzaDestinazione);
		Direzione direzione = Direzione.valueOf(nomeDirezione.toUpperCase());
		if(direzione == null || stanzaPartenza == null || stanzaDestinazione == null ) throw new IllegalArgumentException("Direzione inesistente");
		stanzaPartenza.impostaStanzaAdiacente(direzione, stanzaDestinazione);
		return this;
	}
	
	public LabirintoBuilder addPersonaggio(AbstractPersonaggio personaggio, Stanza stanza) {
		if(stanza != null) 	stanza.setPersonaggio(personaggio);
		else throw new IllegalArgumentException("La stanza non è presente nel labirinto");
		
		return this;
	}
	
	public LabirintoBuilder addCane(String nome, String attrezzo, int peso, String presentazione, String stanza) {
		return addCane(nome, attrezzo, peso, presentazione, Cane.CIBO_PREFERITO_DEFAULT, stanza);
	}
	
	public LabirintoBuilder addCane(String nome, String attrezzo, int peso, String presentazione, String ciboPreferito, String stanza) {
		AbstractPersonaggio cane = new Cane(nome, new Attrezzo(nome, peso), presentazione, ciboPreferito);
		return addPersonaggio(cane, getListaStanze().get(stanza));
	}
	
	public LabirintoBuilder addMago(String nome, String attrezzo, int peso, String presentazione, String stanza) {
		AbstractPersonaggio mago = new Mago(nome, new Attrezzo(attrezzo, peso), presentazione);
		return addPersonaggio(mago, getListaStanze().get(stanza));
	}
	
	public LabirintoBuilder addStrega(String nome, String presentazione, String stanza) {
		AbstractPersonaggio strega = new Strega(nome, presentazione);
		return addPersonaggio(strega, getListaStanze().get(stanza));
	}
	
	
	public Labirinto getLabirinto() {
		return new Labirinto(this.entrata, this.uscita, this.stanze);
	}
}