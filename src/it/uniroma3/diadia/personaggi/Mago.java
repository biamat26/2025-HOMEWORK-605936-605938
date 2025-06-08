package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio{

	private Attrezzo attrezzo;
	
	public Mago(String nome, Attrezzo attrezzo, String presentazione) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}

	@Override
	public String agisci(Partita partita) {
		if(attrezzo == null) return Configurazione.getMessaggioScuseMagoDefault();		
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		this.attrezzo = null;
		return Configurazione.getMessaggioDonoMagoDefault();
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
