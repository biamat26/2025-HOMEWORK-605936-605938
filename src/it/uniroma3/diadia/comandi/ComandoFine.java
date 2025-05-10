package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{

	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Partita finita, grazie per aver giocato!");
		partita.setFinita();
	}

	@Override
	public void setParametro(String parametro) {
		// non fa nulla...
	}

	@Override
	public String getNome() {
		return "fine";
	}

	@Override
	public String getParametro() {
		return null;
	}
	
	@Override
	public void setIOConsole(IO io) {
		this.io = io;
	}

}
