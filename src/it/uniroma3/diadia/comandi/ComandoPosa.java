package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa implements Comando{
	
	private String nomeAttrezzo;
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		
		if(attrezzo == null) {
			io.mostraMessaggio("L'attrezzo " + nomeAttrezzo + "non è presente nella borsa");
			return;
		}
		
		if(partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
			io.mostraMessaggio("L'attrezzo " + nomeAttrezzo + " è stato rimosso dalla borsa" + "\n" + partita.getGiocatore().getBorsa().toString());
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
		return "posa";
	}

	@Override
	public void setIOConsole(IO io) {
		this.io = io;
	}
	
	
}