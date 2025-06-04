package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private Direzione direzBloccata;
	private String attrChiave;
	
	public StanzaBloccata(String nome, Direzione direzBloccata, String attrChiave) {
		super(nome);
		this.direzBloccata = direzBloccata;
		this.attrChiave = attrChiave;
	}
	
	/*
	 * ritorna la stanza adiacente solo se la direzione specificata non è bloccata
	 * o se c'è la chiave all'interno della stanza
	 */
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if (super.hasAttrezzo(this.attrChiave) || !(direzione==direzBloccata))
			return super.getStanzaAdiacente(direzione);
		return this;
	}
	
	@Override
	public String getDescrizione() {
		StringBuilder descrizione = new StringBuilder();
		descrizione.append(super.toString());
		if(!super.hasAttrezzo(this.attrChiave)) {
			descrizione.append("\nDirezione bloccata: "+this.direzBloccata);
			descrizione.append("\nPer sbloccare questa direzione serve: "+attrChiave);
		}
		return descrizione.toString();
	}
	
}
