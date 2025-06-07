package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;

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
	
	private Partita partita;
	private IO console;

	
	public DiaDia(IO io) {
		this.partita = new Partita();
		this.console = io;
	}

	public DiaDia(Labirinto labirinto, IO io) {
		// fa cose...
	}
	
	
	public void gioca() {
		String istruzione; 
		console.mostraMessaggio(MESSAGGIO_BENVENUTO);			
		do {	
			istruzione = console.leggiRiga();
		
		}while (!processaIstruzione(istruzione));
		
		if(partita.vinta()) {
			console.mostraMessaggio("Hai Vinto!");
		}else if(partita.persa()){
			console.mostraMessaggio("Hai Perso!");
		}
	}   

	
	private boolean processaIstruzione(String istruzione) {
		
		FabbricaDiComandi fabbricaComando = new FabbricaDiComandiRiflessiva();
		
		Comando command = fabbricaComando.costruisciComando(istruzione);
		
		command.setIOConsole(console);
		command.esegui(partita);
		
		return partita.isFinita();
	}
	
//vai, posa, prendi, fabbrica comandi, fabbricaComandiFisarmonica, stanza buia, stanza bloccata, stanza maggica
	
	public static void main(String[] argc) {
		
		// IO io = IOConsole.getInstance(); Modo per creare una sola instanza di IOConsole, con il pattern Singleton
		
		IO io = new IOConsole();
		//Labirinto labirinto = new LabirintoBuilder();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}