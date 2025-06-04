package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {

	private int sogliaMagica;
	private final static int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;


	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagica(String nome, int sogliaMagica) {
		super(nome);
		this.sogliaMagica = sogliaMagica;
		this.contatoreAttrezziPosati = 0;
	}

	@Override
	public boolean isMagica() {
		return true;
	}
	
	public int getContatoreAttrezziPosati() {
		return contatoreAttrezziPosati;
	}

	public void setContatoreAttrezziPosati(int contatoreAttrezziPosati) {
		this.contatoreAttrezziPosati = contatoreAttrezziPosati;
	}
	
	/**
	 * Modifica l'attrezzo: inverte il nome raddoppia il peso
	 * 
	 * @param attrezzo
	 * @return l'attrezzo modificato
	 */

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {

		StringBuilder str = new StringBuilder(attrezzo.getNome());
		str.reverse();

		return new Attrezzo(str.toString(), attrezzo.getPeso() * 2);
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {

		this.contatoreAttrezziPosati++;

		if (contatoreAttrezziPosati > this.sogliaMagica) {
			attrezzo = this.modificaAttrezzo(attrezzo);
		}

		return super.addAttrezzo(attrezzo);
	}
	
	
	@Override
	public int hashCode() {
		return super.hashCode() + sogliaMagica;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		if(o.getClass() != this.getClass()) return false;
		StanzaMagica that = (StanzaMagica) o;
		return this.sogliaMagica == that.sogliaMagica;
	}

	

}
