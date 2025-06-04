package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi extends AbstractComando{
	
	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		
		Attrezzo attrezzo = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		
		if(attrezzo == null) {
			io.mostraMessaggio("L'attrezzo " + nomeAttrezzo + " non è presente nella stanza");
			return;
		}
		
		if(partita.getGiocatore().getBorsa().addAttrezzo(attrezzo) && 
				partita.getStanzaCorrente().removeAttrezzo(attrezzo)) {
					io.mostraMessaggio("L'attrezzo " + nomeAttrezzo + " è stato preso correttamente" + "\n" + partita.getGiocatore().getBorsa().toString());
		}else {
			io.mostraMessaggio("L'attrezzo " + nomeAttrezzo + " non è stato preso correttamente" + "\n" + partita.getGiocatore().getBorsa().toString());
		}
	}

	
	@Override
	public String getNome() {
		return "prendi";
	}


}