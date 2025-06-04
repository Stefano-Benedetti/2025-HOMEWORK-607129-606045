package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


/**
 * Classe che gestisce un comando non valido.
 */
public class ComandoNonValido extends AbstractComando implements Comando{
	

	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("\nIl comando inserito non è valido\n");	
	}
	

	public String getNome() {
		return "ComandoNonValido";
	}
	

	public String getParametro() {
		return null;
	}

}
