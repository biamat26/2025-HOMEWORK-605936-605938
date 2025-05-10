package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class TestStanzaMagica {

	private int sogliaMagica;
	private Attrezzo attrezzoNormale;
	private Attrezzo attrezzoMagico;
	private StanzaMagica stanzaMagica;
	
	@BeforeEach
	void setUp() throws Exception {
		StringBuilder nomeAttrezzo = new StringBuilder("chiave");
		this.sogliaMagica = 3;
		this.attrezzoNormale = new Attrezzo(nomeAttrezzo.toString(), 1);
		this.attrezzoMagico = new Attrezzo(nomeAttrezzo.reverse().toString(), attrezzoNormale.getPeso()*2);
		this.stanzaMagica = new StanzaMagica("Lab");
	}
	
	@Test
	void testNullo() {
		assertNotEquals(attrezzoNormale, attrezzoMagico);
	}

	@Test
	void testAggiungiDopoSoglia() {
		this.stanzaMagica.setContatoreAttrezziPosati(this.sogliaMagica);
		this.stanzaMagica.addAttrezzo(attrezzoNormale);
		assertEquals(this.stanzaMagica.getContatoreAttrezziPosati(), 4);
		assertEquals(this.stanzaMagica.getAttrezzo(attrezzoMagico.getNome()), this.attrezzoMagico);
	}
}