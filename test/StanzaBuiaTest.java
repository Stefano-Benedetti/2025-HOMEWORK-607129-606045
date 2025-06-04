import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import it.uniroma3.diadia.ambienti.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;


class StanzaBuiaTest {
	
	StanzaBuia buia;
	Stanza normale;
	Attrezzo lanterna;
	
	@BeforeEach
	void setUp() {
		buia = new StanzaBuia("Buia","lanterna");
		lanterna = new Attrezzo("lanterna",1);
	}

	
	@Test
	void testStanzaVuota() {
		assertEquals("Qui c'è un buio pesto...",buia.getDescrizione());
	}
	
	@Test
	void testConLanterna() {
		buia.addAttrezzo(lanterna);
		assertEquals("Buia\nUscite: \nAttrezzi nella stanza: lanterna (1kg) ",buia.getDescrizione());
	}
	
	@Test
	void testConAttrezzoNoLanterna() {
		Attrezzo sasso = new Attrezzo("sasso",10);
		buia.addAttrezzo(sasso);
		assertEquals("Qui c'è un buio pesto...",buia.getDescrizione());
	}
	
	@Test
	void testConLanternaEUnAltroAttrezzo() {
		Attrezzo sasso = new Attrezzo("sasso",10);
		buia.addAttrezzo(sasso);
		buia.addAttrezzo(lanterna);
		assertEquals("Buia\nUscite: \nAttrezzi nella stanza: sasso (10kg) lanterna (1kg) ",buia.getDescrizione());
	}
	
}
