package it.uniroma3.diadia;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;


public class IOSimulator implements IO{
	private List<String> istruzioni;
	Iterator<String> it;
	
	public IOSimulator() {
		this.istruzioni = new ArrayList<String>();
	}
	
	public void setIstruzione(String istruzione) {
		istruzioni.add(istruzione);
	}
	

	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	

	public void mostraMessaggioNoInvio(String msg) {
		System.out.print(msg);
	}
	
	/*
	 * legge le istruzioni prendendole dalla lista di istruzioni
	 * l'iteratore se null viene inizializzato, altrimenti viene solo
	 * incrementato, questo si può fare perchè durante la lettura la
	 * lista delle istruzioni non viene modificata
	 */
	public String leggiRiga() {
		if(it==null)
			it = istruzioni.iterator();
		String temp = it.next();
		this.mostraMessaggio(temp);
		return temp;
	}
	

}
