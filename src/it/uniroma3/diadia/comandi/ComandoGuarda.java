package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


/**
 * Classe che gestisce il comando guarda dando le informazioni sulla stanza corrente e
 * sullo stato della partita.
 */
public class ComandoGuarda implements Comando{
		
	public ComandoGuarda() {}
	
	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("\n"+partita.getStanzaCorrente().toString());
		io.mostraMessaggio("CFU: "+partita.getGiocatore().getCfu());
		io.mostraMessaggio(partita.getGiocatore().getBorsa().toString()+"\n");	
	}
	
	@Override
	public void setParametro(String parametro) {
		return;
	}
	
	@Override
	public String getNome() {
		return "guarda";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
