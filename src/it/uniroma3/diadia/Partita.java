package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 */

public class Partita {

	private Stanza stanzaCorrente;
	private boolean finita;
	private Labirinto labirinto;
	private Giocatore giocatore;
	
	public Partita(){
		this.labirinto = new Labirinto();
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.giocatore = new Giocatore();
		this.finita = false;
	}
	
	public Partita(String file){
		this.labirinto = new Labirinto(file);
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.giocatore = new Giocatore();
		this.finita = false;
	}
    
	public Partita(Labirinto labirintoStyled){
		this.labirinto = labirintoStyled;
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.giocatore = new Giocatore();
		this.finita = false;
	}

	public void setLabirinto(Labirinto labirintoStyled) {
		this.labirinto = labirintoStyled;
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente() == this.labirinto.getStanzaFinale();
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata persa
	 * @return vero se partita persa
	 */
	public boolean persa() {
		return this.giocatore.getCfu() == 0;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return this.finita || this.vinta() || this.persa();
	}

	/**
	 * Imposta la partita come finita
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
}
