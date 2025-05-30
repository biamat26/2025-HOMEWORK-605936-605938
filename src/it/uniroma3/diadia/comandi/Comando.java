package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public interface Comando {
	
	/**
	 * esecuzione del comando:
	 * @param partita
	 */
	public void esegui(Partita partita);
	
	/**
	 * 
	 * @param io
	 */
	
	public void setIOConsole(IO io);
	
	/**
	 * set parametro del comando
	 * @param istruzione
	 */
	public void setParametro(String parametro);
	
	public String getNome();
	public String getParametro();
	
}
