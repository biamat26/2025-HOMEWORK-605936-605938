package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class TestComandoPrendi {

	private Attrezzo attrezzo;
	private Partita partita;
	private ComandoPrendi comando;
	private IO ioConsole;
	
	@BeforeEach
	void setUp() throws Exception {
		
		this.attrezzo = new Attrezzo("martello", 5);
		this.partita = new Partita();
		this.comando = new ComandoPrendi();
		this.ioConsole = new IOConsole();
		comando.setIOConsole(ioConsole);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		
	}
	
	@Test
	void testPrendiAttrezzoNullo() {
		comando.setParametro(null);
		comando.esegui(partita);
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	void testBorsaTroppoPesante() {
		comando.setParametro(attrezzo.getNome());
		partita.getGiocatore().getBorsa().setPesoMax(this.attrezzo.getPeso()-1);
		
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
	}
	
	
	@Test
	void testAttrezzoPreso() {
		comando.setParametro(attrezzo.getNome());
		comando.esegui(partita);
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome()));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo(attrezzo.getNome()));
	}
	
	@Test
	void testAttrezzoNonTrovato() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		comando.setParametro("spada");
		comando.esegui(partita);
		

		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
}