package it.uniroma3.diadia;
import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = 
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine"};

	private Partita partita;
	private IOConsole console;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		
		String istruzione; 
		console = new IOConsole();
		console.mostraMessaggio(MESSAGGIO_BENVENUTO);			
	
		do {	
			istruzione = console.leggiRiga();
		
		}while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if(comandoDaEseguire.getNome() == null) {
			console.mostraMessaggio(istruzione);
			return false;
		}
		
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			console.mostraMessaggio("Comando sconosciuto");
		
		if(this.partita.isFinita()) {
			if(this.partita.vinta()) {
				console.mostraMessaggio("Hai vinto");
			}else if(this.partita.persa()) {
				console.mostraMessaggio("Hai perso");
			}else if(this.partita.getFinita()){
				console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
			}
			return true;
		}else {
			return false;
		}
	}   
	
	//Prendi dalla stanza un oggetto e posalo nella borsa
	private void prendi(String nomeAttrezzo) {
		
		Attrezzo attrezzo = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		
		// Aggiungo l'attrezzo nella borsa
		if(this.partita.getBorsa().addAttrezzo(attrezzo) && this.partita.getStanzaCorrente().removeAttrezzo(attrezzo)) {
			console.mostraMessaggio("Operazione riuscita");
		}else {
			console.mostraMessaggio("Operazione non riuscita");
		}
	}

	//Prendi dalla borsa un oggetto e posalo nella stanza
	private void posa(String nomeAttrezzo) {
		
		if(nomeAttrezzo == null) {
			console.mostraMessaggio("Non ha inserito l'elemento da prendere");
			return;
		}
		if(!this.partita.getBorsa().hasAttrezzo(nomeAttrezzo)) {
			console.mostraMessaggio("Elemento non presente");
			return;
		}
		
		Attrezzo attrezzoDaPosare = this.partita.getBorsa().removeAttrezzo(nomeAttrezzo);
		if(this.partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)) {
			console.mostraMessaggio("Operazione riuscita");
		}else {
			console.mostraMessaggio("Operazione non riuscita");
		}
	}

//	private void posa(String nomeAttrezzo) {
//	    // Otteniamo l'attrezzo dalla borsa
//	    Attrezzo attrezzoDaPosare = this.partita.getBorsa().removeAttrezzo(nomeAttrezzo);
//
//	    // Se l'attrezzo non è stato trovato nella borsa, informiamo l'utente
//	    if (attrezzoDaPosare == null) {
//	        console.mostraMessaggio("Attrezzo non presente nella borsa.");
//	        return;
//	    }
//
//	    // Tentiamo di posare l'attrezzo nella stanza corrente
//	    if (this.partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)) {
//	        console.mostraMessaggio("Operazione riuscita");
//	    } else {
//	        // Se non si può posare l'attrezzo nella stanza, rimettiamo l'attrezzo nella borsa
//	        this.partita.getBorsa().addAttrezzo(attrezzoDaPosare);
//	        console.mostraMessaggio("Operazione non riuscita: la stanza non può contenere altri attrezzi");
//	    }
//	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) {
			console.mostraMessaggio(elencoComandi[i]+" ");
		}
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	
	private void vai(String direzione) {
		if(direzione==null)
			console.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			this.partita.getGiocatore().mossa();	// Una mossa ti leva un cfu 
		}
		console.mostraMessaggio(partita.toString());
	}

	/**
	 * Comando "Fine".
	 */
	
	private void fine() {
		this.partita.setFinita();
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}