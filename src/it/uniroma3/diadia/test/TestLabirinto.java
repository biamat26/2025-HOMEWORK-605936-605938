package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class TestLabirinto {
	
	Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		labirinto = new Labirinto();
	}
	
	@Test
	public void testEntrata() {
		assertEquals("Atrio", labirinto.getEntrata().getNome());
	}
	
	@Test
	public void testUscita() {
		assertEquals("Biblioteca", labirinto.getUscita().getNome());
	}
	
	@Test
	public void testStanzeAdiacenti() {
		assertEquals("Biblioteca", labirinto.getEntrata().getStanzaAdiacente("nord").getNome());
		assertEquals("Atrio", labirinto.getUscita().getStanzaAdiacente("sud").getNome());
	}

}
