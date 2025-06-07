package it.uniroma3.diadia.giocatore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.Configurazione;
import it.uniroma3.diadia.attrezzi.*;

public class Borsa {
	
	private Set<Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(Configurazione.getPesoMaxBorsaDefault());
	}
	
	public Borsa(int pesoMax) {
		this.attrezzi = new HashSet<>();
		this.pesoMax = pesoMax;
	}
		
	public Set<Attrezzo> getAttrezzi() {
		return attrezzi;
	}

	public void setAttrezzi(Set<Attrezzo> attrezzi) {
		this.attrezzi = attrezzi;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public void setPesoMax(int pesoMax) {
		this.pesoMax = pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo a: this.attrezzi) {
			if (a.getNome().equals(nomeAttrezzo))
				return a;
		}
		return null;
	}
	
	public int getPeso() {
		int peso = 0;
		for (Attrezzo a : attrezzi)
			peso += a.getPeso();

		return peso;
	}
	
	public int getNumeroAttrezzi() {
		return this.attrezzi.size();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		
		if(attrezzo == null) return false;
		
		if ((this.getPeso() + attrezzo.getPeso()) > this.getPesoMax() || this.getNumeroAttrezzi() > Configurazione.getPesoMaxBorsaDefault()) {
			return false;
		}
		return this.attrezzi.add(attrezzo);
	}
	
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = this.getAttrezzo(nomeAttrezzo);
		return this.attrezzi.remove(a) ? a : null;
	}
	
	
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.contains(this.getAttrezzo(nomeAttrezzo));
	}

	
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){
		List<Attrezzo> lista = new ArrayList<>(this.getAttrezzi());
		Collections.sort(lista, new ComparatorePerPeso());
		return lista;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		SortedSet<Attrezzo> sortedSet = new TreeSet<>(new ComparatorePerPeso());
		sortedSet.addAll(this.getAttrezzi());
		return sortedSet;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> sortedSet = new TreeSet<>(new ComparatorePerNome());
		sortedSet.addAll(this.getAttrezzi());
		return sortedSet;
	}

	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		
		Map<Integer, Set<Attrezzo>> mappa = new HashMap<>();
		
		for(Attrezzo attrezzo : this.getAttrezzi()) {
			
			if(!mappa.containsKey(attrezzo.getPeso())) {
				Set<Attrezzo> set = new HashSet<>();
				mappa.put(attrezzo.getPeso(), set);
			}
		
			mappa.get(attrezzo.getPeso()).add(attrezzo);
		}
		
		return mappa;
	}
	


	
	
	
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (Attrezzo attrezzo : this.attrezzi) {
				s.append(attrezzo.toString()+" ");
			}
		}
		else {
			s.append("Borsa vuota");
		}
		return s.toString();
	}

}