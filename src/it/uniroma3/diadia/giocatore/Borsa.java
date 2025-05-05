package it.uniroma3.diadia.giocatore;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella la borsa di un giocatore.
 *
 * @version 1.8
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;				//la borsa ha un peso massimo di 10 kg
	public final static int NUMERO_MASSIMO_ATTREZZI = 10;				//il numero di attrezzi è limitato a 10
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
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
		this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
		this.numeroAttrezzi = 0;
	}
	
	/**
	 * Aggiunge un attrezzo all'interno della borsa
	 * @param attrezzo riferimento all'attrezzo che si vuole aggiungere
	 * @return true se l'operazione è andata a buon fine, altrimenti false
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI && attrezzo.getPeso()+this.getPeso()<=this.pesoMax) {
			attrezzi[this.numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
        }
        else {
        	return false;
        }
    }
	
	/**
	 * Verifica se la borsa contiene un attrezzo cercato per nome
	 * @param nomeAttrezzo nome dell'attrezzo che si vuole cercare
	 * @return true se l'attrezzo cercato è stato trovato, altrimenti false
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/**
	 * Restuisce il riferimento all'attrezzo cercato per nome
	 * @param nomeAttrezzo nome dell'attrezzo di cui si vuole il riferimento
	 * @return a riferimento all'attrezzo cercato, null se l'attrezzo non viene trovato
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (int i=0; i<this.numeroAttrezzi; i++) {
			if (attrezzi[i].getNome().equals(nomeAttrezzo))
				attrezzoCercato = attrezzi[i];
		}
		return attrezzoCercato;
	}
	
	/**
	 * Rimuove un attrezzo cercato per nome
	 * @param nomeAttrezzo nome dell'attrezzo che si intende cancellare
	 * @return rimosso true se l'attrezzo è stato rimosso, altrimenti false
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {
		boolean rimosso = false;
		for (int i=0; i<this.numeroAttrezzi; i++) {
			if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
				this.attrezzi[i] = null;
				rimosso = true;
				for(int j=i+1; j<this.numeroAttrezzi; j++) {		//scala gli attrezzi indietro di una posizione
					this.attrezzi[j-1] = this.attrezzi[j];
				}
				this.numeroAttrezzi--;
				break;
			}
		}
		return rimosso;
	}
	
	/**
	 * Verifica se la borsa è vuota
	 * @return true se la borsa è vuota, altrimenti false
	 */
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	/**
	 * Resistuisce il peso massimo della borsa
	 * @return pesoMax peso massimo della borsa
	 */
	public int getPesoMax() {
		return pesoMax;
	}
	
	/**
	 * Restuisce il peso attuale nella borsa
	 * @return peso il peso attuale nella borsa
	 */
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
				peso += this.attrezzi[i].getPeso();
		return peso;
	}
	
	/**
	 * Restuisce una stringa che descrive il contenuto della borsa con anche il peso
	 * @return stringa che descrive il contenuto della borsa con anche il peso
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<numeroAttrezzi; i++)
					s.append(this.attrezzi[i].toString()+" ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}
}