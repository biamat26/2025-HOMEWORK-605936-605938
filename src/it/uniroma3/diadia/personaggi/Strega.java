package it.uniroma3.diadia.personaggi;

import java.util.SortedSet;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio{

	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}

	@Override
	public String agisci(Partita partita) {
	    SortedSet<Stanza> stanzeAdiacenti = partita.getStanzaCorrente().getStanzeAdiacentiOrdinate();
	    
	    if (stanzeAdiacenti.isEmpty()) {
	        return "Non ci sono stanze adiacenti in cui spostarti.";
	    }
	    
	    Stanza stanza;
	    if (this.haSalutato) {
	        stanza = stanzeAdiacenti.last();
	    } else {
	        stanza = stanzeAdiacenti.first();
	    }
	    
	    partita.setStanzaCorrente(stanza);
	    return "Ti ho spostato nella stanza che meriti";
	}


	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo == null) throw new IllegalArgumentException("Manca l'attrezzo");
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		
		return "HAHAHAHHAHAH";
	}
}
