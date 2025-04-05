package it.uniroma3.diadia.test;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestStanza {
	private Stanza stanza;
	private Stanza stanzaAdiacente;
	private Attrezzo attrezzo;
	
	@BeforeEach
	void setUp() throws Exception {
		this.stanza = new Stanza("N11");
		this.stanzaAdiacente = new Stanza("N12");
		this.stanza.impostaStanzaAdiacente("nord", stanzaAdiacente);
		
		this.attrezzo = new Attrezzo("spada", 4);
		this.stanza.addAttrezzo(attrezzo);
	}
	
	@Test
	void testStanzaAdiacente() {
		assertEquals(this.stanza.getStanzaAdiacente("nord"), this.stanzaAdiacente);
	}
	
	@Test
	void testStanzaAdiacenteNonPresente() {
		assertNotEquals(this.stanza.getStanzaAdiacente("sud"), this.stanzaAdiacente);
	}
	
	@Test 
	void testStanzaAdiacenteNull() {
		assertNotEquals(null, this.stanzaAdiacente);
	}
	
	@Test
	void testAggiungoAttrezzo() {
		assertTrue(this.stanza.addAttrezzo(new Attrezzo("osso", 1)));
	}
	
	@Test
	void testAggiungoAttrezzoNullo() {
		assertFalse(this.stanza.addAttrezzo(null));
	}
	
	@Test
	void testAggiungoTroppiElementi() {
		for(int i = this.stanza.getNumeroAttrezzi(); i < this.stanza.getNumeroMassimoAttrezzi(); i++) {
			this.stanza.addAttrezzo(new Attrezzo("spada", 4));
		}
		assertFalse(this.stanza.addAttrezzo(new Attrezzo("spada", 4)));
	}
	
	@Test
	void testRemoveAttrezzo() {
		assertTrue(this.stanza.removeAttrezzo(attrezzo));
	}
	
	@Test
	void testRemoveAttrezzoNullo() {
		assertFalse(this.stanza.removeAttrezzo(null));
	}
	
	@Test
	void testRemoveAttrezzoNonPresenta() {
		assertFalse(this.stanza.removeAttrezzo(new Attrezzo("lanterna", 6)));
	}
	
}
