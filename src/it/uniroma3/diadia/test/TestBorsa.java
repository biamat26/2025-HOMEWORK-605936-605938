package it.uniroma3.diadia.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class TestBorsa {
	
	private Borsa borsa;
	private int numeroAttrezziCorrente;
	
	
	@BeforeEach
	void setUp() throws Exception {
		this.borsa = new Borsa();
		this.borsa.addAttrezzo(new Attrezzo("lanterna", 5));
		this.numeroAttrezziCorrente = this.borsa.getNumeroAttrezzi();
	}
	
	@Test
	void testAggiungoAttrezzoNonNullo() {
		this.borsa.addAttrezzo(new Attrezzo("osso", 3));
		assertEquals(this.numeroAttrezziCorrente + 1, this.borsa.getNumeroAttrezzi());
	}
	
	@Test
	void testAggiungoAttrezzoNullo() {
		assertFalse(this.borsa.addAttrezzo(null));
		assertEquals(numeroAttrezziCorrente, this.borsa.getNumeroAttrezzi());
	}
	
	@Test
	void testPesoBorsaMax() {
		assertEquals(borsa.getPesoMax(), Configurazione.getPesoMaxBorsaDefault(), "il peso massimo dev'essere 10");
	}
	
	@Test
	void testAggiungoAttrezzoTroppoPesante() {
		assertFalse(this.borsa.addAttrezzo(new Attrezzo("spada", Configurazione.getPesoMaxBorsaDefault() + 1)));
	}
	
	@Test
	void testAggiungoTroppiAttrezzi() {
		for(int i = this.borsa.getNumeroAttrezzi(); i <= Configurazione.getNumeroMaxAttrezziBorsaDefault(); i++) {
			this.borsa.addAttrezzo(new Attrezzo("spada", 0));
		}
		assertFalse(this.borsa.addAttrezzo(new Attrezzo("spada", 0)));
		
	}
	
	@Test
	void testRemoveAttrezzoNullo(){
		assertNull(this.borsa.removeAttrezzo(null));
	}
	
	@Test
	void testRemoveAttrezzoNonPrensente() {
		 assertNull(this.borsa.removeAttrezzo("spada"));
	}
	
	@Test
	void testRemoveAttrezzo() {
		assertNotNull(this.borsa.removeAttrezzo("lanterna"));
	}
}
