import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.*;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


class BorsaTest {
	
	Borsa borsa;
	Attrezzo attrezzo0;
	Attrezzo attrezzo1;
	Attrezzo attrezzo2;
	Attrezzo attrezzo3;
	Attrezzo attrezzo4;
	Attrezzo attrezzo5;
	Attrezzo attrezzo6;
	Attrezzo attrezzo7;
	Attrezzo attrezzo8;
	Attrezzo attrezzo9;
	Attrezzo ditroppo;
	Attrezzo pesante;

	@BeforeEach
	public void setUp() {
		borsa = new Borsa();
		attrezzo0 = new Attrezzo("pala",1);
		attrezzo1 = new Attrezzo("palla",1);
		attrezzo2 = new Attrezzo("palle",1);
		attrezzo3 = new Attrezzo("pallo",1);
		attrezzo4 = new Attrezzo("palo",1);
		attrezzo5 = new Attrezzo("pallol",1);
		attrezzo6 = new Attrezzo("pallallero",1);
		attrezzo7 = new Attrezzo("pallalla",1);
		attrezzo8 = new Attrezzo("pollo",1);
		attrezzo9 = new Attrezzo("pelle",1);
		ditroppo = new Attrezzo("noncentra",1);
		pesante = new Attrezzo("enorme",100);
		borsa.addAttrezzo(attrezzo0);
		borsa.addAttrezzo(attrezzo1);
		borsa.addAttrezzo(attrezzo2);
		borsa.addAttrezzo(attrezzo3);
		borsa.addAttrezzo(attrezzo4);
		borsa.addAttrezzo(attrezzo5);
		borsa.addAttrezzo(attrezzo6);
		borsa.addAttrezzo(attrezzo7);
		borsa.addAttrezzo(attrezzo8);
		borsa.addAttrezzo(attrezzo9);
	}
	
	
	//test di removeAttrezzo()
	@Test
	public void testRimossoAttrezzoEsistenteDaBorsa() {
		assertTrue(borsa.removeAttrezzo("pala"));
	}
	
	@Test
	public void testRimossoAttrezzoNonEsistenteDaBorsa() {
		assertFalse(borsa.removeAttrezzo("inesistente"));
	}
	
	
	//test di addAttrezzo, casi particolari
	@Test
	public void testRaggiuntoNumMaxDiAttrezzi() {
		assertFalse(borsa.addAttrezzo(ditroppo));
	}
	
	@Test
	public void testRaggiuntoPesoMaxBorsa() {
		borsa.removeAttrezzo("pelle");
		assertFalse(borsa.addAttrezzo(pesante));
	}
	
	
	//test di getPeso()
	@Test
	public void testGetPeso() {
		assertEquals(10, borsa.getPeso());
	}
	
	
	//test di getAttrezzo()
	@Test
	public void testGetAttrezzoEsistente() {
		assertEquals("pala", borsa.getAttrezzo("pala").getNome());
	}
	
	@Test
	public void testGetAttrezzoNonEsistente() {
		assertNull(borsa.getAttrezzo("inesistente"));
	}
	
	
	//test di isEmpty()
	@Test
	public void testBorsaNonVuota() {
		assertFalse(borsa.isEmpty());
	}
	
	@Test
	public void testBorsaVuota() {
		borsa.removeAttrezzo("pala");
		borsa.removeAttrezzo("palla");
		borsa.removeAttrezzo("palle");
		borsa.removeAttrezzo("pallo");
		borsa.removeAttrezzo("palo");
		borsa.removeAttrezzo("pallol");
		borsa.removeAttrezzo("pallallero");
		borsa.removeAttrezzo("pallalla");
		borsa.removeAttrezzo("pollo");
		borsa.removeAttrezzo("pelle");
		assertTrue(borsa.isEmpty());
	}
	
	
}