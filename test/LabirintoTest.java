import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class LabirintoTest {
	
	
	Labirinto labirinto;
	Stanza stanzaCorrente;
	
	@BeforeEach
	public void SetUp() {
		labirinto = new Labirinto();
	}

	//test di creaStanze()
	
	@Test
	public void VerificaStanzaIniziale() {
		assertEquals("Atrio",labirinto.getStanzaIniziale().getNome());
	}
	
	@Test
	public void VerificaStanzaFinale() {
		assertEquals("Biblioteca",labirinto.getStanzaFinale().getNome());
	}
	
	@Test
	public void VerificaOssoInAtrio() {
		assertTrue(labirinto.getStanzaIniziale().hasAttrezzo("osso"));
	}
		
	@Test
	public void VerificaNordDiAtrio() {
		assertEquals("Biblioteca",labirinto.getStanzaIniziale().getStanzaAdiacente("nord").getNome());
	}
}
