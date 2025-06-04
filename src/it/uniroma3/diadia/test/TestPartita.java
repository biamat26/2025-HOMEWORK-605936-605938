package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

import org.junit.jupiter.api.BeforeEach;


class TestPartita {

		private Partita partita;
		
		@BeforeEach
		void setUp() throws Exception {
			this.partita = new Partita();
		}
		
		@Test
		void testInizializzazione() {
			assertNotNull(partita.getStanzaCorrente());
			assertEquals(partita.getLabirinto().getStanzaIniziale(), partita.getStanzaCorrente());
			assertNotNull(partita.getStanzaVincente());
			assertEquals(20, partita.getGiocatore().getCfu());
		}
		
		@Test
		void testNuovaPartitaNonFinita() {
			assertFalse(this.partita.isFinita());
		}
		
		@Test
		void testPartitaNonVintaEPoiVinta() {
			assertFalse(this.partita.isFinita());
			this.partita.setFinita();
			assertTrue(this.partita.isFinita());
		}
		
		@Test
		void testPartitaVinta() {
			partita.setStanzaCorrente(partita.getStanzaVincente());
			assertTrue(this.partita.vinta());
		}
		
		@Test
		void testPartitaPersa() {
			partita.getGiocatore().setCfu(0);
			assertTrue(this.partita.persa());
		}
		
		@Test
		void testCambioStanza() {
			Stanza stanzaCorrente = partita.getStanzaCorrente();
			Stanza stanzaAdiacente = new Stanza("Stanza adiacente");
			stanzaCorrente.impostaStanzaAdiacente("nord", stanzaAdiacente);
			partita.setStanzaCorrente(stanzaAdiacente);
			assertEquals(stanzaAdiacente, partita.getStanzaCorrente());
		}

}
