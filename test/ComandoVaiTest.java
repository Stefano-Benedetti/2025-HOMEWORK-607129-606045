import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import static it.uniroma3.diadia.ambienti.Direzione.*;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;

class ComandoVaiTest {
	ComandoVai c;
	Partita partita;
	IO io;
	LabirintoBuilder labirinto;
	
	@BeforeEach
	void setUp() {
		io = new IOConsole(new Scanner(System.in));		//				 biblioteca
														//	 				 |
	}													//---laboratorio---atrio---aulaN11---
														//			|		 |		 |
	@Test												//			------aulaN10-----
	void testVaiNord() {
		partita=new Partita();
		c=new ComandoVai("nord");
		c.esegui(partita, io);
		assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testVaiSud() {
		partita=new Partita();
		c=new ComandoVai("sud");
		c.esegui(partita, io);
		assertEquals("Aula N10", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testVaiDirezioneInesistente() {
		partita=new Partita();
		c=new ComandoVai("aaaa");
		c.esegui(partita, io);
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testVaiDirezioneInesistenteMonolocale() {
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("monolocale");
		partita=new Partita(labirinto);
		
		c=new ComandoVai("nord");
		c.esegui(partita, io);
		assertEquals("monolocale", partita.getStanzaCorrente().getNome());
	}

	@Test
	void testVaiDirezioneEsistenteBilocale() {
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("Bilocale1")
			.addStanza("Bilocale2")
			.addAdiacenza("Bilocale1", "Bilocale2", nord);
		partita=new Partita(labirinto);
		
		c=new ComandoVai("nord");
		c.esegui(partita, io);
		assertEquals("Bilocale2", partita.getStanzaCorrente().getNome());
	}
	

}
