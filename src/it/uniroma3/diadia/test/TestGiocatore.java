package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

class TestGiocatore {

	private Giocatore giocatore;

	@BeforeEach
	void setUp() throws Exception {
		this.giocatore = new Giocatore();
	}

	@Test
	void testCFUNonNegativi() {
	    assertTrue(this.giocatore.getCfu() >= 0);
	}

	@Test
	void testInizializzazioneCFU() {
		assertEquals(this.giocatore.getCFU_INIZIALI(), this.giocatore.getCfu());
	}

	@Test
	void testDecrementareCFU() {
		int cfuCorrenti = giocatore.getCfu();
		giocatore.mossa();
		assertEquals(cfuCorrenti - 1, giocatore.getCfu());
	}


}
