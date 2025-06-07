package it.uniroma3.diadia.ambienti;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
		
	private String nome;
	
	/**
	 *  
	 */
		
	private Set<Attrezzo> attrezzi;
   
    //private Map<String, Stanza> stanzeAdiacenti;
    private Map<Direzione, Stanza> stanzeAdiacenti;
    
    private AbstractPersonaggio personaggio;

    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
    	this.nome = nome;
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashSet<>();
    }
    

    public Map<Direzione, Stanza> getMapStanzeAdiacenti(){
    	return this.stanzeAdiacenti;
    }

	public Stanza getStanzaAdiacente(Direzione direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}

	public SortedSet<Stanza> getStanzeAdiacentiOrdinate(){
		return new TreeSet<>(new ComparatoreStanzePerAttrezzi()); 
	}
	
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
    	this.nome = nome;
    }
    
    public String getDescrizione() {
        return this.toString();
    }
  
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}
    
    public Set<Attrezzo> getAttrezzi(){
    	return this.attrezzi;
    }
 
    public Set<Direzione> getDirezioni(){
    	return this.stanzeAdiacenti.keySet();
    }
    
    public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo.getNome().equals(nomeAttrezzo)) {
				return attrezzo;
			}
		}
		return null;
	}
    
    
    public AbstractPersonaggio getPersonaggio() {
		return personaggio;
	}

	public void setPersonaggio(AbstractPersonaggio personaggio) {
		this.personaggio = personaggio;
	}
    
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(attrezzo == null || this.getNumeroAttrezzi() > Configurazione.getNumeroMaxAttrezziStanzaDefault()) {
    		return false;
    	}
    	return attrezzi.add(attrezzo);
    }

    
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
    	if(this.stanzeAdiacenti.keySet().size()>=4 && !this.stanzeAdiacenti.containsKey(direzione)) {
    		return;
    	}
    	this.stanzeAdiacenti.put(direzione, stanza);
    	stanza.stanzeAdiacenti.put(direzione.getDirezioneOpposta(), this);
    }

    
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.contains(this.getAttrezzo(nomeAttrezzo));
	}	

	public boolean removeAttrezzo(Attrezzo attrezzo) {
		return this.attrezzi.remove(attrezzo);
	}	
	
	
	public boolean isMagica() {
		return false;
	}
	
	public boolean isBloccata() {
		return false;
	}
	
	public boolean isBuia() {
		return false;
	}
	 
	
	@Override
	public int hashCode() {
		return this.nome.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null || ! (o instanceof Stanza)) return false;
		Stanza that = (Stanza) o;
		
		return this.getNome().equals(that.getNome());
	}
	
	
    @Override
    public String toString() {
    	
    	StringBuilder risultato = new StringBuilder();
    	
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	
    	for(Direzione direzione : this.stanzeAdiacenti.keySet()) {
    		risultato.append(" " + direzione.toString());
    	}
    	
    	risultato.append("\nAttrezzi nella stanza:");
    	
    	for(Attrezzo a : this.attrezzi) {
    		risultato.append(a.toString() + " ");
    	}
       	
    	return risultato.toString();
    }	
}