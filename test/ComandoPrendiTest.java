import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import java.util.Scanner;

class ComandoPrendiTest {

	private Partita partita;
	private ComandoPrendi cmd;
	private IO io;
	

	@BeforeEach
	void setUp() {
		partita = new Partita();
		cmd = new ComandoPrendi("osso");
		io = new IOConsole(new Scanner(System.in));
				
	}
	
	@Test
	void testAttrezzoPresoQuindiNellaBorsa() {
		cmd.esegui(partita, io);
		
		assertFalse(partita.getGiocatore().getBorsa().isEmpty());
	}
	
	@Test
	void testPrendiConStanzaVuota() {
		partita.getStanzaCorrente().removeAttrezzo("osso");
		
		assertDoesNotThrow(()->{cmd.esegui(partita, io);});
	}
	
	@Test
	void testPrendiConAttrezzoNonTrovato() {
		Attrezzo pera = new Attrezzo("Pera",1);
		partita.getStanzaCorrente().addAttrezzo(pera);
		partita.getStanzaCorrente().removeAttrezzo("osso");
		
		assertDoesNotThrow(()->{cmd.esegui(partita, io);});
	}
	
	@Test
	void testPrendiConBorsaPiena() {
		Attrezzo TheRock = new Attrezzo("The Rock",10);
		partita.getGiocatore().getBorsa().addAttrezzo(TheRock);
		
		assertDoesNotThrow(()->{cmd.esegui(partita, io);});
	}
}
