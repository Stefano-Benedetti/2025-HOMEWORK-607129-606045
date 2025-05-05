package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/**
 * Classe che gestisce il comando fine settando la partita su finita.
 */
public class ComandoFine implements Comando{
	
	public ComandoFine() {}
	
	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("\nGrazie per aver giocato!");  // si desidera smettere
		partita.setFinita();
	}
	
	@Override
	public void setParametro(String parametro) {
		return;
	}
	
	@Override
	public String getNome() {
		return "fine";
	}
	
	@Override
	public String getParametro() {
		return null;
	}
}
