package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOSimulator;

class TestIOSimulator {
	
	@Test
	void testPartitaStanzaBloccata() {
		String[] input = {"vai nord", "vai est", "guarda", "prendi chiave", "vai ovest", "posa chiave", "vai nord"};
		IOSimulator io = new IOSimulator(input);
		
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		String[] expected = {"messaggioIniziale",
							 "Non puoi andare nella direzione inserita\n"  + "Borsa vuota\n" + "CFU: 20",
							 "Sei entrato nella stanza: Aula N11\n" + "Borsa vuota\n" + "CFU: 19",
							 "Aula N11\n" + "Uscite:  est ovest\n" + "Attrezzi nella stanza: chiave (1kg)",
							 "L'attrezzo chiave è stato preso correttamente\n" + "Contenuto borsa (1kg/10kg): chiave (1kg) ",
							 "Sei entrato nella stanza: Atrio\n" + "Contenuto borsa (1kg/10kg): chiave (1kg) \n" + "CFU: 18",
							 "L'attrezzo chiave è stato rimosso dalla borsa\n" + "Borsa vuota",
							 "Sei entrato nella stanza: Biblioteca\n" + "Borsa vuota\n" + "CFU: 17", "Hai vinto!!"};
		
		String[] output = io.getOutput();
		int i = 1;
		while(i<output.length && i<expected.length && expected[i] != null && output[i] != null) {
			assertEquals(expected[i], output[i]);
			i++;
		}
	}
	
	@Test
	void testPartitaStanzaBuia() {
		String[] input = {"vai sud", "guarda", "prendi lanterna", "guarda", "fine"};
		IOSimulator io = new IOSimulator(input);
		
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		String[] expected = {"messaggioIniziale",
							 "Sei entrato nella stanza: Aula N10\n" + "Borsa vuota\n" + "CFU: 19",
							 "Aula N10\n" + "Uscite:  nord est ovest\n" + "Attrezzi nella stanza: lanterna (3kg)",
							 "L'attrezzo lanterna è stato preso correttamente\n" + "Contenuto borsa (3kg/10kg): lanterna (3kg) ",
							 "Aula N10\n" + "Uscite:  nord est ovest\n" + "Attrezzi nella stanza: \n" + "Qui c'è buio pesto...",
							 "Partita finita, grazie per aver giocato!"};
		
		String[] output = io.getOutput();
		int i = 1;
		while(i<output.length && i<expected.length && expected[i] != null && output[i] != null) {
			assertEquals(expected[i], output[i]);
			i++;
		}
	}
	
	@Test
	void testPartitaStanzaMagica() {
		String[] input = {"prendi lancia", "vai ovest", "posa lancia", "prendi lancia", 
					"posa lancia", "prendi lancia", "posa lancia","prendi lancia", "posa lancia", 
					"guarda", "prendi aicnal", "fine"};
		IOSimulator io = new IOSimulator(input);
		
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		String[] expected = {"messaggioIniziale",
							 "L'attrezzo lancia è stato preso correttamente\n" + "Contenuto borsa (4kg/10kg): lancia (4kg) ",
							 "Sei entrato nella stanza: Laboratorio Campus\n" + "Contenuto borsa (4kg/10kg): lancia (4kg) \n" + "CFU: 19",
							 "L'attrezzo lancia è stato rimosso dalla borsa\n" + "Borsa vuota",
							 "L'attrezzo lancia è stato preso correttamente\n" + "Contenuto borsa (4kg/10kg): lancia (4kg) ",
							 "L'attrezzo lancia è stato rimosso dalla borsa\n" + "Borsa vuota",
							 "L'attrezzo lancia è stato preso correttamente\n" + "Contenuto borsa (4kg/10kg): lancia (4kg) ",
							 "L'attrezzo lancia è stato rimosso dalla borsa\n" + "Borsa vuota",
							 "L'attrezzo lancia è stato preso correttamente\n" + "Contenuto borsa (4kg/10kg): lancia (4kg) ",
							 "L'attrezzo lancia è stato rimosso dalla borsa\n" + "Borsa vuota",
							 "Laboratorio Campus\n" + "Uscite:  est ovest\n" + "Attrezzi nella stanza: aicnal (8kg)",
							 "L'attrezzo aicnal è stato preso correttamente\n" + "Contenuto borsa (8kg/10kg): aicnal (8kg) ",
							 "Partita finita, grazie per aver giocato!"};
		
		String[] output = io.getOutput();
		int i = 1;
		while(i<output.length && i<expected.length && expected[i] != null && output[i] != null) {
			assertEquals(expected[i], output[i]);
			i++;
		}
	}
	
	@Test
	void testPartitaPersa() {
		String[] input = {"vai sud", "vai nord", "vai sud", "vai nord", "vai sud", "vai nord", "vai sud", "vai nord", "vai sud", "vai nord",
				          "vai sud", "vai nord", "vai sud", "vai nord", "vai sud", "vai nord", "vai sud","vai nord", "vai sud","vai nord"};
		IOSimulator io = new IOSimulator(input);
		
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
		String[] expected = {"messaggioIniziale",
							 "Sei entrato nella stanza: Aula N10\n" + "Borsa vuota\n" + "CFU: 19",
							 "Sei entrato nella stanza: Atrio\n" + "Borsa vuota\n" + "CFU: 18",
							 "Sei entrato nella stanza: Aula N10\n" + "Borsa vuota\n" + "CFU: 17",
							 "Sei entrato nella stanza: Atrio\n" + "Borsa vuota\n" + "CFU: 16",
							 "Sei entrato nella stanza: Aula N10\n" + "Borsa vuota\n" + "CFU: 15",
							 "Sei entrato nella stanza: Atrio\n" + "Borsa vuota\n" + "CFU: 14",
							 "Sei entrato nella stanza: Aula N10\n" + "Borsa vuota\n" + "CFU: 13",
							 "Sei entrato nella stanza: Atrio\n" + "Borsa vuota\n" + "CFU: 12",
							 "Sei entrato nella stanza: Aula N10\n" + "Borsa vuota\n" + "CFU: 11",
							 "Sei entrato nella stanza: Atrio\n" + "Borsa vuota\n" + "CFU: 10",
							 "Sei entrato nella stanza: Aula N10\n" + "Borsa vuota\n" + "CFU: 9",
							 "Sei entrato nella stanza: Atrio\n" + "Borsa vuota\n" + "CFU: 8",
							 "Sei entrato nella stanza: Aula N10\n" + "Borsa vuota\n" + "CFU: 7",
							 "Sei entrato nella stanza: Atrio\n" + "Borsa vuota\n" + "CFU: 6",
							 "Sei entrato nella stanza: Aula N10\n" + "Borsa vuota\n" + "CFU: 5",
							 "Sei entrato nella stanza: Atrio\n" + "Borsa vuota\n" + "CFU: 4",
							 "Sei entrato nella stanza: Aula N10\n" + "Borsa vuota\n" + "CFU: 3",
							 "Sei entrato nella stanza: Atrio\n" + "Borsa vuota\n" + "CFU: 2",
							 "Sei entrato nella stanza: Aula N10\n" + "Borsa vuota\n" + "CFU: 1",
							 "Sei entrato nella stanza: Atrio\n" + "Borsa vuota\n" + "CFU: 0",
							 "Hai perso!!"};
		
		String[] output = io.getOutput();
		int i = 1;
		while(i<output.length && i<expected.length && expected[i] != null && output[i] != null) {
			assertEquals(expected[i], output[i]);
			i++;
		}
		
	}
	
}