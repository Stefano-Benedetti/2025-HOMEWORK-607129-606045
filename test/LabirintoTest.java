import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import static it.uniroma3.diadia.ambienti.Direzione.*;

class LabirintoTest {
	
	
	Labirinto labirinto;
	Stanza stanzaCorrente;
	
	@BeforeEach
	void SetUp() {
		labirinto = new Labirinto();
	}

	//test di creaStanze()
	
	@Test
	void VerificaStanzaIniziale() {
		assertEquals("Atrio",labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	void VerificaStanzaFinale() {
		assertEquals("Biblioteca",labirinto.getStanzaFinale().getNome());
	}
	
	@Test
	void VerificaOssoInAtrio() {
		assertTrue(labirinto.getStanzaIniziale().hasAttrezzo("osso"));
	}
		
	@Test
	void VerificaNordDiAtrio() {
		assertEquals("Biblioteca",labirinto.getStanzaIniziale().getStanzaAdiacente(nord).getNome());
	}
}
