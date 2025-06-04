package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComandoRegala extends AbstractComando implements Comando{
	
	private static final String MESSAGGIO_NON_TROVATO = "Non possiedi questo attrezzo!";
	private static final String MESSAGGIO_COSA = "Regalo cosa?";
	private static final String MESSAGGIO_CHI = "Qui non c'è nessuno...";
	private String messaggio;
	private String nomeAttrezzo;
	

	public void esegui(Partita partita, IO io) {
		if(nomeAttrezzo==null)
			io.mostraMessaggio("\n"+MESSAGGIO_COSA+"\n");
		else {
			if(!partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) 
				io.mostraMessaggio("\n"+MESSAGGIO_NON_TROVATO+"\n");
			else {
				AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
				if(personaggio!=null) {
					
					Attrezzo att = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
					if(partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo)) {
						this.messaggio = personaggio.riceviRegalo(att, partita);
						io.mostraMessaggio("\n"+this.messaggio+"\n");
					}
					
				}
				else
					io.mostraMessaggio("\n"+MESSAGGIO_CHI+"\n");
			}
		
		}
	}
	
	public String getMessaggio() {
		return this.messaggio;
	}
	
	public String getNome() {
		return "regala";
	}
	
	public String getParametro() {
		return this.nomeAttrezzo;
	}
	
	@Override
	public void setParametro(String nomeAttrezzo) {
		this.nomeAttrezzo = nomeAttrezzo;
	}

}
