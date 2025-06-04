import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


class BorsaTest {
	
	Borsa borsa;
	Attrezzo ditroppo;
	Attrezzo pesante;

	@BeforeEach
	void setUp() {
		borsa = new Borsa();
		borsa.addAttrezzo(new Attrezzo("pala0",1));
		ditroppo = new Attrezzo("noncentra",1);
		pesante = new Attrezzo("enorme",100);
	}
	
	
	//test di removeAttrezzo()
	@Test
	void testRimossoAttrezzoEsistenteDaBorsa() {
		assertTrue(borsa.removeAttrezzo("pala0"));
	}
	
	@Test
	void testRimossoAttrezzoNonEsistenteDaBorsa() {
		assertFalse(borsa.removeAttrezzo("inesistente"));
	}
	
	
	//test di addAttrezzo, casi particolari
	@Test
	void testRaggiuntoNumMaxDiAttrezzi() {
		for(int i=1; i<10;i++)
			borsa.addAttrezzo(new Attrezzo("pala"+i,1));
		assertFalse(borsa.addAttrezzo(ditroppo));
	}
	
	@Test
	void testRaggiuntoPesoMaxBorsa() {
		for(int i=1; i<10;i++)
			borsa.addAttrezzo(new Attrezzo("pala"+i,1));
		assertFalse(borsa.addAttrezzo(pesante));
	}
	
	
	//test di getPeso()
	@Test
	void testGetPeso() {
		for(int i=1; i<10;i++)
			borsa.addAttrezzo(new Attrezzo("pala"+i,1));
		assertEquals(10, borsa.getPeso());
	}
	
	
	//test di getAttrezzo()
	@Test
	void testGetAttrezzoEsistente() {
		assertEquals("pala0", borsa.getAttrezzo("pala0").getNome());
	}
	
	@Test
	void testGetAttrezzoNonEsistente() {
		assertNull(borsa.getAttrezzo("inesistente"));
	}
	
	
	//test di isEmpty()
	@Test
	void testBorsaNonVuota() {
		assertFalse(borsa.isEmpty());
	}
	
	@Test
	void testBorsaVuota() {
		borsa.removeAttrezzo("pala0");
		assertTrue(borsa.isEmpty());
	}
	
}