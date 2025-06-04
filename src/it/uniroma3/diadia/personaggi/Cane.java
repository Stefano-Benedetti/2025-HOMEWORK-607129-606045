package it.uniroma3.diadia.personaggi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_MORSO = "Il cane ti ha morso, hai perso un CFU!";
	private static final String MESSAGGIO_ACCETTA_REGALO = "Il cane ha accettato il tuo regalo e"
			+ " forse ha lasciato qualcosa a terra...";
	private String nomeAttrezzoGustoso;
	private Attrezzo attrezzoInBocca;
	
	public Cane(String nome, String presentazione, Attrezzo attrezzo, String nomeAttrezzoGustoso) {
		super(nome, presentazione);
		this.attrezzoInBocca = attrezzo;
		this.nomeAttrezzoGustoso = nomeAttrezzoGustoso;
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
		return MESSAGGIO_MORSO;
	}
	
	@Override
	public String saluta() {
		StringBuilder risposta = new StringBuilder();
		risposta.append(super.getNome()+": ");
		if (!super.getSalutato())
			risposta.append(super.getPresentazione());
		else
			risposta.append("...");
		super.setSalutato();
		return risposta.toString();
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals(this.nomeAttrezzoGustoso)) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzoInBocca);
			this.attrezzoInBocca = attrezzo;
			return MESSAGGIO_ACCETTA_REGALO;
		}
		else {
			partita.getStanzaCorrente().addAttrezzo(attrezzo);
			return this.agisci(partita);
		}
	}
}