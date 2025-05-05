package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.IO;

/**
 * Classe che gestisce il comando vai.
 * Cerca di andare in una direzione. Se c'è una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */
public class ComandoVai implements Comando{
	private String direzione;

	public ComandoVai() {}
	
	public ComandoVai(String direzione) {
		this.direzione=direzione;
	}

	/**
	* esecuzione del comando
	*/
	@Override
	public void esegui(Partita partita, IO io) {
		
		if(direzione==null) {
			io.mostraMessaggio("\nDove vuoi andare ?");
			io.mostraMessaggio("Direzioni possibili:");
			for(String temp : partita.getStanzaCorrente().getDirezioni())
				io.mostraMessaggio(" "+temp);
			io.mostraMessaggio("\n");
			return;
		}
		
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		
		if (prossimaStanza == null) {
			io.mostraMessaggio("\nDirezione inesistente.");
			io.mostraMessaggio("Direzioni possibili:");
			for(String temp : partita.getStanzaCorrente().getDirezioni())
				io.mostraMessaggio(" "+temp);
			io.mostraMessaggio("\n");
			return;
		}
		
		partita.setStanzaCorrente(prossimaStanza);
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		io.mostraMessaggio("\n"+partita.getStanzaCorrente().toString()+"\n");				
			
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione=parametro;
	}
	
	@Override
	public String getNome() {
		return "vai";
	}
	
	@Override
	public String getParametro() {
		return direzione;
	}
		
}
