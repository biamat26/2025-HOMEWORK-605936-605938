package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{

	private String parametro;
	protected IO io;
	
	@Override
	abstract public void esegui(Partita partita);
	
	@Override
	abstract public String getNome() ;

	@Override
	public void setIOConsole(IO io) {
		this.io = io;
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro = parametro;
		
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}

}
