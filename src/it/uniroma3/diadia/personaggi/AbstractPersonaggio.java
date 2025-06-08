package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	protected boolean haSalutato;
	
	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
	}
	
	abstract public String agisci(Partita partita);
	
	abstract public String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");
		risposta.append(this.getNome()+". ");
		if(!haSalutato) {
			risposta.append(this.presentazione);
		}else {
			risposta.append("Ci siamo già presentati");
		}
		this.haSalutato = true;
		return risposta.toString();
	}
	
	public String getNome() {
		return this.nome;
	}
	
	@Override
	public String toString() {
		return this.getNome();
	}
}
