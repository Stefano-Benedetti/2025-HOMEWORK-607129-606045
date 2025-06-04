package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;

/**
 * Classe che gestice il comando aiuto stampando i possibili comandi.
 */
public class ComandoAiuto extends AbstractComando implements Comando{
	
	static final private String[] elencoComandi= {"aiuto", "guarda", "borsa", "vai", "prendi", "posa", "fine",
			"interagisci", "saluta"};
	
	
	public void esegui(Partita partita, IO io) {		
		io.mostraMessaggioNoInvio("\nELENCO COMANDI: ");
		for(int i=0; i< elencoComandi.length; i++) {
			io.mostraMessaggioNoInvio(elencoComandi[i]);
			if(i!=elencoComandi.length-1)
				io.mostraMessaggioNoInvio(", ");
		}
		io.mostraMessaggio("\n");
	}	

	public String getNome() {
		return "aiuto";
	}
	
	public String getParametro() {
		return null;
	}

}
