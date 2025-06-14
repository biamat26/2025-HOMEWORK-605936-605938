package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;


class TestComandoVai {

	private IOConsole console;
	private ComandoVai comando;
	private Partita partita;
	private Stanza stanzaVecchia;
	
	@BeforeEach
	void setUp() throws Exception {
		this.comando = new ComandoVai();
		this.console = new IOConsole(new Scanner(System.in));
		comando.setIOConsole(console);
		this.partita = new Partita();
		this.stanzaVecchia = partita.getStanzaCorrente();
	}
	
	@Test
	void testVaiInDirezioneBloccata() {
		int cfu = this.partita.getGiocatore().getCfu();
		comando.setParametro("nord");
		comando.esegui(partita);
		assertSame(this.stanzaVecchia, this.partita.getStanzaCorrente());
		assertEquals(cfu, partita.getGiocatore().getCfu());
	}
	
	@Test
	void testVaiInDirezioneConsentita() {
		int cfu = this.partita.getGiocatore().getCfu();
		comando.setParametro("sud");
		comando.esegui(partita);
		assertNotSame(this.stanzaVecchia, this.partita.getStanzaCorrente());
		assertEquals(cfu-1, partita.getGiocatore().getCfu());
	}

	@Test
	void testVaiInDirezioneNonConsentita() {
		this.partita.setStanzaCorrente(partita.getStanzaCorrente().getStanzaAdiacente(Direzione.SUD));
		this.stanzaVecchia = this.partita.getStanzaCorrente();
		int cfu = this.partita.getGiocatore().getCfu();
		comando.setParametro("sud");
		comando.esegui(partita);
		assertSame(this.stanzaVecchia, this.partita.getStanzaCorrente());
		assertEquals(cfu, partita.getGiocatore().getCfu());
	}
}