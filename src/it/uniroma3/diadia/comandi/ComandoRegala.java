package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Attrezzo attrezzo = stanzaCorrente.getAttrezzo(getParametro());
		
		if(attrezzo == null) io.mostraMessaggio("Non hai messo nessun attrezzo");
		
		if(!partita.getGiocatore().getBorsa().hasAttrezzo(attrezzo.getNome())){
			io.mostraMessaggio("Non hai " + attrezzo.getNome() + " nella borsa...");
			return;
		}
	
		io.mostraMessaggio(stanzaCorrente.getPersonaggio().riceviRegalo(attrezzo, partita));
	}

	@Override
	public String getNome() {
		return "regala";
	}

}
