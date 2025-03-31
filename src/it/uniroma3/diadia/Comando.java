package it.uniroma3.diadia;



import java.util.Scanner;

/**
 * Questa classe modella un comando.
 * Un comando consiste al piu' di due parole:
 * il nome del comando e un parametro
 * su cui si applica il comando.
 * (Per esempio, alla riga digitata dall'utente "vai nord"
 *  corrisponde un comando di nome "vai" e parametro "nord").
 *
 * @author  docente di POO
 * @version 1.8
 */

public class Comando {

    private String nome;										//nome usato come comando principale
    private String parametro;
    
    /**
	 * Crea il comando.
	 */
    public Comando(String istruzione) {
		Scanner scannerDiParole = new Scanner(istruzione);
																
		// prima parola: nome del comando
		if (scannerDiParole.hasNext())
			this.nome = scannerDiParole.next(); 

		// seconda parola: eventuale parametro
		if (scannerDiParole.hasNext())
			this.parametro = scannerDiParole.next();
    }

    public String getNome() {
        return this.nome;
    }

    public String getParametro() {
        return this.parametro;
    }

    public boolean sconosciuto() {								//ritorna 1 se il nome è null altrimenti 0,
        return (this.nome == null);								//non viene mai invocato
    }
}