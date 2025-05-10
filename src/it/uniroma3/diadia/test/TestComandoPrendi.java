package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class TestComandoPrendi {

	private Attrezzo attrezzo;
	private Stanza stanza;
	private Partita partita;
	private ComandoPrendi comando;
	private String azione;
	private String parametro;
	private IO ioConsole;
	
	@BeforeEach
	void setUp() throws Exception {
		this.attrezzo = new Attrezzo("martello", 5);
		this.stanza = new Stanza("Atrio");
		this.partita = new Partita();
		this.comando = new ComandoPrendi();
		this.azione = new String("prendi");
		this.ioConsole = new IOConsole();
		comando.setIOConsole(ioConsole);
	}
	
	@Test
	void testAttrezzoPreso() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		comando.setParametro("martello");
		comando.esegui(partita);
		
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
	
	@Test
	void testAttrezzoNonPreso() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		comando.setParametro("spada");
		comando.esegui(partita);
		

		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
	
	@Test
	void testPrendiUnAttrezzo() {
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		partita.getStanzaCorrente().addAttrezzo(new Attrezzo("martello", 5));
		comando.setParametro("martello");
		comando.esegui(partita);
		
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("martello"));
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("martello"));
	}
}