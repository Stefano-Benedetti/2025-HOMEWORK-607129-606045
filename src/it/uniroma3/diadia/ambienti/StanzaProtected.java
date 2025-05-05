package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaProtected {
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;				//il numero di direzioni è limitato a 4 (nord,sud,est,ovest)
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;				//il numero di attrezzi è limitato a 10
	
	private String nome;
	
    protected Attrezzo[] attrezzi;
    protected int numeroAttrezzi;
    
    protected Stanza[] stanzeAdiacenti;
    protected int numeroStanzeAdiacenti;
    
    protected String[] direzioni;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public StanzaProtected(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
        this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
        this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
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
        boolean aggiornato = false;							
    	for(int i=0; i<this.direzioni.length; i++)
        	if (direzione.equals(this.direzioni[i])) {
        		this.stanzeAdiacenti[i] = stanza;
        		aggiornato = true;
        	}
    	if (!aggiornato)
    		if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
    			this.direzioni[numeroStanzeAdiacenti] = direzione;
    			this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
    		    this.numeroStanzeAdiacenti++;
    		}
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
        Stanza stanza = null;
		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
        	if (this.direzioni[i].equals(direzione))
        		stanza = this.stanzeAdiacenti[i];
        return stanza;
	}
	
	/**
	 * Resistuisce il riferimento a un array in cui ci sono le direzioni esistenti
	 * per questa stanza
	 * @return riferimento all'array
	 */
	public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
	    for(int i=0; i<this.numeroStanzeAdiacenti; i++)
	    	direzioni[i] = this.direzioni[i];
	    return direzioni;
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Attrezzo[] getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
			this.attrezzi[this.numeroAttrezzi] = attrezzo;
			this.numeroAttrezzi++;
			return true;
		}
        else {
        	return false;
        }
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (int i=0; i<this.numeroAttrezzi; i++) {
				if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
					attrezzoCercato = this.attrezzi[i];
		}
		return attrezzoCercato;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
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
    
    /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (int i=0; i<this.numeroStanzeAdiacenti; i++)
    		risultato.append(" " + this.direzioni[i]);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (int i=0; i<this.numeroAttrezzi; i++) {
    		risultato.append(this.attrezzi[i].toString()+" ");
    	}
    	return risultato.toString();
    }

}
