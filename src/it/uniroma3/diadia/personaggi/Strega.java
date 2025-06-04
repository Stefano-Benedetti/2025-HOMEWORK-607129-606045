package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.util.Collection;

public class Strega extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_SALUTATA = "Lasciami in pace, grazie.";
	private static final String MESSAGGIO_NON_SALUTATA = "Maleducato, non mi hai neanche salutata! Vattene!";
	private static final String MESSAGGIO_RIDE = "Ora è mio ... hihihihi!";
	//private Attrezzo attrezzoPreso;	vedi riga 56
	
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}
	
	@Override
	public String agisci(Partita partita) {
		String msg = null;
		Stanza prossimaStanza;
		
		Collection<Stanza> stanze = partita.getStanzaCorrente().getMapStanzeAdiacenti().values();
		Stanza stin = (Stanza)stanze.toArray()[0];
		int min = stin.getAttrezzi().size();
		int max = min;
		prossimaStanza = stin;
		
		if(super.getSalutato())	{
			msg = MESSAGGIO_SALUTATA;
			for(Stanza st : stanze) {
				if(st.getAttrezzi().size()>max) {
					max = st.getAttrezzi().size();
					prossimaStanza = st;
				}
			}
		}
		else{
			msg = MESSAGGIO_NON_SALUTATA;
			for(Stanza st : stanze) {
				if(st.getAttrezzi().size()<min) {
					min = st.getAttrezzi().size();
					prossimaStanza = st;
				}
			}
		}
		partita.setStanzaCorrente(prossimaStanza);
		return msg;
	}
	
	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		//this.attrezzoPreso = attrezzo;	in caso si voglia riutilizzare l'attrezzo preso dalla strega
		return MESSAGGIO_RIDE;
	}
}