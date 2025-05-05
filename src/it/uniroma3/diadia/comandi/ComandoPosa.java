package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;


/**
 * Classe che gestisce il comando posa.
 * Posa l'attrezzo nella stanza togliendolo dalla borsa del giocatore
 */
public class ComandoPosa implements Comando{
	private String nomeAttrezzo;
	
	public ComandoPosa() {}
	
	public ComandoPosa(String nomeAttrezzo) {
		this.nomeAttrezzo=nomeAttrezzo;
	}
	
	@Override
	public void esegui(Partita partita, IO io) {
		if(nomeAttrezzo==null)
			io.mostraMessaggio("Poso cosa ?");
		else {
			Attrezzo temp = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			boolean aggiunto=false;						//aggiunto (alla stanza)
			if(temp!=null) {
				aggiunto=partita.getStanzaCorrente().addAttrezzo(temp);
				if(aggiunto) {
					partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
					io.mostraMessaggio("\nHai posato l'attrezzo '"+nomeAttrezzo+"' nella stanza.\n");
				}
				else
					io.mostraMessaggio("\nImpossibile posare l'attrezzo '"+nomeAttrezzo+"' nella stanza (è piena).\n");
			}
			else {
				io.mostraMessaggio("\nAttrezzo '"+nomeAttrezzo+"' non presente nella borsa.");
				io.mostraMessaggio(partita.getGiocatore().getBorsa().toString()+"\n");
			}
		}	
	}
	
	@Override
	public void setParametro(String parametro) {
		nomeAttrezzo=parametro;
	}
	
	@Override
	public String getNome() {
		return "posa";
	}
	
	@Override
	public String getParametro() {
		return nomeAttrezzo;
	}
	
}
