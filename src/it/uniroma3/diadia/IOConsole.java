package it.uniroma3.diadia;
import java.util.Scanner;

/**
 * Questa classe gestisce gli input e output.
 * 
 * @version 1.8
 */

public class IOConsole {
	
	/**
	 * Mostra in output la stringa passata
	 * @param msg stringa da mostrare in output
	 */
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	/**
	 * Mostra in output la stringa passata, ma senza l'invio
	 * @param msg stringa da mostrare in output
	 */
	public void mostraMessaggioNoInvio(String msg) {
		System.out.print(msg);
	}
	
	/**
	 * Legge una riga in input
	 * @return riga stringa letta
	 */
	public String leggiRiga() {
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		//scannerDiLinee.close();							ci ritorneremo
		return riga;
	}
}