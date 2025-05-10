package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPrendi implements Comando{
	
	private String nomeAttrezzo;
	private IO io;
	
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
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}

	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public void setIOConsole(IO io) {
		this.io = io;
	}
}