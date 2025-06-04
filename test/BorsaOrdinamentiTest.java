import static org.junit.jupiter.api.Assertions.*;

import java.util.SortedSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaOrdinamentiTest {
	
	Borsa borsa;

	@BeforeEach
	void setUp() {
		borsa = new Borsa();
	}
	
	@Test
	void testOrdinamentoPerNome() {
		borsa.addAttrezzo(new Attrezzo("sasso",2));
		borsa.addAttrezzo(new Attrezzo("banana",1));
		borsa.addAttrezzo(new Attrezzo("aereo",4));
		borsa.addAttrezzo(new Attrezzo("zorro",3));
		
		assertEquals("[ aereo, banana, sasso, zorro ]",borsa.getDescrizioneOrdinatoPerNome());
	}
	
	@Test
	void testOrdinamentoPerPesoConPesiUguali() {
		borsa.addAttrezzo(new Attrezzo("sasso",2));
		borsa.addAttrezzo(new Attrezzo("banana",2));
		borsa.addAttrezzo(new Attrezzo("aereo",2));
		borsa.addAttrezzo(new Attrezzo("zorro",2));
		
		assertEquals("{ aereo, banana, sasso, zorro }",borsa.getDescrizioneOrdinatoPerPeso());
	}
	
	@Test
	void testOrdinamentoPerPesoConPesiDiversi() {
		borsa.addAttrezzo(new Attrezzo("sasso",2));
		borsa.addAttrezzo(new Attrezzo("banana",1));
		borsa.addAttrezzo(new Attrezzo("aereo",4));
		borsa.addAttrezzo(new Attrezzo("zorro",3));
		
		assertEquals("{ banana, sasso, zorro, aereo }",borsa.getDescrizioneOrdinatoPerPeso());
	}
	
	@Test
	void testOrdinamentoPerPesoConPesiMisti() {
		borsa.addAttrezzo(new Attrezzo("sasso",2));
		borsa.addAttrezzo(new Attrezzo("banana",2));
		borsa.addAttrezzo(new Attrezzo("aereo",3));
		borsa.addAttrezzo(new Attrezzo("zorro",3));
		
		assertEquals("{ banana, sasso, aereo, zorro }",borsa.getDescrizioneOrdinatoPerPeso());
	}
	
	@Test
	void testRaggruppamentoPerPesoConPesiUguali() {
		borsa.addAttrezzo(new Attrezzo("sasso",2));
		borsa.addAttrezzo(new Attrezzo("banana",2));
		borsa.addAttrezzo(new Attrezzo("aereo",2));
		borsa.addAttrezzo(new Attrezzo("zorro",2));
		
		assertEquals("(2, { aereo, banana, sasso, zorro })",borsa.getDescrizioneRaggruppatoPerPeso());
	}
	
	@Test
	void testRaggruppamentoPerPesoConPesiDiversi() {
		borsa.addAttrezzo(new Attrezzo("sasso",2));
		borsa.addAttrezzo(new Attrezzo("banana",1));
		borsa.addAttrezzo(new Attrezzo("aereo",3));
		borsa.addAttrezzo(new Attrezzo("zorro",4));
		
		assertEquals("(1, { banana }) ; (2, { sasso }) ; (3, { aereo }) ; (4, { zorro })",borsa.getDescrizioneRaggruppatoPerPeso());
	}
	
	@Test
	void testRaggruppamentoPerPesoConPesiMisti() {
		borsa.addAttrezzo(new Attrezzo("sasso",2));
		borsa.addAttrezzo(new Attrezzo("banana",2));
		borsa.addAttrezzo(new Attrezzo("aereo",3));
		borsa.addAttrezzo(new Attrezzo("zorro",3));
		
		assertEquals("(2, { banana, sasso }) ; (3, { aereo, zorro })",borsa.getDescrizioneRaggruppatoPerPeso());
	}
	
	@Test
	void testSortedSetPerPesoConPesiUguali() {
		borsa.addAttrezzo(new Attrezzo("sasso",2));
		borsa.addAttrezzo(new Attrezzo("banana",2));
		borsa.addAttrezzo(new Attrezzo("aereo",2));
		borsa.addAttrezzo(new Attrezzo("zorro",2));
		SortedSet<Attrezzo> s = borsa.getSortedSetOrdinatoPerPeso();
		StringBuilder stringa = new StringBuilder();
		for(Attrezzo att : s)
			stringa.append(att.getNome()+" ");
		assertEquals("aereo banana sasso zorro ",stringa.toString());
	}
	
	@Test
	void testSortedSetPerPesoConPesiDiversi() {
		borsa.addAttrezzo(new Attrezzo("sasso",2));
		borsa.addAttrezzo(new Attrezzo("banana",1));
		borsa.addAttrezzo(new Attrezzo("aereo",3));
		borsa.addAttrezzo(new Attrezzo("zorro",4));
		SortedSet<Attrezzo> s = borsa.getSortedSetOrdinatoPerPeso();
		StringBuilder stringa = new StringBuilder();
		for(Attrezzo att : s)
			stringa.append(att.getNome()+" ");
		assertEquals("banana sasso aereo zorro ",stringa.toString());
	}
	
	@Test
	void testSortedSetPerPesoConPesiMisti() {
		borsa.addAttrezzo(new Attrezzo("sasso",2));
		borsa.addAttrezzo(new Attrezzo("banana",2));
		borsa.addAttrezzo(new Attrezzo("aereo",3));
		borsa.addAttrezzo(new Attrezzo("zorro",3));
		SortedSet<Attrezzo> s = borsa.getSortedSetOrdinatoPerPeso();
		StringBuilder stringa = new StringBuilder();
		for(Attrezzo att : s)
			stringa.append(att.getNome()+" ");
		assertEquals("banana sasso aereo zorro ",stringa.toString());
	}

}
