package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

import java.io.*;
import java.util.*;

public class CaricatoreLabirinto {
	
	/* i vari prefissi di ogni riga per ogni elemento del labirinto da impostare*/
	private static final String ABILITATO_MARKER = "Abilitato: ";
	private static final String STANZE_MARKER = "Stanze: ";    
	private static final String STANZEBUIE_MARKER = "StanzeBuie: ";
	private static final String STANZEBLOCCATE_MARKER = "StanzeBloccate: ";
	private static final String STANZEMAGICHE_MARKER = "StanzeMagiche: ";
	private static final String STANZA_INIZIALE_MARKER = "Inizio: ";    
	private static final String STANZA_VINCENTE_MARKER = "Finale: ";  
	private static final String ATTREZZI_MARKER = "Attrezzi: ";
	private static final String PERSONAGGI_MARKER = "Personaggi: ";
	private static final String USCITE_MARKER = "Uscite: ";
		

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Finale: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	
	private LineNumberReader reader;

	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;
	private LabirintoBuilder labirinto;


	public CaricatoreLabirinto(String nomeFile) throws FileNotFoundException {
		this.labirinto = Labirinto.newBuilder();
		this.reader = new LineNumberReader(new FileReader(nomeFile));
	}
	
	public CaricatoreLabirinto(StringReader reader) throws FileNotFoundException {
		this.labirinto = Labirinto.newBuilder();
		this.reader = new LineNumberReader(reader);
	}

	public boolean carica() throws FormatoFileNonValidoException {
		try {
			if(!this.leggiFileAbilitato())
				return false;
			this.leggiECreaStanze();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiECreaStanzeMagiche();
			this.leggiInizialeEfinale();
			this.leggiECollocaAttrezzi();
			this.leggiECollocaPersonaggi();
			this.leggiEImpostaUscite();
		} catch(FormatoFileNonValidoException e) {
			throw e;
		}
		finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
		return true;
	}
	
