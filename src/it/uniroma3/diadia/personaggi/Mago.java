package it.uniroma3.diadia.personaggi;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Partita;

public class Mago extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";
	
	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";
	private static final String MESSAGGIO_REGALO = "L'attrezzo regalato, più leggero è diventato!";
	private Attrezzo attrezzo;
	
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}
	
	@Override
	public String agisci(Partita partita) {
		if (this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			return MESSAGGIO_DONO;
		}
		else
			return MESSAGGIO_SCUSE;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		attrezzo.setPeso(attrezzo.getPeso()/2);
		partita.getStanzaCorrente().addAttrezzo(attrezzo);
		return MESSAGGIO_REGALO;
	}
}