package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.ConfiguraProprieta;

/**
 * Questa classe modella un giocatore.
 * 
 */

public class Giocatore {
	
	static final private int CFU_INIZIALI = Integer.parseInt(ConfiguraProprieta.getCFUIniziali());

	private int cfu;
	private Borsa borsa;
	
	/**
	 * Crea un giocatore
	 */
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
	
	/**
	 * Imposta il valore della variabile di istanza cfu
	 * @param num valore che si vuole alla variabile di istanza cfu
	 */
	public void setCfu(int num) {
		this.cfu = num;
	}
	
	/**
	 * Restuisce il valore della variabile di istanza cfu
	 * @return valore della variabile di istanza cfu
	 */
	public int getCfu() {
		return this.cfu;
	}
	
	/**
	 * Restuisce il valore della variabile di istanza cfu
	 * @return riferimento alla borsa di giocatore
	 */
	public Borsa getBorsa() {
		return this.borsa;
	}

}
