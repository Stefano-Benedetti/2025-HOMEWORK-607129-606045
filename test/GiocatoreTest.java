import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {

	Giocatore giocatore;
	
	@BeforeEach
	void setup() {
		giocatore=new Giocatore();
	}
	
	//test di corretta inizializzazione cfu
	@Test
	void controllaCfuIniziali() {
		assertEquals(20, giocatore.getCfu());
	}
	
	//test di setCfu
	@Test
	void controllaCfuImpostati() {
		this.giocatore.setCfu(10);
		assertEquals(10, giocatore.getCfu());
	}

}
