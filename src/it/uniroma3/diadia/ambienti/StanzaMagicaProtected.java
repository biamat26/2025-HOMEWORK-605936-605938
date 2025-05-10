package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected{
	
	private int sogliaMagica;
	private final static int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;

	public StanzaMagicaProtected(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagicaProtected(String nome, int sogliaMagica) {
		super(nome);
		this.sogliaMagica = sogliaMagica;
		this.contatoreAttrezziPosati = 0;
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
		
		if(this.numeroAttrezzi < this.attrezzi.length) {
			this.attrezzi[this.numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}else {
			return false;
		}
		
	}
}
