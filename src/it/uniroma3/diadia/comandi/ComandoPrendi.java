package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;


/**
 * Classe che gestisce il comando prendi. Prende l'attrezzo dalla stanza e 
 * lo mette nella borsa del giocatore.
 */
public class ComandoPrendi extends AbstractComando implements Comando{
	private String nomeAttrezzo;
	
	public ComandoPrendi() {}
	
	public ComandoPrendi(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;
	}
	

	public void esegui(Partita partita, IO io) {
		if(this.nomeAttrezzo==null)
			io.mostraMessaggio("\nPrendo cosa ?\n");
		else {
			Attrezzo temp = partita.getStanzaCorrente().getAttrezzo(this.nomeAttrezzo);
			boolean aggiunto = false;								//aggiunto (al giocatore)
			if(temp!=null) {
				aggiunto = partita.getGiocatore().getBorsa().addAttrezzo(temp);
				if(aggiunto) {
					partita.getStanzaCorrente().removeAttrezzo(this.nomeAttrezzo);
					io.mostraMessaggio("\nHai aggiunto l'attrezzo '"+this.nomeAttrezzo+"' alla borsa\n");
				}
				else
					io.mostraMessaggio("\nImpossibile aggiungere l'attrezzo '"+this.nomeAttrezzo+"' alla borsa (è piena)\n");
			}
			else {
				io.mostraMessaggio("\nAttrezzo '"+this.nomeAttrezzo+"' non presente nella stanza");
					io.mostraMessaggioNoInvio("Attrezzi presenti nella stanza: ");
					for (Attrezzo att : partita.getStanzaCorrente().getAttrezzi().values()) {
						io.mostraMessaggioNoInvio(att.toString()+" ");
					}
				io.mostraMessaggio("\n");
			}
		}	
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	

	public String getNome() {
		return "prendi";
	}
	

	public String getParametro() {
		return this.nomeAttrezzo;
	}
	
}
