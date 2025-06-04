package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

public class ComandoVai extends AbstractComando{

	private String direzione;
	
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		
		if(this.direzione == null) {
			io.mostraMessaggio("Dove vuoi andare?");
		}
		
		Stanza prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(this.direzione);
		if(prossimaStanza == null) {
			io.mostraMessaggio("La direzione non esiste");
			return;
		}
		
		partita.setStanzaCorrente(prossimaStanza);
		
		if(stanzaCorrente != prossimaStanza) {
			partita.getGiocatore().mossa();
			io.mostraMessaggio("Sei entrato nella stanza: " + partita.getStanzaCorrente().getNome() + "\n" + partita.getGiocatore().toString());
		}else {
			io.mostraMessaggio("Non puoi andare nella direzione inserita" + "\n" + partita.getGiocatore().toString());
		}
	}


	@Override
	public String getNome() {
		return "vai";
	}

}