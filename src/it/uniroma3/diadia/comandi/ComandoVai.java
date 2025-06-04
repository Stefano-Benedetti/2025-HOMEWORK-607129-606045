package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Direzione;

/**
 * Classe che gestisce il comando vai.
 * Cerca di andare in una direzione. Se c'è una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */
public class ComandoVai extends AbstractComando implements Comando{
	private String direzione;

	public ComandoVai() {}
	
	public ComandoVai(String direzione) {
		this.direzione = direzione;
	}

	/**
	* esecuzione del comando
	*/

	public void esegui(Partita partita, IO io) {
		
		if(this.direzione==null) {
			io.mostraMessaggio("\nDove vuoi andare ?");
			io.mostraMessaggioNoInvio("Direzioni possibili:");
			for(Direzione temp : partita.getStanzaCorrente().getDirezioni())
				io.mostraMessaggioNoInvio(" "+temp);
			io.mostraMessaggio("\n");
			return;
		}
		
		Stanza prossimaStanza = null;
		Direzione dir;

		try {
			dir = Direzione.valueOf(this.direzione);
		} catch(IllegalArgumentException e) {
			io.mostraMessaggio("\nDirezione inesistente");
			io.mostraMessaggioNoInvio("Direzioni possibili:");
			for(Direzione temp : partita.getStanzaCorrente().getDirezioni())
				io.mostraMessaggioNoInvio(" "+temp);
			io.mostraMessaggio("\n");
			return;
		}
		
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(dir);
		
		if(prossimaStanza!=null) {
			partita.setStanzaCorrente(prossimaStanza);
			partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
			io.mostraMessaggio("\n"+partita.getStanzaCorrente().getDescrizione()+"\n");
		}
		else {
			io.mostraMessaggio("\nDirezione assente");
			io.mostraMessaggioNoInvio("Direzioni possibili:");
			for(Direzione temp : partita.getStanzaCorrente().getDirezioni())
				io.mostraMessaggioNoInvio(" "+temp);
			io.mostraMessaggio("\n");
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
	

	public String getNome() {
		return "vai";
	}
	

	public String getParametro() {
		return this.direzione.toString();
	}
		
}
