package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestStanzaBuia {
	
	private Stanza stanzaBuia;
	private Attrezzo attrezzoLuminoso;
	private Stanza stanzaLuminosa;
	
	@BeforeEach
	void setUp() {
		this.attrezzoLuminoso = new Attrezzo("lanterna", 1);
		this.stanzaBuia = new StanzaBuia("N10", attrezzoLuminoso.getNome());
		this.stanzaLuminosa = new Stanza("N12");
	}
	
	@Test
	void testAttrezzoLuminosoNonPresente() {
		assertNotEquals(stanzaBuia.getDescrizione(), stanzaLuminosa.getDescrizione());
	}
	
	@Test
	void testAttrezzoLuminosoPresente() {
		this.stanzaBuia.addAttrezzo(attrezzoLuminoso);
		assertEquals(stanzaBuia.getDescrizione(), stanzaLuminosa.getDescrizione());
	}
}
