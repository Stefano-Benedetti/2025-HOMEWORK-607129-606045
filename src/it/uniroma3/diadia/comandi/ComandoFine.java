package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/**
 * Classe che gestisce il comando fine settando la partita su finita.
 */
public class ComandoFine extends AbstractComando implements Comando{
	

	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("\nGrazie per aver giocato!\n");  // si desidera smettere
		partita.setFinita();
	}
	

	public String getNome() {
		return "fine";
	}
	

	public String getParametro() {
		return null;
	}
}
