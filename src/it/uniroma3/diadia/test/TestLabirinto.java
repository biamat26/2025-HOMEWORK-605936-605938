package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

class TestLabirinto {
	
	Labirinto labirinto;
	
	@BeforeEach
	public void setUp() {
		labirinto = new Labirinto();
	}
	
	@Test
	public void testEntrata() {
		assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testUscita() {
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}
	
	@Test
	public void testStanzeAdiacenti() {
		assertEquals("Aula N11", labirinto.getStanzaIniziale().getStanzaAdiacente(Direzione.EST).getNome());
		assertEquals("Atrio", labirinto.getStanzaVincente().getStanzaAdiacente(Direzione.SUD).getNome());
	}

}
