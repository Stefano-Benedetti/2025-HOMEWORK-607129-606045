package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;
import java.io.FileNotFoundException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import static it.uniroma3.diadia.ambienti.Direzione.*;
import java.util.Map;
import java.util.HashMap;
import it.uniroma3.diadia.personaggi.*;

/**
 * Questa classe modella la mappa di gioco creando e collegando
 * tra loro le stanze, imposta la stanza iniziale e quella finale
 * e crea gli attrezzi all'interno delle stanze.
 *
 */

public class Labirinto {
	
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;
	
	public Labirinto() {
		creaStanze();
	}
	
	public Labirinto(String nomeFile) {
		try {
			CaricatoreLabirinto c = new CaricatoreLabirinto(nomeFile);
			if(c.carica()) {
				this.stanzaIniziale = c.getStanzaIniziale();
				this.stanzaFinale = c.getStanzaFinale();
			}
			else
				creaStanze();				
		}catch (FileNotFoundException e) {
			creaStanze();
		}catch (FormatoFileNonValidoException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
     * Crea tutte le stanze e le porte di collegamento
     */
    public void creaStanze() {

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
		atrio.impostaStanzaAdiacente(nord, biblioteca);
		atrio.impostaStanzaAdiacente(est, aulaN11);					//				 biblioteca
		atrio.impostaStanzaAdiacente(sud, aulaN10);					//	 				 |
		atrio.impostaStanzaAdiacente(ovest, laboratorio);				//---laboratorio---atrio---aulaN11---
		aulaN11.impostaStanzaAdiacente(est, laboratorio);				//			|		 |		 |
		aulaN11.impostaStanzaAdiacente(ovest, atrio);					//			------aulaN10-----
		aulaN10.impostaStanzaAdiacente(nord, atrio);
		aulaN10.impostaStanzaAdiacente(est, aulaN11);
		aulaN10.impostaStanzaAdiacente(ovest, laboratorio);
		laboratorio.impostaStanzaAdiacente(est, atrio);
		laboratorio.impostaStanzaAdiacente(ovest, aulaN11);
		biblioteca.impostaStanzaAdiacente(sud, atrio);

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
    
    public void setStanzaIniziale(Stanza iniziale) {
		this.stanzaIniziale = iniziale;
	}
    
    public void setStanzaFinale(Stanza finale) {
		this.stanzaFinale = finale;
	}
    
    public static LabirintoBuilder newBuilder(){
    	return new LabirintoBuilder();
    }
    
    public static class LabirintoBuilder extends Labirinto{
    	
    	Map<String, Stanza> stanze;
    	Stanza ultimaStanzaAggiunta;
    	
    	private LabirintoBuilder() {
    		this.stanze = new HashMap<String, Stanza>();
    		ultimaStanzaAggiunta = null;
    	}
    	
    	public LabirintoBuilder addStanza(String nomeStanza) {
    		Stanza stanza = new Stanza(nomeStanza);
    		this.stanze.put(nomeStanza, stanza);
    		this.ultimaStanzaAggiunta = stanza;
    		return this;
    	}
    	
    	public LabirintoBuilder addStanzaIniziale(String nomeStanzaIniziale) {
    		Stanza in = new Stanza(nomeStanzaIniziale);
    		super.setStanzaIniziale(in);
    		this.stanze.put(nomeStanzaIniziale, in);
    		this.ultimaStanzaAggiunta = in;
    		return this;
    	}
    	
    	public LabirintoBuilder addStanzaFinale(String nomeStanzaFinale) {
    		Stanza fin = new Stanza(nomeStanzaFinale);
    		super.setStanzaFinale(fin);
    		this.stanze.put(nomeStanzaFinale, fin);
    		this.ultimaStanzaAggiunta = fin;
    		return this;
    	}
    	
    	public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica) {
    		StanzaMagica stanzaMag = new StanzaMagica(nomeStanzaMagica);
    		this.stanze.put(nomeStanzaMagica, stanzaMag);
    		this.ultimaStanzaAggiunta = stanzaMag;
    		return this;
    	}
    	//overloading di addStanzaMagica
    	public LabirintoBuilder addStanzaMagica(String nomeStanzaMagica, int SogliaMagica) {
    		StanzaMagica stanzaMag = new StanzaMagica(nomeStanzaMagica,SogliaMagica);
    		this.stanze.put(nomeStanzaMagica, stanzaMag);
    		this.ultimaStanzaAggiunta = stanzaMag;
    		return this;
    	}
    	
    	public LabirintoBuilder addStanzaBloccata(String nomeStanza, Direzione direzBloccata, String chiave) {
    		StanzaBloccata stanzaBloc = new StanzaBloccata(nomeStanza, direzBloccata, chiave);
    		this.stanze.put(nomeStanza, stanzaBloc);
    		this.ultimaStanzaAggiunta = stanzaBloc;
    		return this;
    	}
    	
    	public LabirintoBuilder addStanzaBuia(String nomeStanza, String AttLuminoso) {
    		StanzaBuia stanzaBuia = new StanzaBuia(nomeStanza, AttLuminoso);
    		this.stanze.put(nomeStanza, stanzaBuia);
    		this.ultimaStanzaAggiunta = stanzaBuia;
    		return this;
    	}
    	
    	public LabirintoBuilder addAttrezzo(String nome, int peso) {
    		this.ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nome, peso));
    		return this;
    	}
    	
    	//Overloading di addAttrezzo
    	public LabirintoBuilder addAttrezzo(String nome, int peso, String StanzaTarget) {
    		this.stanze.get(StanzaTarget).addAttrezzo(new Attrezzo(nome, peso));
    		return this;
    	}
    	
    	public LabirintoBuilder setPersonaggio(AbstractPersonaggio p) {
    		this.ultimaStanzaAggiunta.setPersonaggio(p);
    		return this;
    	}
    	
    	//Overloading di setPersonaggio
    	public LabirintoBuilder setPersonaggio(AbstractPersonaggio p, String StanzaTarget) {
    		this.stanze.get(StanzaTarget).setPersonaggio(p);
    		return this;
    	}
    	
    	public LabirintoBuilder addAdiacenza(String nomeIn, String nomeFin, Direzione direzione) {
    		Stanza in = this.stanze.get(nomeIn);
    		Stanza fin = this.stanze.get(nomeFin);
    		in.impostaStanzaAdiacente(direzione, fin);
    		return this;
    	}
    	
    	public Map<String, Stanza> getListaStanze(){
    		return this.stanze;
    	}
    	
    	public LabirintoBuilder getLabirinto() {
    		return this;
    	}
    }
}
