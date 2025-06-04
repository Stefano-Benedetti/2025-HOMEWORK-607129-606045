package it.uniroma3.diadia;


//import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.Comando;
import java.io.*;
import java.util.Scanner;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 */

public class DiaDia {

	private static String MESSAGGIO_BENVENUTO = ConfiguraProprieta.getMessaggioBenvenuto();	
	
	private Partita partita;
	private IO io;

	public DiaDia(IO io) throws IOException{
		this.partita = new Partita();
		this.io = io;
	}
	
	public DiaDia(IO io, String nomeFile) throws IOException{
		this.partita = new Partita(nomeFile);
		this.io = io;
	}
	
	public DiaDia(Labirinto labirintoStyled, IO io) throws IOException{
		this.partita = new Partita(labirintoStyled);
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
		//FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		FabbricaDiComandiRiflessiva factory = new FabbricaDiComandiRiflessiva();
		comandoDaEseguire = factory.costruisciComando(istruzione);
		
		comandoDaEseguire.esegui(this.partita, this.io);
		
		if (this.partita.vinta())
			this.io.mostraMessaggio("Hai vinto!");
		if (this.partita.persa())
			this.io.mostraMessaggio("Hai esaurito i CFU...");
		
		return this.partita.isFinita();
	}   

	

	public static void main(String[] argc) throws IOException{
		try (Scanner scannerDiLinee = new Scanner(System.in)) {
			IO io = new IOConsole(scannerDiLinee);
			DiaDia gioco = new DiaDia(io, "Labirinto.txt");
			gioco.gioca();
		}	
	}
	
}