package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IO;


/**
 * Classe che gestisce il comando posa.
 * Posa l'attrezzo nella stanza togliendolo dalla borsa del giocatore
 */
public class ComandoPosa extends AbstractComando implements Comando{
	private String nomeAttrezzo;
	
	public ComandoPosa() {}
	
	public ComandoPosa(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;
	}
	

	public void esegui(Partita partita, IO io) {
		if(this.nomeAttrezzo==null)
			io.mostraMessaggio("\nPoso cosa ?\n");
		else {
			Attrezzo temp = partita.getGiocatore().getBorsa().getAttrezzo(this.nomeAttrezzo);
			boolean aggiunto = false;						//aggiunto (alla stanza)
			if(temp!=null) {
				aggiunto = partita.getStanzaCorrente().addAttrezzo(temp);
				if(aggiunto) {
					partita.getGiocatore().getBorsa().removeAttrezzo(this.nomeAttrezzo);
					io.mostraMessaggio("\nHai posato l'attrezzo '"+this.nomeAttrezzo+"' nella stanza\n");
				}
				else
					io.mostraMessaggio("\nImpossibile posare l'attrezzo '"+this.nomeAttrezzo+"' nella stanza (è piena)\n");
			}
			else {
				io.mostraMessaggio("\nAttrezzo '"+this.nomeAttrezzo+"' non presente nella borsa");
				io.mostraMessaggio(partita.getGiocatore().getBorsa().getDescrizione()+"\n");
			}
		}	
	}
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	

	public String getNome() {
		return "posa";
	}
	

	public String getParametro() {
		return this.nomeAttrezzo;
	}
	
}
