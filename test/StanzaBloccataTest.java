import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.*;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.*;
import static it.uniroma3.diadia.ambienti.Direzione.*;


class StanzaBloccataTest {
	
	Stanza adiacente;
	StanzaBloccata bloccata;
	Attrezzo chiave;
	
	
	@BeforeEach
	void setUp() {
		chiave = new Attrezzo("chiave",1);
		bloccata = new StanzaBloccata("S1",nord,"chiave");
		adiacente = new Stanza("S2");
		bloccata.impostaStanzaAdiacente(nord, adiacente);
	}

	@Test
	void testDirezioneNordBloccata() {
		assertEquals("S1\nUscite:  nord\nAttrezzi nella stanza: \nDirezione bloccata: nord\nPer sbloccare questa direzione serve: chiave",bloccata.getStanzaAdiacente(nord).getDescrizione());
	}

	@Test
	void testDirezioneNordSbloccata() {
		bloccata.addAttrezzo(chiave);
		assertEquals("S2\nUscite: \nAttrezzi nella stanza: ",bloccata.getStanzaAdiacente(nord).getDescrizione());
	}
	
	@Test
	void testDirezioneInesistente() {
		assertNull(bloccata.getStanzaAdiacente(sud));
	}
	
	@Test
	void testDirezioneNordBloccataEDirezioneEstSbloccata() {
		Stanza wc = new Stanza("WC");
		bloccata.impostaStanzaAdiacente(est, wc);
		assertEquals("WC\nUscite: \nAttrezzi nella stanza: ",bloccata.getStanzaAdiacente(est).getDescrizione());
	}
}
