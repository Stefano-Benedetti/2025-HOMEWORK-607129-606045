package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
*/

public class StanzaProtected {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;				//il numero di direzioni è limitato a 4 (nord,sud,est,ovest)
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;				//il numero di attrezzi è limitato a 10
	
	protected String nome;

	protected Map<String,Attrezzo> attrezzi;
	
	protected Map<String,Stanza> stanzeAdiacenti;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public StanzaProtected(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<>();
        this.attrezzi = new HashMap<String,Attrezzo>();
    }

    /**
     * Imposta una stanza adiacente. 
     * Se trova la direzione richiesta nell'array delle direzioni allora ci imposta, allo
     * stesso indice la stanza adiacente nell'array delle stanze adiacenti.
     * Altrimenti al primo posto libero nell'array delle direzioni imposta la direzione e,
     * allo stesso indice, nell'array delle stanze adiacenti, imposta la stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
    	if(this.stanzeAdiacenti.size()<NUMERO_MASSIMO_DIREZIONI)
    		this.stanzeAdiacenti.put(direzione,stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
		return this.stanzeAdiacenti.get(direzione);
	}
	
	/**
	 * Resistuisce il riferimento a un array in cui ci sono le direzioni esistenti
	 * per questa stanza
	 * @return riferimento all'array
	 */
	public Set<String> getDirezioni() {
		return this.stanzeAdiacenti.keySet();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Map<String,Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {				//se un attrezzo con lo stesso nome già esiste,
        if (this.attrezzi.size() < NUMERO_MASSIMO_ATTREZZI) {	//viene sostituito
        	this.attrezzi.put(attrezzo.getNome(),attrezzo);
			return true;
        }
		return false;
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.get(nomeAttrezzo);
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.remove(nomeAttrezzo)!=null;
	}
	
	public boolean isMagica(){
    	return false;
    }
	
	/**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }
    
    public Map<String,Stanza> getMapStanzeAdiacenti(){
    	return this.stanzeAdiacenti;
    }
    
    /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (String direz : this.getDirezioni())
    		risultato.append(" " + direz);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo att : this.attrezzi.values()) {
    		risultato.append(att.toString()+" ");
    	}
    	return risultato.toString();
    }
    
    @Override
	public boolean equals(Object obj) {
		Stanza stanza = (Stanza)obj;
		return this.getNome().equals(stanza.getNome());
	}

}
