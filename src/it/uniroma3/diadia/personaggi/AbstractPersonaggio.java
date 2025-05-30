package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

public abstract class AbstractPersonaggio {
	private String nome;
	private String presentazione;
	private boolean haSalutato;
	
	public AbstractPersonaggio(String nome, String presentazione) {
		this.nome = nome;
		this.presentazione = presentazione;
		this.haSalutato = false;
	}
	
	abstract public String agisci(Partita partita);
	
	public String saluta() {
		StringBuilder risposta = new StringBuilder("Ciao, io sono ");
		risposta.append(this.getNome()+".");
		if(!haSalutato) {
			risposta.append(this.presentazione);
		}else {
			risposta.append("Ci siamo già presentati");
		}
		this.haSalutato = true;
		return risposta.toString();
	}
	
	
	private String getNome() {
		return this.nome;
	}

	@Override
	public String toString() {
		return this.getNome();
	}
}
