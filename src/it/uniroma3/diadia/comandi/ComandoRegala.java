package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		System.out.println(getParametro());
		
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(getParametro());
		
		if(attrezzo == null) {
			io.mostraMessaggio("Non hai messo nessun attrezzo");
			return;
		}
		
		io.mostraMessaggio(stanzaCorrente.getPersonaggio().riceviRegalo(attrezzo, partita));
	}

	@Override
	public String getNome() {
		return "regala";
	}

}
