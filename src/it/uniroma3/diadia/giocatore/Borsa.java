package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.ConfiguraProprieta;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import it.uniroma3.diadia.comparatori.*;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.SortedSet;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Questa classe modella la borsa di un giocatore.
 * 
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = Integer.parseInt(ConfiguraProprieta.getPesoMaxBorsa());				//la borsa ha un peso massimo di 10 kg
	private Map<String,Attrezzo> attrezzi;
	private int pesoMax;
	
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	/**
	 * Crea una borsa vuota
	 * @param pesoMax peso massimo che può avere la borsa
	 */
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<String,Attrezzo>();
	}
	
	/**
	 * Aggiunge un attrezzo all'interno della borsa
	 * @param attrezzo riferimento all'attrezzo che si vuole aggiungere
	 * @return true se l'operazione è andata a buon fine, altrimenti false
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {					//se un attrezzo con lo stesso nome già esiste,
        if (attrezzo.getPeso()+this.getPeso() <= this.pesoMax) {	//viene sostituito
        	this.attrezzi.put(attrezzo.getNome(),attrezzo);
			return true;
        }
        return false;
    }
	
	/**
	 * Verifica se la borsa contiene un attrezzo cercato per nome
	 * @param nomeAttrezzo nome dell'attrezzo che si vuole cercare
	 * @return true se l'attrezzo cercato è stato trovato, altrimenti false
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}
	
	/**
	 * Restuisce il riferimento all'attrezzo cercato per nome
	 * @param nomeAttrezzo nome dell'attrezzo di cui si vuole il riferimento
	 * @return a riferimento all'attrezzo cercato, null se l'attrezzo non viene trovato
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}
	
	/**
	 * Rimuove un attrezzo cerca	@Overrideto per nome
	 * @param nomeAttrezzo nome dell'attrezzo che si intende cancellare
	 * @return rimosso true se l'attrezzo è stato rimosso, altrimenti false
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo)!=null;
	}
	
	/**
	 * Verifica se la borsa è vuota
	 * @return true se la borsa è vuota, altrimenti false
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	/**
	 * Resistuisce il peso massimo della borsa
	 * @return pesoMax peso massimo della borsa
	 */
	public int getPesoMax() {
		return this.pesoMax;
	}
	
	/**
	 * Restuisce il peso attuale nella borsa
	 * @return peso il peso attuale nella borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo att : this.attrezzi.values())
				peso += att.getPeso();
		return peso;
	}
	
	/**
	 * Restuisce gli attrezzi di borsa ordinati per nome in un sorted set
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		ComparatorePerNome cn = new ComparatorePerNome();
		SortedSet<Attrezzo> s = new TreeSet<Attrezzo>(cn);
		s.addAll(this.attrezzi.values());
		
		return s;
	}
	
	/**
	 * Restuisce gli attrezzi di borsa ordinati per peso e poi per nome in una lista
	 * Funziona grazie al metodo sort di Collections, che utilizza il comparatore per peso
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> l = new ArrayList<Attrezzo>();
		ComparatorePerPeso cp = new ComparatorePerPeso();
		
		l.addAll(this.attrezzi.values());
		Collections.sort(l, cp);
		
		return l;
	}
	
	/**
	 * Restuisce gli attrezzi di borsa ordinati per peso e poi per nome in un sorted set
	 * Funziona grazie al metodo addAll di TreeSet, ordinato dal comparatore per peso
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		ComparatorePerPeso cp = new ComparatorePerPeso();
		SortedSet<Attrezzo> set = new TreeSet<Attrezzo>(cp);
		
		set.addAll(this.attrezzi.values());
		
		return set;
	}
	
	/**
     * Restuisce una mappa che associa un intero con l'insieme degli attrezzi di tale
     * peso: tutti gli attrezzi dell'insieme, che figura come valore, hanno lo stesso
     * peso, che figura come chiave
     */
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>> map = new HashMap<Integer,Set<Attrezzo>>();
		ComparatorePerNome cn = new ComparatorePerNome();
		
		for(Attrezzo att : this.attrezzi.values()) {
			int peso = att.getPeso();
			
			if(!map.containsKey(peso))
				map.put(peso,new TreeSet<Attrezzo>(cn));
			map.get(peso).add(att);										//aggiunge att al set relativo a peso
		}
		return map;
	}
	
	/**
	 * Richiama il toString di borsa, restituendo una stringa che descrive
	 * il contenuto della borsa NON ordinato
	 */
	public String getDescrizione() {
		return this.toString();
	}
	
	/**
	 * Restuisce una stringa che descrive il contenuto della borsa ordinato per nome
	 */
	public String getDescrizioneOrdinatoPerNome() {
		if(this.isEmpty())
			return "Borsa vuota";
		
		SortedSet<Attrezzo> set = this.getContenutoOrdinatoPerNome();
		Iterator<Attrezzo> it = set.iterator();
		
		StringBuilder s = new StringBuilder();
		s.append("[ ");
		while (it.hasNext()) {
				Attrezzo att = it.next();
				s.append(att.getNome());
				if(it.hasNext())
					s.append(", ");
		}
		s.append(" ]");
		return s.toString();
	}
	
	
	
	/**
	 * Restuisce una stringa che descrive il contenuto della borsa ordinato per peso
	 */
	public String getDescrizioneOrdinatoPerPeso() {
		if(this.isEmpty())
			return "Borsa vuota";
		
		List<Attrezzo> lista = this.getContenutoOrdinatoPerPeso();
		Iterator<Attrezzo> it = lista.iterator();
		
		StringBuilder s = new StringBuilder();
		s.append("{ ");
		while (it.hasNext()) {
				Attrezzo att = it.next();
				s.append(att.getNome());
				if(it.hasNext())
					s.append(", ");
		}
		s.append(" }");
		return s.toString();
	}
	
	/**
	 * Restuisce una stringa che descrive il contenuto della borsa raggruppato per peso
	 */
	public String getDescrizioneRaggruppatoPerPeso() {
		if(this.isEmpty())
			return "Borsa vuota";
		
		Map<Integer,Set<Attrezzo>> map = this.getContenutoRaggruppatoPerPeso();
		Set<Integer> chiavi = map.keySet();
		Iterator<Integer> itChiavi = chiavi.iterator();
		
		StringBuilder s = new StringBuilder();
		
		while (itChiavi.hasNext()) {
			int peso = itChiavi.next();
			s.append("("+peso+", { ");
			
			Iterator<Attrezzo> itAttrezzi= map.get(peso).iterator();
			while(itAttrezzi.hasNext()) {
				Attrezzo att = itAttrezzi.next();
				s.append(att.getNome());
				if(itAttrezzi.hasNext())
					s.append(", ");
			}
			
			s.append(" })");
			if(itChiavi.hasNext())
				s.append(" ; ");
		}
		return s.toString();
	}
	
	/**
	 * Restuisce una stringa che descrive il contenuto della borsa
	 * con anche il peso, ma NON è ordinato
	 */
	public String toString() {
		if(this.isEmpty())
			return "Borsa vuota";
		
		StringBuilder s = new StringBuilder();
		s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
		for (Attrezzo att : this.attrezzi.values())
				s.append(att.toString()+" ");
		return s.toString();
	}
}