import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import static it.uniroma3.diadia.ambienti.Direzione.*;


class PartitaTest {
	
	Partita partita;
	
	@BeforeEach
	void SetUp() {
		partita = new Partita();
	}
	
	
	//test di creazione di partita
	@Test
	void VerificaStanzaCorrenteUgualeAStanzaIniziale() {
		assertEquals("Atrio",partita.getStanzaCorrente().getNome());
	}		
	
	
	//test di vinta()
	@Test
	void TestNoVittoriaIstantanea() {
		assertFalse(partita.vinta());
	}
	
	@Test
	void TestVittoria() {
		Stanza stanzaFinale=partita.getStanzaCorrente().getStanzaAdiacente(nord);
		partita.setStanzaCorrente(stanzaFinale);
		assertTrue(partita.vinta());
	}
	
	@Test
	void TestNoVittoria() {
		Stanza stanza=partita.getStanzaCorrente().getStanzaAdiacente(sud);
		partita.setStanzaCorrente(stanza);
		assertFalse(partita.vinta());
	}
	
	
	//test di isFinita()
	@Test
	void FineDopoVittoria() {
		Stanza stanzaFinale=partita.getStanzaCorrente().getStanzaAdiacente(nord);
		partita.setStanzaCorrente(stanzaFinale);
		assertTrue(partita.isFinita());
	}
	
	@Test
	void FineDopoAzzeramentoCFU() {
		partita.getGiocatore().setCfu(0);
		assertTrue(partita.isFinita());
	}
	
	@Test
	void NoFine() {
		assertFalse(partita.isFinita());
	}
}
