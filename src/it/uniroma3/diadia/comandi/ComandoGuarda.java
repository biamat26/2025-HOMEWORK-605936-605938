package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoGuarda implements Comando{

	private IO io;
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	@Override
	public void setParametro(String parametro) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getParametro() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setIOConsole(IO io) {
		this.io = io;
	}
}
