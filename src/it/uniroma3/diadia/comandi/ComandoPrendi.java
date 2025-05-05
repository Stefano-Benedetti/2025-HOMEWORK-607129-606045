package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;


/**
 * Classe che gestisce il comando prendi. Prende l'attrezzo dalla stanza e 
 * lo mette nella borsa del giocatore.
 */
public class ComandoPrendi implements Comando{
	private String nomeAttrezzo;
	
	public ComandoPrendi() {}
	
	public ComandoPrendi(String nomeAttrezzo) {
		this.nomeAttrezzo=nomeAttrezzo;
	}
	
	@Override
	public void esegui(Partita partita, IO io) {
		if(nomeAttrezzo==null)
			io.mostraMessaggio("\nPrendo cosa ?\n");
		else {
			Attrezzo temp = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			boolean aggiunto=false;								//aggiunto (al giocatore)
			if(temp!=null) {
				aggiunto=partita.getGiocatore().getBorsa().addAttrezzo(temp);
				if(aggiunto) {
					partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
					io.mostraMessaggio("\nHai aggiunto l'attrezzo '"+nomeAttrezzo+"' alla borsa.\n");
				}
				else
					io.mostraMessaggio("\nImpossibile aggiungere l'attrezzo '"+nomeAttrezzo+"' alla borsa (è piena).\n");
			}
			else {
				io.mostraMessaggio("\nAttrezzo '"+nomeAttrezzo+"' non presente nella stanza.");
				io.mostraMessaggio(partita.getStanzaCorrente().toString()+"\n");
			}
		}	
	}
	
	@Override
	public void setParametro(String parametro) {
		nomeAttrezzo=parametro;
	}
	
	@Override
	public String getNome() {
		return "prendi";
	}
	
	@Override
	public String getParametro() {
		return nomeAttrezzo;
	}
	
}
