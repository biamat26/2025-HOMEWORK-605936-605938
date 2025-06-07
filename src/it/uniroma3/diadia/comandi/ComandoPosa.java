package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoPosa extends AbstractComando{
	
	private String nomeAttrezzo;
	
	@Override
	public void esegui(Partita partita) {
		
		
		
		if(this.getParametro() == null) {
			io.mostraMessaggio("Non ha inserito alcun attrezzo da posare");
			return;
		}
		
		this.nomeAttrezzo = getParametro();
		
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
		
		if(attrezzo == null) {
			io.mostraMessaggio("L'attrezzo " + nomeAttrezzo + "non è presente nella borsa");
			return;
		}
		
		if(partita.getStanzaCorrente().addAttrezzo(attrezzo)) {
			io.mostraMessaggio("L'attrezzo " + nomeAttrezzo + " è stato rimosso dalla borsa" + "\n" + partita.getGiocatore().getBorsa().toString());
		}
	}

	
	@Override
	public String getNome() {
		return "posa";
	}
	
}