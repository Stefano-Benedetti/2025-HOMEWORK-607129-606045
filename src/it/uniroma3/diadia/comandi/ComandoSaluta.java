package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.personaggi.*;

public class ComandoSaluta extends AbstractComando implements Comando {
	
	private static final String MESSAGGIO_CHI = "Qui non c'è nessuno...";
	private String messaggio;
	

	public void esegui(Partita partita, IO io) {
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio!=null) {
			this.messaggio = personaggio.saluta();
			io.mostraMessaggio("\n"+this.messaggio+"\n");
		} 
		else 
			io.mostraMessaggio("\n"+MESSAGGIO_CHI+"\n");
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
	
	public String getNome() {
		return "saluta";
	}
	
	public String getParametro() {
		return null;
	}
}
