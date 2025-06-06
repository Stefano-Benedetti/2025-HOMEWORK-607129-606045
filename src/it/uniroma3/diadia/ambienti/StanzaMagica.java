package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.ConfiguraProprieta;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza{
	final static private int SOGLIA_MAGICA_DEFAULT = Integer.parseInt(ConfiguraProprieta.getSogliaMagica());
	private int contatoreAttrezziPosati;
	private int sogliaMagica;					//num attrezzi da posare per attivare il comportamento magico
	
	
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}
	
	@Override
	public boolean isMagica(){
    	return true;
    }
	
	/*
	 * Questo metodo non aggiunge effettivamente l'oggetto alla stanza ma fa
	 * le modifiche necessarie prima di aggiungerlo. Per aggiungerlo effettivamente
	 * utilizza il metodo addAttrezzo() definito nella superclasse Stanza.
	 */
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if (this.contatoreAttrezziPosati>this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		return super.addAttrezzo(attrezzo);
	}
	
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),pesoX2);
		return attrezzo;
	}
}
