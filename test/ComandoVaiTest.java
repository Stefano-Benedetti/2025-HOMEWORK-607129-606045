import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;

class ComandoVaiTest {
	ComandoVai c;
	Partita partita;
	IO io;
	
	@BeforeEach
	void setUp() {
		io = new IOConsole();							//				 biblioteca
		partita=new Partita();							//	 				 |
	}													//---laboratorio---atrio---aulaN11---
														//			|		 |		 |
	@Test												//			------aulaN10-----
	void testVaiNord() {
		c=new ComandoVai("nord");
		c.esegui(partita, io);
		assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testVaiSud() {
		c=new ComandoVai("sud");
		c.esegui(partita, io);
		assertEquals("Aula N10", partita.getStanzaCorrente().getNome());
	}
	
	@Test
	void testVaiDirezioneInesistente() {
		c=new ComandoVai("aaaa");
		c.esegui(partita, io);
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}
	

}
