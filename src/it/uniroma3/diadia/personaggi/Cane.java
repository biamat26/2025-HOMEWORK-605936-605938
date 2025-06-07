package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio{

	public final static String CIBO_PREFERITO_DEFAULT = "osso";
	private String ciboPreferito;
	private Attrezzo attrezzo;
	
	public Cane(String nome, Attrezzo attrezzo, String presentazione) {
		this(nome, attrezzo, presentazione, CIBO_PREFERITO_DEFAULT);
	}
	
	public Cane(String nome, Attrezzo attrezzo, String presentazione, String ciboPreferito) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
		this.ciboPreferito = ciboPreferito;
	}

	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);	
		return "Sei furibondo! Il cane ti ha morso";
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo == null) throw new IllegalArgumentException("Manca l'attrezzo");
		
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		
		if(attrezzo.getNome().equals(this.ciboPreferito)) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			return "Bau Bau (Tradotto: ho lasciato qualcosa per terra)";
		}
		
		return agisci(partita);
	}

}
