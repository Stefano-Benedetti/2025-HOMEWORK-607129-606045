package it.uniroma3.diadia;


import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.comandi.Comando;

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
	
	
	private Partita partita;
	private IO io;

	public DiaDia(IO io) {
		this.partita = new Partita();
		this.io = io;
	}
	
	/**
	 * Avvia il gioco mostrando messaggio di benvenuto e prendendo volta
	 * per volta le istruzioni (con IOConsole).
	 * Ogni istruzione la passa al metodo processaIstruzione() e se questo
	 * ritorna false allora il do-while continua
	 */
	public void gioca() {
		String istruzione;
		this.io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		do		
			istruzione = this.io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione (gli viene passata da gioca()).
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, altrimenti false
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		
		comandoDaEseguire.esegui(this.partita, this.io);
		
		if (this.partita.vinta())
			this.io.mostraMessaggio("Hai vinto!");
		if (this.partita.persa())
			this.io.mostraMessaggio("Hai esaurito i CFU...");
		
		return this.partita.isFinita();
	}   

	

	public static void main(String[] argc) {
		IO io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
	
}