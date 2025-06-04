import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {

	StanzaMagica magica;
	Stanza normale;
	Attrezzo a1;
	Attrezzo a2;
	Attrezzo a3;
	Attrezzo a4;
	Attrezzo contrario;
	
	@BeforeEach
	void setUp() {
		magica = new StanzaMagica("Magica",3);
		a1 = new Attrezzo("a1",1);
		a2 = new Attrezzo("a2",1);
		a3 = new Attrezzo("a3",1);
		a4 = new Attrezzo("a4",1);
		contrario = new Attrezzo("amogus",1);
	}
	
	@Test
	void testSogliaNonRaggiunta() {
		magica.addAttrezzo(a1);
		magica.addAttrezzo(a2);
		magica.addAttrezzo(a3);
		assertTrue(magica.hasAttrezzo("a1"));
		assertTrue(magica.hasAttrezzo("a2"));
		assertTrue(magica.hasAttrezzo("a3"));
	}
	
	@Test
	void testNomeContrarioConSogliaRaggiunta() {
		magica.addAttrezzo(a1);
		magica.addAttrezzo(a2);
		magica.addAttrezzo(a3);
		magica.addAttrezzo(contrario);
		assertTrue(magica.hasAttrezzo("sugoma"));
		assertFalse(magica.hasAttrezzo("amogus"));
	}
	
	@Test
	void testPesoDoppioConSogliaRaggiunta() {
		magica.addAttrezzo(a1);
		magica.addAttrezzo(a2);
		magica.addAttrezzo(a3);
		magica.addAttrezzo(contrario);
		assertEquals(2,magica.getAttrezzo("sugoma").getPeso());
	}
	
	@Test
	void testNomeContrarioConSogliaSuperata() {
		magica.addAttrezzo(a1);
		magica.addAttrezzo(a2);
		magica.addAttrezzo(a3);
		magica.addAttrezzo(a4);
		magica.addAttrezzo(contrario);
		assertTrue(magica.hasAttrezzo("sugoma"));
		assertFalse(magica.hasAttrezzo("amogus"));
	}
}