	private boolean leggiFileAbilitato() throws FormatoFileNonValidoException {
		boolean abilitato = false;
		String bool = this.leggiRigaCheCominciaPer(ABILITATO_MARKER);
		check(bool.equals("false") || bool.equals("true"), "Abilitato deve essere true o false" );
		if(bool.equals("true"))
			abilitato = true;
		return abilitato;
	}
	
	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza : separaStringheAlleVirgole(nomiStanze))
			this.labirinto.addStanza(nomeStanza);
	}
	
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException  {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZEBUIE_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {
			String nomeStanza = null;
			String attrezzoLuminoso = null;
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza buia"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo luminoso di "+nomeStanza+"."));
				attrezzoLuminoso = scannerLinea.next();
			}
			this.labirinto.addStanzaBuia(nomeStanza, attrezzoLuminoso);
		}
	}
	
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException  {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZEBLOCCATE_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {
			String nomeStanza = null;
			String direzione = null;
			String chiave = null;
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza bloccata"));
				nomeStanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("direzione di "+nomeStanza+"."));
				direzione = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo chiave di "+nomeStanza+"."));
				chiave = scannerLinea.next();
			}
			this.labirinto.addStanzaBloccata(nomeStanza, Direzione.valueOf(direzione), chiave);
		}
	}
	
	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException  {
		String specificheStanze = this.leggiRigaCheCominciaPer(STANZEMAGICHE_MARKER);
		for(String specificaStanza : separaStringheAlleVirgole(specificheStanze)) {
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaStanza)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza buia"));
				nomeStanza = scannerLinea.next();
			}
			this.labirinto.addStanzaMagica(nomeStanza);
		}
	}
	
	private void leggiInizialeEfinale() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		
		String nomeStanzaFinale = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaFinale), nomeStanzaFinale + " non definita");
		
		this.stanzaIniziale = this.labirinto.getListaStanze().get(nomeStanzaIniziale);
		this.stanzaFinale = this.labirinto.getListaStanze().get(nomeStanzaFinale);
	}
	
	/**
	 * La variabile specificaAttrezzo indica tutte le info riguardanti
	 * un solo attrezzo e viene creato uno scanner per scannerizzare ogni 
	 * specifica singolarmente
	 * @throws FormatoFileNonValidoException
	 */
	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			String nomeAttrezzo = null;
			String pesoAttrezzo = null;
			String nomeStanza = null; 
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}
	
	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		try {
			check(isStanzaValida(nomeStanza),"Attrezzo "+ nomeAttrezzo+" non collocabile: stanza " +nomeStanza+" inesistente");
			this.labirinto.addAttrezzo(nomeAttrezzo, Integer.parseInt(pesoAttrezzo), nomeStanza);
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}
	

	/**
	 * metodo per aggiungere i personaggi letti da file, utilizza il metodo
	 * leggiPresentazione() per leggere le ultime parole di ogni specifica di ogni personaggio
	 * @throws FormatoFileNonValidoException
	 */
	private void leggiECollocaPersonaggi() throws FormatoFileNonValidoException {
		String specifichePersonaggi = this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);

		for(String specificaPersonaggio : separaStringheAlleVirgole(specifichePersonaggi)) {
			String tipologia;
			String stanza;
			String nome;
			String presentazione;	
			AbstractPersonaggio p=null;
			
			try (Scanner scannerLinea = new Scanner(specificaPersonaggio)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la tipologia di un personaggio."));
				tipologia = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("la stanza di un personaggio."));
				stanza = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome del personaggio."));
				nome = scannerLinea.next();
				if (tipologia.equals("strega")) {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del personaggio."));
					presentazione = leggiPresentazione(scannerLinea);
					p = new Strega(nome, presentazione);
				}
				if (tipologia.equals("mago")) {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo del mago "+nome+"."));
					String attrezzo = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo del mago "+nome+"."));
					String peso = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del personaggio."));
					presentazione = leggiPresentazione(scannerLinea);
					p = new Mago(nome, presentazione, new Attrezzo(attrezzo, Integer.parseInt(peso)));
				}
				if (tipologia.equals("cane")) {
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("l'attrezzo del cane "+nome+"."));
					String attrezzo = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo del cane "+nome+"."));
					String peso = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome dell'attrezzo gustoso del cane "+nome+"."));
					String nomeAttGustoso = scannerLinea.next();
					check(scannerLinea.hasNext(),msgTerminazionePrecoce("la presentazione del personaggio."));
					presentazione = leggiPresentazione(scannerLinea);
					p = new Cane(nome, presentazione, new Attrezzo(attrezzo, Integer.parseInt(peso)), nomeAttGustoso);
				}
				
				check(p!=null, "tipologia del personaggio inesistente");
				this.labirinto.setPersonaggio(p, stanza);
			}				
			
		}
	}
	
	
	
	
	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificaUscita : separaStringheAlleVirgole(specificheUscite)) {
			String stanzaPartenza = null;
			String dir = null;
			String stanzaDestinazione = null; 
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {			
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
				stanzaPartenza = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
				dir = scannerDiLinea.next();
				check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dir));
				stanzaDestinazione = scannerDiLinea.next();
			}	
			impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
		} 
	}
	
	private void impostaUscita(String stanzaDa, String dir, String nomeA) throws FormatoFileNonValidoException {
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		labirinto.addAdiacenza(stanzaDa, nomeA, Direzione.valueOf(dir));
	}
	
	

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga!=null,"era attesa una riga che cominciasse per "+marker);
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length());
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(" / ");
		try (Scanner scannerDiParole = scanner) {
			while(scanner.hasNext())
				result.add(scannerDiParole.next());
		}
		return result;
	}
	
	private String leggiPresentazione(Scanner scannerLinea) {
		StringBuilder prep = new StringBuilder();
		scannerLinea.useDelimiter("-");				//N.B. se si vuole riutilizzare lo scanner va risettato il delimiter
		while(scannerLinea.hasNext())
			prep.append(scannerLinea.next());
		return prep.toString();
	}

	
	
	private boolean isStanzaValida(String nomeStanza) {
		return this.labirinto.getListaStanze().containsKey(nomeStanza);
	}

	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}

	public Stanza getStanzaFinale() {
		return this.stanzaFinale;
	}
}
