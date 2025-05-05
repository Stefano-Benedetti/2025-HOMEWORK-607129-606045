import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class ComandoPosaTest {
	
	private Partita partita;
	private ComandoPosa cmd;
	private IO io;
	

	@BeforeEach
	void setUp() {
		partita = new Partita();
		cmd = new ComandoPosa("osso");
		io = new IOConsole();
				
	}
	
	@Test
	void testAttrezzoPosatoQuindiBorsaVuota() {
		partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().getAttrezzo("osso"));
		cmd.esegui(partita, io);
		assertTrue(partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	void testPosaConBorsaVuota() {
		assertDoesNotThrow(()->{cmd.esegui(partita, io);});
	}
	
	@Test
	void testPosaConAttrezzoNonTrovato() {
		Attrezzo pera = new Attrezzo("Pera",1);
		partita.getGiocatore().getBorsa().addAttrezzo(pera);
		assertDoesNotThrow(()->{cmd.esegui(partita, io);});
	}
	
	@Test
	void testPosaConStanzaPiena() {
		partita.getGiocatore().getBorsa().addAttrezzo(partita.getStanzaCorrente().getAttrezzo("osso"));
		for(int i=0; i<10; i++) {
			Attrezzo A = new Attrezzo("A",1);
			partita.getStanzaCorrente().addAttrezzo(A);
		}
		
		assertDoesNotThrow(()->{cmd.esegui(partita, io);});
	}
	
}
