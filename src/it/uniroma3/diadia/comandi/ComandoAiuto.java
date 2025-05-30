package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando{
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine", "guarda"};
	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		for(String s : elencoComandi) {
			this.io.mostraMessaggio(s + " ");
		}
	}

	@Override
	public void setParametro(String parametro) {
		// Il parametro è inutile
	}

	@Override
	public String getNome() {
		return "aiuto";
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
