package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando{

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if(personaggio == null) {
			io.mostraMessaggio("Non c'è nessuno da salutare!");
		}else {
			io.mostraMessaggio(personaggio.saluta());
		}
	}

	@Override
	public String getNome() {
		return "saluta";
	}

}
