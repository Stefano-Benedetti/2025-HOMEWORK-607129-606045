package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Classe che gestisce il comando borsa. Mostra gli attrezzi nella
 * borsa nell'ordine specificato.
 */
public class ComandoBorsa extends AbstractComando implements Comando{
	
	private String ordine;
	
	public ComandoBorsa() {}
	
	public ComandoBorsa(String ordine) {
		this.ordine = ordine;
	}
	

	public void esegui(Partita partita, IO io) {
		Borsa borsa=partita.getGiocatore().getBorsa();
		if(this.ordine==null)
			io.mostraMessaggio("\nCome ordino ?\n"+"Tipi di ordinamento: nome, peso, pesi\n");
		else if(this.ordine.equals("nome")) {
			io.mostraMessaggio("\n"+borsa.getDescrizioneOrdinatoPerNome()+"\n");
		}
		else if(this.ordine.equals("peso")) {
			io.mostraMessaggio("\n"+borsa.getDescrizioneOrdinatoPerPeso()+"\n");
		}
		else if(this.ordine.equals("pesi")) {
			io.mostraMessaggio("\n"+borsa.getDescrizioneRaggruppatoPerPeso()+"\n");
		}
		else {
			io.mostraMessaggio("\nQuesto ordinamento non esiste\n"+"Tipi di ordinamento: nome, peso, pesi\n");
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.ordine = parametro;
	}
	

	public String getNome() {
		return "borsa";
	}
	

	public String getParametro() {
		return this.ordine;
	}
	

}
