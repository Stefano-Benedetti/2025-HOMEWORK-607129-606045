import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.StringReader;

import org.junit.After;
import org.junit.Before;

import it.uniroma3.diadia.eccezioni.FormatoFileNonValidoException;
import it.uniroma3.diadia.ambienti.CaricatoreLabirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import org.junit.jupiter.api.Test;

class CaricatoreLabirintoTest {
	private final String monolocale = 
			"Abilitato: true\n"+
			"Stanze: monolocale\n"+
			"StanzeBuie: \n"+
			"StanzeBloccate: \n"+
			"StanzeMagiche: \n"+
			"Inizio: monolocale\n"+
			"Finale: monolocale\n"+
			"Attrezzi: \n"+
			"Personaggi: \n"+
			"Uscite: \n";

	private final String bilocale = 
			"Abilitato: true\n"+
			"Stanze: bilocale1, bilocale2\n"+
			"StanzeBuie: \n"+
			"StanzeBloccate: \n"+
			"StanzeMagiche: \n"+
			"Inizio: bilocale1\n"+
			"Finale: bilocale2\n"+
			"Attrezzi: spada 3 bilocale1\n"+
			"Personaggi: \n"+
			"Uscite: \n";
	
	private CaricatoreLabirinto cl;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMonolocale() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(new StringReader(monolocale));
		cl.carica();
		assertEquals("monolocale", this.cl.getStanzaIniziale().getNome());
		assertEquals("monolocale", this.cl.getStanzaFinale().getNome());
		}
	
	@Test
	public void testBilocale() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(new StringReader(bilocale));
		cl.carica();
		assertEquals("bilocale1", this.cl.getStanzaIniziale().getNome());
		assertEquals("bilocale2", this.cl.getStanzaFinale().getNome());
		}
	
	
	@Test
	public void testBilocaleAttrezzo() throws FormatoFileNonValidoException, FileNotFoundException {
		cl = new CaricatoreLabirinto(new StringReader(bilocale));
		cl.carica();
		Attrezzo expected = new Attrezzo("spada", 3);
		assertEquals(expected, this.cl.getStanzaIniziale().getAttrezzo("spada"));
		}
}
