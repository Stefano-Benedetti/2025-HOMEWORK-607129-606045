import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class FabbricaDiComandiFisarmonicaTest {
	
	FabbricaDiComandiFisarmonica f;
	Comando c;
	String comando_scritto;
	
	@BeforeEach
	void setUp() {
		f=new FabbricaDiComandiFisarmonica();
	}
	
	@Test
	void testComandoVaiNome() {
		comando_scritto="vai";
		c=f.costruisciComando(comando_scritto);
		assertEquals("vai", c.getNome());
	}
	
	@Test
	void testComandoVaiParametro() {
		comando_scritto="vai nord";
		c=f.costruisciComando(comando_scritto);
		assertEquals("nord", c.getParametro());
	}
	
	@Test
	void testNessunComando() {
		comando_scritto="";
		c=f.costruisciComando(comando_scritto);
		assertEquals("ComandoNonValido", c.getNome());
	}
	
	@Test
	void testComandoInesistente() {
		comando_scritto="aaa";
		c=f.costruisciComando(comando_scritto);
		assertEquals("ComandoNonValido", c.getNome());
	}

}
