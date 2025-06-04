import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.comandi.*;

class AbstractComandoTest {
	
	Partita partita;
	ComandoAiuto ca;
	ComandoPrendi cp;
	
	@BeforeEach
	void setUp() {
		partita = new Partita();
		ca = new ComandoAiuto();
		cp = new ComandoPrendi();
	}

	@Test
	void testComandoSenzaParametro() {
		ca.setParametro("impossibile");
		assertEquals(null, ca.getParametro());
	}
	
	@Test
	void testComandoConParametro() {
		cp.setParametro("osso");;
		assertEquals("osso", cp.getParametro());
	}

}
