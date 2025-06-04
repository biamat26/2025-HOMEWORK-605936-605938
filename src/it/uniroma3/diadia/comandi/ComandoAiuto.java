package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando{
	
	static final private String[] elencoComandi = {"vai", "prendi", "posa", "aiuto", "fine", "guarda"};
	
	@Override
	public void esegui(Partita partita) {
		for(String s : elencoComandi) {
			this.io.mostraMessaggio(s + " ");
		}
	}

	@Override
	public String getNome() {
		return "aiuto";
	}
}
