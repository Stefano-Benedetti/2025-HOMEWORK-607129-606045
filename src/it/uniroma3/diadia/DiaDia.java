package it.uniroma3.diadia;



import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version 1.8
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Università, ma oggi è diversa dal solito...\n" +
			"Meglio andare al più presto in biblioteca a studiare. Ma dov'è?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissà!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.\n";
	
	static final private String[] elencoComandi = {"aiuto", "info", "vai", "prendi", "posa", "fine"};
	
	private Partita partita;
	private IOConsole IO;

	public DiaDia() {
		this.partita = new Partita();
		IO = new IOConsole();
	}
	
	/**
	 * Avvia il gioco mostrando messaggio di benvenuto e prendendo volta
	 * per volta le istruzioni (con IOConsole).
	 * Ogni istruzione la passa al metodo processaIstruzione() e se questo
	 * ritorna false allora il do-while continua
	 */
	public void gioca() {
		String istruzione;
		IO.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = IO.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione (gli viene passata da gioca()).
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, altrimenti false
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		boolean finita = false;
		
		if (comandoDaEseguire.getNome()!=null) {				
			if (comandoDaEseguire.getNome().equals("fine")) {
				this.fine(); 
				return true;
			} else if (comandoDaEseguire.getNome().equals("vai"))		//se l'istr. è vai parte il metodo vai()
				this.vai(comandoDaEseguire.getParametro());
			
			else if (comandoDaEseguire.getNome().equals("aiuto"))		//se l'istr. è aiuto parte il metodo aiuto()
				this.aiuto();
			
			else if(comandoDaEseguire.getNome().equals("prendi"))		//se l'istr. è prendi parte il metodo prendi()
				this.prendi(comandoDaEseguire.getParametro());
			else if(comandoDaEseguire.getNome().equals("posa"))			//se l'istr. è posa parte il metodo posa()
				this.posa(comandoDaEseguire.getParametro());
			
			else if(comandoDaEseguire.getNome().equals("info"))
				this.info();
			
			else
				IO.mostraMessaggio("\nComando sconosciuto.\n");
		}
		if (this.partita.vinta()) {									//inoltre controlla se la partita è vinta
			IO.mostraMessaggio("Hai vinto!");						
			finita = true;
		}
		else if(this.partita.persa()) {								//oppure se è persa
			IO.mostraMessaggio("Hai perso, CFU esauriti!");
			finita = true;
		}
		return finita;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		IO.mostraMessaggio("\nELENCO COMANDI:");
		for(int i=0; i< elencoComandi.length; i++) 
			IO.mostraMessaggio(elencoComandi[i]+" ");
		IO.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			IO.mostraMessaggio("\nDove vuoi andare ?");
			IO.mostraMessaggioNoInvio("Direzioni possibili:");
			for(String temp : this.partita.getStanzaCorrente().getDirezioni())
	    			IO.mostraMessaggioNoInvio(" "+temp);
			IO.mostraMessaggio("\n");
		}
		else {
			Stanza prossimaStanza = null;
			prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
			if (prossimaStanza == null) {
				IO.mostraMessaggio("\nDirezione inesistente.");
				IO.mostraMessaggioNoInvio("Direzioni possibili:");
				for(String temp : this.partita.getStanzaCorrente().getDirezioni())
		    			IO.mostraMessaggioNoInvio(" "+temp);
				IO.mostraMessaggio("\n");
			}
			else {
				this.partita.setStanzaCorrente(prossimaStanza);
				int cfu = this.partita.getGiocatore().getCfu();
				cfu--;
				this.partita.getGiocatore().setCfu(cfu);
				IO.mostraMessaggio("\n"+partita.getStanzaCorrente().toString()+"\n");				
			}
		}
	}
	
	/**
	 * Prende l'attrezzo dalla stanza e lo mette nella borsa del giocatore
	 * @param nomeAttrezzo nome dell'attrezzo che si intende prendere dalla stanza
	 */
	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo==null)
			IO.mostraMessaggio("\nPrendo cosa ?\n");
		else {
			Attrezzo temp = partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
			boolean aggiunto=false;								//aggiunto (al giocatore)
			if(temp!=null) {
				aggiunto=partita.getGiocatore().getBorsa().addAttrezzo(temp);
				if(aggiunto) {
					partita.getStanzaCorrente().removeAttrezzo(nomeAttrezzo);
					IO.mostraMessaggio("\nHai aggiunto l'attrezzo '"+nomeAttrezzo+"' alla borsa.\n");
				}
				else
					IO.mostraMessaggio("\nImpossibile aggiungere l'attrezzo '"+nomeAttrezzo+"' alla borsa (è piena).\n");
			}
			else {
				IO.mostraMessaggio("\nAttrezzo '"+nomeAttrezzo+"' non presente nella stanza.");
				IO.mostraMessaggio(partita.getStanzaCorrente().toString()+"\n");
			}
		}	
	}
	
	/**
	 * Posa l'attrezzo nella stanza togliendolo dalla borsa del giocatore
	 * @param nomeAttrezzo nome dell'attrezzo che si intende prendere dalla stanza
	 */
	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo==null)
			IO.mostraMessaggio("Poso cosa ?");
		else {
			Attrezzo temp = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
			boolean aggiunto=false;						//aggiunto (alla stanza)
			if(temp!=null) {
				aggiunto=partita.getStanzaCorrente().addAttrezzo(temp);
				if(aggiunto) {
					partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
					IO.mostraMessaggio("\nHai posato l'attrezzo '"+nomeAttrezzo+"' nella stanza.\n");
				}
				else
					IO.mostraMessaggio("\nImpossibile posare l'attrezzo '"+nomeAttrezzo+"' nella stanza (è piena).\n");
			}
			else {
				IO.mostraMessaggio("\nAttrezzo '"+nomeAttrezzo+"' non presente nella borsa.");
				IO.mostraMessaggio(partita.getGiocatore().getBorsa().toString()+"\n");
			}
		}	
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		IO.mostraMessaggio("\nGrazie per aver giocato!");  // si desidera smettere
	}
	
	/**
	 * Mostra la stanza corrente (con la descrizione), i cfu posseduti e
	 * le info riguardo la borsa
	 */
	private void info() {
		IO.mostraMessaggio("\n"+this.partita.getStanzaCorrente().toString());
		IO.mostraMessaggio("CFU: "+this.partita.getGiocatore().getCfu());
		IO.mostraMessaggio(this.partita.getGiocatore().getBorsa().toString()+"\n");
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
	
}