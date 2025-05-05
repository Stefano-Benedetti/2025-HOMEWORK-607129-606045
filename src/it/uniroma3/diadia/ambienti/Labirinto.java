package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Questa classe modella la mappa di gioco creando e collegando
 * tra loro le stanze, imposta la stanza iniziale e quella finale
 * e crea gli attrezzi all'interno delle stanze.
 * 
 * @version 1.8
 */

public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;
	
	public Labirinto() {
		creaStanze();
	}
	
	/**
     * Crea tutte le stanze e le porte di collegamento
     */
    private void creaStanze() {

		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);					//				 biblioteca
		atrio.impostaStanzaAdiacente("sud", aulaN10);					//	 				 |
		atrio.impostaStanzaAdiacente("ovest", laboratorio);				//---laboratorio---atrio---aulaN11---
		aulaN11.impostaStanzaAdiacente("est", laboratorio);				//			|		 |		 |
		aulaN11.impostaStanzaAdiacente("ovest", atrio);					//			------aulaN10-----
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
        this.stanzaIniziale = atrio;
		this.stanzaFinale = biblioteca;
    }
    
    
    /**
	 * Restituisce il riferimento alla stanzaFinale
	 * @return riferimento alla stanza finale
	 */
    public Stanza getStanzaFinale() {
		return this.stanzaFinale;
	}
    
    /**
	 * Restituisce il riferimento alla stanzaIniziale
	 * @return riferimento alla stanza iniziale
	 */
    public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
}
