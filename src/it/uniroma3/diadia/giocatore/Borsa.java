package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	

	public static final int DEFAULT_PESO_MAX_BORSA = 10;
	public static final int DEFAULT_NUMERO_MAX_ATTREZZI = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[DEFAULT_NUMERO_MAX_ATTREZZI]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		
		if(attrezzo == null) {
			return false;
		}
		
		if(this.numeroAttrezzi == DEFAULT_NUMERO_MAX_ATTREZZI) {
			return false;
		}
		
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) {
			return false;
		}
		
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

		return a;
	}
	
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			peso += this.attrezzi[i].getPeso();

		return peso;
	}
	
	public int getNumeroAttrezzi() {
		return numeroAttrezzi;
	}

	public void setNumeroAttrezzi(int numeroAttrezzi) {
		this.numeroAttrezzi = numeroAttrezzi;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * 
	 * @param nomeAttrezzo
	 * @return attrezzo che ho rimosso
	 */

	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
	    Attrezzo attrezzo = this.getAttrezzo(nomeAttrezzo);

	    if (attrezzo == null) {
	        return null; // Se non esiste, restituisci null
	    }

	    // Trova l'indice dell'attrezzo da rimuovere
	    int indice = -1;
	    for (int i = 0; i < this.numeroAttrezzi; i++) {
	        if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
	            indice = i;
	            break; // Troviamo solo il primo che corrisponde
	        }
	    }

	    // Se l'attrezzo è stato trovato, spostiamo gli elementi a sinistra per compattare l'array
	    if (indice != -1) {
	        for (int i = indice; i < this.numeroAttrezzi - 1; i++) {
	            this.attrezzi[i] = this.attrezzi[i + 1];
	        }
	        
	        // Imposta l'ultimo elemento a null (dato che lo abbiamo spostato)
	        this.attrezzi[this.numeroAttrezzi - 1] = null;
	        
	        // Decrementa il numero di attrezzi
	        this.numeroAttrezzi = this.numeroAttrezzi - 1;
	    }

	    return attrezzo;
	}

	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++) {
				s.append(attrezzi[i].toString()+" ");
			}
		}
		else {
			s.append("Borsa vuota");
		}
		return s.toString();
	}
}