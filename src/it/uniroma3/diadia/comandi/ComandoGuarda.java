package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


/**
 * Classe che gestisce il comando guarda dando le informazioni sulla stanza corrente e
 * sullo stato della partita.
 */
public class ComandoGuarda extends AbstractComando implements Comando{
	

	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("\n"+partita.getStanzaCorrente().getDescrizione());
		io.mostraMessaggio("CFU: "+partita.getGiocatore().getCfu());
		io.mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione()+"\n");	
	}
	

	public String getNome() {
		return "guarda";
	}
	

	public String getParametro() {
		return null;
	}
}
