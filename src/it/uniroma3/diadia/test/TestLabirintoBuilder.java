package it.uniroma3.diadia.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class TestLabirintoBuilder {
	private Labirinto.LabirintoBuilder labirintoBuilder;
	private String nomeStanzaIniziale = "Atrio";
	private String nomeStanzaVincente = "Uscita";

	@Before
	public void setUp() throws Exception {
		labirintoBuilder = new Labirinto.LabirintoBuilder();
	}

	@Test
	public void testMonolocale() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaIniziale)
				.getLabirinto();
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
	assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzo() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("spada",1)
				.addStanzaVincente(nomeStanzaIniziale).addAttrezzo("spadina", 3)
				.getLabirinto();
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaIniziale,monolocale.getStanzaVincente().getNome());
		assertEquals("spada",monolocale.getStanzaIniziale().getAttrezzo("spada").getNome());
		assertEquals("spadina",monolocale.getStanzaVincente().getAttrezzo("spadina").getNome());
	}
	
	@Test
	public void testMonolocaleConAttrezzoSingoloDuplicato() {
		Labirinto monolocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addAttrezzo("spada",1)
				.addAttrezzo("spada",1)
				.getLabirinto();
		int size = monolocale.getStanzaIniziale().getAttrezzi().size();
		assertTrue(size==1);
		assertEquals(Set.of(new Attrezzo("spada",1)),monolocale.getStanzaIniziale().getAttrezzi());
	}
	
	@Test
	public void testBilocale() {
		Labirinto bilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza(nomeStanzaIniziale, nomeStanzaVincente, Direzione.NORD.toString())
				.getLabirinto();
		assertEquals(bilocale.getStanzaVincente(),bilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD));
		assertEquals(Set.of(Direzione.NORD),bilocale.getStanzaIniziale().getDirezioni());
		assertEquals(Set.of(Direzione.SUD),bilocale.getStanzaVincente().getDirezioni());
	}
	
	@Test
	public void testTrilocale(){
		Labirinto trilocale = labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale).addAttrezzo("sedia", 1)
				.addStanza("biblioteca")
				.addAdiacenza(nomeStanzaIniziale, "biblioteca", Direzione.SUD.toString())
				.addAttrezzo("libro antico", 5)
				.addStanzaVincente(nomeStanzaVincente)
				.addAdiacenza("biblioteca", nomeStanzaVincente, Direzione.EST.toString())
				.getLabirinto();	
		assertEquals(nomeStanzaIniziale, trilocale.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("biblioteca",trilocale.getStanzaIniziale().getStanzaAdiacente(Direzione.SUD).getNome());
	}
	
	@Test
	public void testTrilocaleConStanzaDuplicata() {
				labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza("stanza generica")
				.addStanza("stanza generica")
				.addAdiacenza(nomeStanzaIniziale, "stanza generica", Direzione.NORD.toString())
				.getLabirinto();
		assertTrue(labirintoBuilder.getListaStanze().size()<=2);
	}
	
	
	@Test
	public void testImpostaStanzaInizialeCambiandola() {
		Labirinto maze = labirintoBuilder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addStanza("nuova iniziale")
				.addStanzaIniziale("nuova iniziale")
				.getLabirinto();
		assertEquals("nuova iniziale",maze.getStanzaIniziale().getNome());
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_Iniziale() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		Labirinto maze = this.labirintoBuilder
				.addStanzaIniziale(this.nomeStanzaIniziale)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		assertEquals(attrezzo,maze.getStanzaIniziale().getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezziAStanze_AppenaAggiunte() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		assertTrue(this.labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzi().contains(new Attrezzo (nomeAttrezzo,peso)));
		assertEquals(new Attrezzo(nomeAttrezzo,peso),this.labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzo(nomeAttrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_AppenaAggiunteMultiple() {
		String nomeAttrezzo = "attrezzo";
		int peso = 1;
		String nomeStanza = "stanza 1";
		this.labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanza(nomeStanza)
				.addAttrezzo(nomeAttrezzo, peso)
				.getLabirinto();
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo,peso);
		Set<Attrezzo> attrezzi = labirintoBuilder.getListaStanze().get(nomeStanza).getAttrezzi();
		assertTrue(attrezzi.contains(attrezzo));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_MoltepliciAttrezziStessaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo2));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
	}
	
	
	@Test  //verifico che gli attrezzi vengano aggiunti all'ultima stanza aggiunta
	public void testAggiuntaAttrezzoAStanze_AttrezzoAggiuntoAllaSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testAggiuntaAttrezzoAStanze_PrimoAttrezzoInUnaStanzaSecondoAttrezzoSecondaStanza() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		int peso1 = 1;
		int peso2 = 2;
		String nomeStanza1 = "Stanza 1";
		String nomeStanza2 = "Stanza 2";
		this.labirintoBuilder
		.addStanza(nomeStanza1)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addStanza(nomeStanza2)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1),listaStanze.get(nomeStanza1).getAttrezzo(nomeAttrezzo1));
		assertEquals(new Attrezzo(nomeAttrezzo2,peso2),listaStanze.get(nomeStanza2).getAttrezzo(nomeAttrezzo2));
	}
	
	@Test
	public void testLabirintoConStanzaMagica() {
		int sogliaMagica = 1;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica);
		StanzaMagica stanzaMagica = (StanzaMagica)labirintoBuilder.getListaStanze().get(nomeStanzaMagica);
		assertTrue(stanzaMagica.isMagica());
	}
	
	@Test
	public void testLabirintoConStanzaMagica_AggiuntaElementoOltreSogliaMagica() {
		String nomeAttrezzo1 = "attrezzo 1";
		String nomeAttrezzo2 = "attrezzo 2";
		String nomeAttrezzo2Inv = "2 ozzertta";
		int sogliaMagica = 1;
		int peso1 = 1;
		int peso2 = 2;
		int peso2_x2 = peso2*2;
		String nomeStanzaMagica = "Stanza Magica";
		this.labirintoBuilder
		.addStanzaMagica(nomeStanzaMagica, sogliaMagica)
		.addAttrezzo(nomeAttrezzo1, peso1)
		.addAttrezzo(nomeAttrezzo2, peso2);
		Map<String, Stanza> listaStanze = labirintoBuilder.getListaStanze();
		assertEquals(new Attrezzo(nomeAttrezzo2Inv,peso2_x2), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo2Inv));
		assertEquals(new Attrezzo(nomeAttrezzo1,peso1), listaStanze.get(nomeStanzaMagica).getAttrezzo(nomeAttrezzo1));
	}
	
	
	@Test
	public void testLabirintoConStanzaBloccata_ConPassepartout() {
		this.labirintoBuilder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", Direzione.NORD.toString(), "chiave").addAttrezzo("chiave", 1)
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", Direzione.NORD.toString())
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, Direzione.NORD.toString());
		Stanza stanzaVincente = new Stanza(nomeStanzaVincente);
		//Asserisce che in presenza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna la corretta adiacenza
		assertEquals(stanzaVincente,labirintoBuilder.getListaStanze().get("stanza bloccata").getStanzaAdiacente(Direzione.NORD));	
	}
	
	@Test
	public void testLabirintoConStanzaBloccata_SenzaPassepartout() {
		this.labirintoBuilder
		.addStanzaIniziale(nomeStanzaIniziale)
		.addStanzaBloccata("stanza bloccata", Direzione.NORD.toString(), "chiave")
		.addAdiacenza(nomeStanzaIniziale, "stanza bloccata", Direzione.NORD.toString())
		.addStanzaVincente(nomeStanzaVincente)
		.addAdiacenza("stanza bloccata", nomeStanzaVincente, Direzione.NORD.toString());
		Stanza stanzaBloccata = new StanzaBloccata("stanza bloccata", Direzione.NORD, "chiave");
		//Asserisce che in caso di mancanza di passepartout, invocato il metodo getStanzaAdiacente(), la stanza bloccata ritorna se stessa
		assertEquals(stanzaBloccata,labirintoBuilder.getListaStanze().get("stanza bloccata").getStanzaAdiacente(Direzione.NORD));
	}
	
	@Test
	public void testLabirintoCompletoConTutteLeStanze() {
		
		Labirinto labirintoCompleto = this.labirintoBuilder
				.addStanzaIniziale(nomeStanzaIniziale)
				.addStanzaVincente(nomeStanzaVincente)
				.addStanza("corridoio")
				.addAttrezzo("chiave", 1)
				.addAttrezzo("lanterna", 1)
				.addStanzaBloccata("corridoio bloccato",Direzione.NORD.toString(),"chiave")
				.addStanzaMagica("stanza magica", 1)
				.addStanzaBuia("stanza buia","lanterna")
				.addStanza("Aula 1")
				.addAdiacenza(nomeStanzaIniziale, "corridoio", Direzione.NORD.toString())
				.addAdiacenza("corridoio", "corridoio bloccato", Direzione.NORD.toString())
				.addAdiacenza("corridoio bloccato", "Aula 1", Direzione.NORD.toString())
				.addAdiacenza("Aula 1", nomeStanzaVincente,Direzione.NORD.toString())
				.addAdiacenza("corridoio", "stanza magica", Direzione.EST.toString())
				.addAdiacenza("corridoio", "stanza buia", Direzione.OVEST.toString())
				.getLabirinto();
		assertEquals(nomeStanzaIniziale, labirintoCompleto.getStanzaIniziale().getNome());
		assertEquals(nomeStanzaVincente, labirintoCompleto.getStanzaVincente().getNome());
		Stanza corridoio = labirintoCompleto.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD);
		assertEquals("corridoio",corridoio.getNome());
		assertTrue(corridoio.getDirezioni().containsAll(Arrays.asList(Direzione.OVEST,Direzione.EST,Direzione.NORD, Direzione.SUD)));
		Map<Direzione,Stanza> mapAdiacenti = new HashMap<>();
		mapAdiacenti.put(Direzione.NORD,new Stanza("corridoio bloccato"));
		mapAdiacenti.put(Direzione.SUD,new Stanza(nomeStanzaIniziale));
		mapAdiacenti.put(Direzione.EST,new Stanza("stanza magica"));
		mapAdiacenti.put(Direzione.OVEST,new Stanza("stanza buia"));
		assertEquals(mapAdiacenti,corridoio.getMapStanzeAdiacenti());
		Attrezzo a1 = new Attrezzo("chiave",1);
		Attrezzo a2 = new Attrezzo("lanterna",1);
		assertEquals(Set.of(a1,a2),corridoio.getAttrezzi());
	}
}
