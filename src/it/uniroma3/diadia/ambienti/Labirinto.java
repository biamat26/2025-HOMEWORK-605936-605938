package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

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
	
	private Labirinto(Stanza stanzaIniziale, Stanza stanzaVincente, Map<String, Stanza> stanze) {
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
	
	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
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
	
	
	
	
	
	public static class LabirintoBuilder {
		
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
			if(stanza != null) {
				stanza.setPersonaggio(personaggio);
			}
			else throw new IllegalArgumentException("La stanza non è presente nel labirinto");
			
			return this;
		}
		
		public LabirintoBuilder addCane(String nome, String attrezzo, int peso, String presentazione, String stanza) {
			return addCane(nome, attrezzo, peso, presentazione, Cane.CIBO_PREFERITO_DEFAULT, stanza.trim());
		}
		
		public LabirintoBuilder addCane(String nome, String attrezzo, int peso, String presentazione, String ciboPreferito, String stanza) {
			AbstractPersonaggio cane = new Cane(nome, new Attrezzo(attrezzo, peso), presentazione, ciboPreferito);
			return addPersonaggio(cane, getListaStanze().get(stanza.trim()));
		}
		
		public LabirintoBuilder addMago(String nome, String attrezzo, int peso, String presentazione, String stanza) {
			AbstractPersonaggio mago = new Mago(nome, new Attrezzo(attrezzo, peso), presentazione);
			return addPersonaggio(mago, getListaStanze().get(stanza.trim()));
		}
		
		public LabirintoBuilder addStrega(String nome, String presentazione, String stanza) {
			AbstractPersonaggio strega = new Strega(nome, presentazione);
			return addPersonaggio(strega, getListaStanze().get(stanza.trim()));
		}
		
		public LabirintoBuilder setStanzaIniziale(String nomeStanza) {
		    this.entrata = stanze.get(nomeStanza);
		    if (!stanze.containsKey(nomeStanza)){
		        this.addStanza(nomeStanza);
		    }
		    return this;
		}

		public LabirintoBuilder setStanzaVincente(String nomeStanza) {
		    this.uscita = stanze.get(nomeStanza);
		    if (!stanze.containsKey(nomeStanza)){
		        this.addStanza(nomeStanza);
		    }
		    return this;
		}

		
		public Labirinto getLabirinto() {
			return new Labirinto(this.entrata, this.uscita, this.stanze);
		}
	}
}