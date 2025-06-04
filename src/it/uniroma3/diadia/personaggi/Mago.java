package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{

	
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	
	private Attrezzo attrezzo;
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		if(attrezzo == null) return Mago.MESSAGGIO_SCUSE;
		
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		this.attrezzo = null;
		return Mago.MESSAGGIO_DONO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo == null) throw new IllegalArgumentException("Manca l'attrezzo");
		
		partita.getGiocatore().getBorsa().removeAttrezzo(attrezzo.getNome());
		attrezzo.setPeso(attrezzo.getPeso()/2);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		
		return "Ti ho lasciato un regalo per terra...";
	}
	
}
