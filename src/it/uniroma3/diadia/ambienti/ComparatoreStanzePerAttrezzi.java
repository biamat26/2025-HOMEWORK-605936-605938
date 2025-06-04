package it.uniroma3.diadia.ambienti;

import java.util.Comparator;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComparatoreStanzePerAttrezzi implements Comparator<Stanza>{

	@Override
	public int compare(Stanza s1, Stanza s2) {
		return s1.getAttrezzi().size() - s2.getAttrezzi().size();
	}

	

}
