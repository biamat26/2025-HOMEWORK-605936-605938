package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.lang.invoke.StringConcatFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class CaricatoreLabirinto {

	/** prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/** prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/** prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  

	/** prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/** prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";
	
	/** prefisso di una singola riga di testo contenente tutti i nomi delle stanze bloccate */
	private static final String STANZE_BLOCCATA_MARKER = "Bloccate:";
	
	/** prefisso di una singola riga di testo contenente tutti i nomi delle stanze magiche */
	private static final String STANZE_MAGICHE_MARKER= "Magiche:";
	
	/** prefisso di una singola riga di testo contenente tutti i nomi delle stanze buie */
	private static final String STANZE_BUIE_MARKER= "Buie:";
	
	/** prefisso della riga contenente le specifiche dei cani da collocare */
	private static final String CANE_MARKER = "Cane:";
	
	/** prefisso della riga contenente le specifiche dei maghi da collocare */
	private static final String MAGO_MARKER = "Mago:";
	
	/** prefisso della riga contenente le specifiche delle streghe da collocare */
	private static final String STREGA_MARKER = "Strega:";
	
	/** tiene traccia del numero di riga mentre leggi un file riga per riga*/
	private LineNumberReader reader;
	private LabirintoBuilder builder;
	
	
	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.reader = new LineNumberReader(new FileReader(nomeFile));
		this.builder = new LabirintoBuilder();
	}
	
	public Labirinto getLabirinto() {
		return this.builder.getLabirinto();
	}
	
	public void carica() throws FormatoFileNonValidoException, RuntimeException{
		try{
			this.leggiECreaStanze();
			this.leggiStanzaIniziale();
			this.leggiStanzaVincente();
			this.leggiECollocaAttrezzi();
			this.leggiECollocaUscita();
			this.leggiStanzeBloccate();
			this.leggiStanzeMagiche();
			this.leggiStanzeBuie();
			this.leggiECollocaCane();
			this.leggiECollocaMago();
			this.leggiECollocaStrega();
		}
		finally {
			try {
				this.reader.close();
			}catch(IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}


	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza);
		}
	}
	
	private void leggiStanzaIniziale() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale= leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale + " non definita");
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
	}
	
	private void leggiStanzaVincente() throws FormatoFileNonValidoException{
		String nomeStanzaVincente = leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.builder.addStanzaVincente(nomeStanzaVincente);
	}
	
	private void leggiStanzeBloccate() throws FormatoFileNonValidoException {
		String specificheStanze = leggiRigaCheCominciaPer(STANZE_BLOCCATA_MARKER);
		for(String specificheStanza : separaStringheAlleVirgole(specificheStanze)) {
			String nomeStanza = null;
			String nomeAttrezzoSblocco = null;
			String nomeDirezione = null; 
			try(Scanner scanner = new Scanner(specificheStanza)){
				check(scanner.hasNext(), msgTerminazionePrecoce("nome della stanza"));
				nomeStanza = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della direzione bloccata."));
				nomeDirezione = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome dell'attrezzo sblocco."));
				nomeAttrezzoSblocco = scanner.next(); 
			}
			this.builder.addStanzaBloccata(nomeStanza, nomeDirezione, nomeAttrezzoSblocco);
		}
	}
	
	private void leggiStanzeMagiche() throws FormatoFileNonValidoException {
		String specificheStanze = leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String specificheStanza : separaStringheAlleVirgole(specificheStanze)) {
			String sogliaMagica = null; 
			String nomeStanza = null;
			try(Scanner scanner = new Scanner(specificheStanza)){
				check(scanner.hasNext(), msgTerminazionePrecoce("nome della stanza"));
				nomeStanza = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("la soglia magica."));
				sogliaMagica = scanner.next();
			}
			this.builder.addStanzaMagica(nomeStanza, getNumber(sogliaMagica, "Soglia " + sogliaMagica + " non valida"));
		}
	}
	
	
	private void leggiStanzeBuie() throws FormatoFileNonValidoException {
		String specificaStanze = leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specificheStanza : separaStringheAlleVirgole(specificaStanze)) {
			String nomeStanza = null;
			String attrezzoLuminoso = null; 
			try(Scanner scanner = new Scanner(specificheStanza)){
				check(scanner.hasNext(), msgTerminazionePrecoce("nome della stanza"));
				nomeStanza = scanner.next();
				check(scanner.hasNext(), msgTerminazionePrecoce("l'attrezzo luminoso"));
				attrezzoLuminoso = scanner.next();
			}
			this.builder.addStanzaBuia(nomeStanza, attrezzoLuminoso);
		}
	}
	

	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = leggiRigaCheCominciaPer(ATTREZZI_MARKER);
		for(String specificheAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null;
			try(Scanner sc = new Scanner(specificheAttrezzo)){
				check(sc.hasNext(), msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = sc.next();
				check(sc.hasNext(), msgTerminazionePrecoce("il peso dell'attrezzo: " + nomeAttrezzo));
				pesoAttrezzo = sc.next();
				check(sc.hasNext(), msgTerminazionePrecoce("il nome della stanza dove colloccare: " + nomeAttrezzo));
				nomeStanza = sc.next();
			}
			impostaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}
	
	private void leggiECollocaCane() throws FormatoFileNonValidoException {
		String specifichePersonaggi = leggiRigaCheCominciaPer(CANE_MARKER);
		for(String specifichePersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String nome = null;
			String nomeAttrezzo = null;
			String pesoString = null;
			String presentazione = null;
			String nomeStanza = null;
			String ciboPreferito = null;
			
			try(Scanner sc = new Scanner(specifichePersonaggio)){
				check(sc.hasNext(),msgTerminazionePrecoce("il nome del cane"));
				nome = sc.next();
				check(sc.hasNext(),msgTerminazionePrecoce("l'attrezzo che dona il cane"));
				nomeAttrezzo = sc.next();
				check(sc.hasNext(),msgTerminazionePrecoce("il peso dell' attrezzo: " + nomeAttrezzo));
				pesoString = sc.next();
				check(sc.hasNext(),msgTerminazionePrecoce("la presentazione del cane"));
				presentazione = sc.next();
				check(sc.hasNext(),msgTerminazionePrecoce("la stanza dove sta il cane"));
				nomeStanza = sc.next();
				
				if(sc.hasNext()) ciboPreferito = sc.next();
			}
			
			int peso = getNumber(pesoString, "Attrezzo "+ nomeAttrezzo+" non collocabile: peso " +pesoString+" non valido.");
			
			if(ciboPreferito == null) {
				this.builder.addCane(nome, nomeAttrezzo, peso, presentazione, nomeStanza);
			}else {
				this.builder.addCane(nome, nomeAttrezzo, peso, presentazione, ciboPreferito, nomeStanza);
			}
		}
	}
	
	private void leggiECollocaStrega() throws FormatoFileNonValidoException {
		
		String specifichePersonaggi = leggiRigaCheCominciaPer(STREGA_MARKER);
		for(String specifichePersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String nome = null;
			String presentazione = null;
			String nomeStanza = null;
			
			try(Scanner sc = new Scanner(specifichePersonaggio)){
				check(sc.hasNext(),msgTerminazionePrecoce("il nome della strega"));
				nome = sc.next();
				check(sc.hasNext(),msgTerminazionePrecoce("la presentazione della strega"));
				presentazione = sc.next();
				check(sc.hasNext(),msgTerminazionePrecoce("la stanza dove sta la strega"));
				nomeStanza = sc.next();
			
			}
			this.builder.addStrega(nome, presentazione, nomeStanza)	;
		}
	}
	
	private void leggiECollocaMago() throws FormatoFileNonValidoException {
		String specifichePersonaggi = leggiRigaCheCominciaPer(MAGO_MARKER);
		for(String specifichePersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String nome = null;
			String nomeAttrezzo = null;
			String pesoString = null;
			String presentazione = null;
			String nomeStanza = null;
			
			try(Scanner sc = new Scanner(specifichePersonaggio)){
				check(sc.hasNext(),msgTerminazionePrecoce("il nome del mago"));
				nome = sc.next();
				check(sc.hasNext(),msgTerminazionePrecoce("l'attrezzo che dona il mago"));
				nomeAttrezzo = sc.next();
				check(sc.hasNext(),msgTerminazionePrecoce("il peso dell' attrezzo: " + nomeAttrezzo));
				pesoString = sc.next();
				check(sc.hasNext(),msgTerminazionePrecoce("la presentazione del mago"));
				presentazione = sc.next();
				check(sc.hasNext(),msgTerminazionePrecoce("la stanza dove sta il mago"));
				nomeStanza = sc.next();
			
			}
			
			int peso = getNumber(pesoString, "Attrezzo "+ nomeAttrezzo+" non collocabile: peso " +pesoString+" non valido.");
			
			this.builder.addMago(nome, nomeAttrezzo, peso, presentazione, nomeStanza);	
			
		}
	}
	

	
	private void leggiECollocaUscita() throws FormatoFileNonValidoException {
		String specificheUscite = leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificheUscita : separaStringheAlleVirgole(specificheUscite)) {
			String stanzaPartenza = null;
			String direzione = null;
			String stanzaDestinazione = null;
			try(Scanner sc = new Scanner(specificheUscita)){
				check(sc.hasNext(), msgTerminazionePrecoce("il nome della stanza di partenza."));
				stanzaPartenza = sc.next();
				check(sc.hasNext(), msgTerminazionePrecoce("la direzione dell'uscita della stanza " + stanzaPartenza));
				direzione = sc.next();
				check(sc.hasNext(), msgTerminazionePrecoce("la destinazione di una uscita della stanza " + 
															stanzaPartenza + " nella direzione " + direzione));
				stanzaDestinazione = sc.next();
			}
			impostaUscita(stanzaPartenza, direzione, stanzaDestinazione);
		}
	}
		
	private void impostaUscita(String stanzaPartenza, String direzione, String stanzaDestinazione) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaPartenza),msgTerminazionePrecoce("stanza di partenza sconosciuta."));
		check(isDirezioneValida(direzione), msgTerminazionePrecoce("direzione sconosciuta."));
		check(isStanzaValida(stanzaDestinazione), msgTerminazionePrecoce("stanza destinazione sconosciuta."));
		this.builder.addAdiacenza(stanzaPartenza, stanzaDestinazione, direzione);
	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker), "Era attesa una riga che cominciasse per: " + marker);
			return riga.substring(marker.length());
		}catch(IOException e) {
			throw new FormatoFileNonValidoException();
		}
	}
	
	private List<String> separaStringheAlleVirgole(String string){
		List<String> lista = new ArrayList<>();
		try(Scanner sc = new Scanner(string)){
			sc.useDelimiter(",");
			while (sc.hasNext()) {
	            lista.add(sc.next().trim()); // trim() rimuove spazi
	        }
		}
		return lista;
	}
	
	final private void check (boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if(!condizioneCheDeveEsseraVera) {
			throw new FormatoFileNonValidoException("Formato file non valido [" + 
			this.reader.getLineNumber() + "] "+messaggioErrore);	
		}
	}
	
	private void impostaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {	
		final String msgString = "Attrezzo "+ nomeAttrezzo+" non collocabile: peso " +pesoAttrezzo+" non valido.";
		Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, this.getNumber(pesoAttrezzo, msgString));
		this.builder.getListaStanze().get(nomeStanza).addAttrezzo(attrezzo);
	}
	
	private int getNumber(String numero, String msg) throws FormatoFileNonValidoException {
		int number = 0;
		try {
			number = Integer.parseInt(numero);
		}catch (NumberFormatException e) {
			throw new FormatoFileNonValidoException(msg);
		}
		return number;
	}
	
	private boolean isDirezioneValida(String direzione) {
		return Direzione.getDirezioni().contains(direzione);
	}
	
	private boolean isStanzaValida(String nomeStanza) {
		return this.builder.getListaStanze().containsKey(nomeStanza);
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}
}
