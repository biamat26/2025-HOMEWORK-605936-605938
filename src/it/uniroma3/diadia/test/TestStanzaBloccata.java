package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestStanzaBloccata {
	
	private Direzione direzioneBloccata;
	private String attrezzoSblocco;
	private Stanza stanzaBloccata;
	private Stanza stanzaAdiacente;
	
	
	@BeforeEach
	void setUp() {
		
		this.direzioneBloccata = Direzione.NORD;
		this.attrezzoSblocco = "chiave";
		
		this.stanzaBloccata = new StanzaBloccata("atrio", direzioneBloccata, attrezzoSblocco);
		this.stanzaAdiacente = new Stanza("biblioteca");
		
		this.stanzaBloccata.impostaStanzaAdiacente(direzioneBloccata, stanzaAdiacente);
		
	}
	
	@Test
	void testAttrezzoSbloccoNonPresente() {
		assertSame(stanzaBloccata, stanzaBloccata.getStanzaAdiacente(direzioneBloccata));
		assertNotSame(stanzaAdiacente, stanzaBloccata.getStanzaAdiacente(direzioneBloccata));
	}
	
	@Test
	void testAttrezzoSbloccoPresente() {
		
		Attrezzo attrezzoSbloccoDaAggiungere = new Attrezzo(attrezzoSblocco, 1);
		this.stanzaBloccata.addAttrezzo(attrezzoSbloccoDaAggiungere);
		
		assertSame(stanzaAdiacente, stanzaBloccata.getStanzaAdiacente(direzioneBloccata));
		assertNotSame(stanzaBloccata, stanzaBloccata.getStanzaAdiacente(direzioneBloccata));
	}
	
	
}
