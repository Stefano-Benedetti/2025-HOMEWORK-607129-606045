package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/**
 * Classe che gestice il comando aiuto stampando i possibili comandi.
 */
public class ComandoAiuto implements Comando{
	static final private String[] elencoComandi= {"aiuto", "guarda", "vai", "prendi", "posa", "fine"};
	
	public ComandoAiuto() {}
	
	@Override
	public void esegui(Partita partita, IO io) {
		io.mostraMessaggio("\nELENCO COMANDI:");
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
		io.mostraMessaggio("");
	}
	
	@Override
	public void setParametro(String parametro) {
		return;
	}
	
	@Override
	public String getNome() {
		return "aiuto";
	}
	
	@Override
	public String getParametro() {
		return null;
	}

}
