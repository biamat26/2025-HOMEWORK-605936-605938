package it.uniroma3.diadia.attrezzi;
import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Una semplice classe che modella un attrezzo.
 * Gli attrezzi possono trovarsi all'interno delle stanze
 * del labirinto.
 * Ogni attrezzo ha un nome ed un peso.
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */
public class Attrezzo {

	private String nome;
	private int peso;


	
	public Attrezzo(String nome, int peso) {
		this.peso = peso;
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public int getPeso() {
		return this.peso;
	}
	
	public void setPeso(int peso) {
		this.peso = peso;
	}
	
	@Override
	public int hashCode() {
		return this.nome.hashCode() + this.peso*31;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o==null || o.getClass() != this.getClass()) return false;
		Attrezzo that = (Attrezzo) o;
		return this.getNome().equals(that.getNome()) && this.getPeso() == that.getPeso();
	}
	
	@Override
	public String toString() {
		return this.getNome()+" ("+this.getPeso()+"kg)";
	}
}