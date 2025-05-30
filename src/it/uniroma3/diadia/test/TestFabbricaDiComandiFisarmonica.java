package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.*;

class TestFabbricaDiComandiFisarmonica {

	private FabbricaDiComandi fdi;
	private Comando cmd;
	
	@BeforeEach
	void setUp() throws Exception {
		fdi = new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	void testCreaComandoVai() {
		cmd = fdi.costruisciComando("vai nord");
		Comando comandoVai = new ComandoVai();
		comandoVai.setParametro("nord");
		assertEquals(cmd.getNome(), comandoVai.getNome());
		assertEquals(cmd.getParametro(), comandoVai.getParametro());
	}
	
	@Test
	void testCreaComandoGuarda() {
		cmd = fdi.costruisciComando("guarda");
		Comando comandoGuarda = new ComandoGuarda();
		assertEquals(cmd.getNome(), comandoGuarda.getNome());
		assertNull(cmd.getParametro());
	}
	
	@Test
	void testCreaComandoFine() {
		cmd = fdi.costruisciComando("fine");
		Comando comandoFine = new ComandoFine();
		assertEquals(cmd.getNome(), comandoFine.getNome());
		assertNull(cmd.getParametro());
	}
	
	@Test
	void testCreaComandoAiuto() {
		cmd = fdi.costruisciComando("aiuto");
		Comando comandoAiuto = new ComandoAiuto();
		assertEquals(cmd.getNome(), comandoAiuto.getNome());
		assertNull(cmd.getParametro());
	}
	
	@Test
	void testCreaComandoPrendi() {
		cmd = fdi.costruisciComando("prendi cacciavite");
		Comando comandoPrendi = new ComandoPrendi();
		comandoPrendi.setParametro("cacciavite");
		assertEquals(cmd.getNome(), comandoPrendi.getNome());
		assertEquals(cmd.getParametro(), comandoPrendi.getParametro());
	}
	
	@Test
	void testCreaComandoPosa() {
		cmd = fdi.costruisciComando("posa cacciavite");
		Comando comandoPosa = new ComandoPosa();
		comandoPosa.setParametro("cacciavite");
		assertEquals(cmd.getNome(), comandoPosa.getNome());
		assertEquals(cmd.getParametro(), comandoPosa.getParametro());
	}
}