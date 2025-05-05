package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


/**
 * Classe che gestisce un comando non valido.
 */
public class ComandoNonValido implements Comando{
	
	public ComandoNonValido() {}
	
	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("Il comando inserito non è valido");	
	}
	
	@Override
	public void setParametro(String parametro) {
		return;
	}
	
	@Override
	public String getNome() {
		return "ComandoNonValido";
	}
	
	@Override
	public String getParametro() {
		return null;
	}

}
