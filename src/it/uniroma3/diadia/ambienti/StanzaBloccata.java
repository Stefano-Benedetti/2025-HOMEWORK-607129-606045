package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	private String direzBloccata;
	private String attrChiave;
	
	public StanzaBloccata(String nome, String direzBloccata, String attrChiave) {
		super(nome);
		this.direzBloccata = direzBloccata;
		this.attrChiave = attrChiave;
	}
	
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		
		if (super.hasAttrezzo(attrChiave) || !direzione.equals(direzBloccata)) {
			return super.getStanzaAdiacente(direzione);
		}
		
		return this;
			
	}
	
	@Override
	public String getDescrizione() {
		StringBuilder descrizione = new StringBuilder();
		descrizione.append(super.toString());
		descrizione.append("\nDirezione bloccata: "+direzBloccata);
		descrizione.append("\nPer sbloccare questa direzione serve: "+attrChiave);
		return descrizione.toString();
	}
	
}
