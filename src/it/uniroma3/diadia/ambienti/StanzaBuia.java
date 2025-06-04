package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String attrezzoLuce;
	
	public StanzaBuia(String nome, String attrezzoLuce) {
		super(nome);
		this.attrezzoLuce = attrezzoLuce;
	}
	
	@Override
	public String getDescrizione() {
		if(super.hasAttrezzo(this.attrezzoLuce))
			return super.toString();
		return "Qui c'è un buio pesto...";
	}
	
}
