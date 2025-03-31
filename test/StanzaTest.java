import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


class StanzaTest {
	private Stanza a;
	private Stanza b;
	private Attrezzo spada;
	
	@BeforeEach
	public void setUp() {
		this.a=new Stanza("a");
		this.b=new Stanza("b");
		this.a.impostaStanzaAdiacente("nord", this.b);
		
		this.spada=new Attrezzo("spada", 7);		
		
	}
	
	//test di impostaStanzaAdiacente()
	@Test
	public void stanzaNordDeveEssereb() {
		assertEquals(this.b, this.a.getStanzaAdiacente("nord"));
	}
	
	@Test
	public void stanzaSudDeveEssereNull() {
		assertNull(this.a.getStanzaAdiacente("sud"));
	}
	
	@Test
	public void modificandoLaStanzaNordDeveRestituireLaNuovaStanzaNord() {
		Stanza c=new Stanza("c");
		a.impostaStanzaAdiacente("nord", c);
		assertEquals(c, this.a.getStanzaAdiacente("nord"));
	}
	
	
	//test di addAttrezzo()
	@Test
	public void deveEsserciSpazioPerAttrezzoDeveTornareTrue() {
		assertTrue(this.a.addAttrezzo(spada));
	}
	
	@Test
	public void ArrayAttrezziPienoDeveTornareFalse() {
		for(int i=0; i<10; i++)
			this.a.addAttrezzo(new Attrezzo("spada", 7));
		assertFalse(this.a.addAttrezzo(new Attrezzo("ascia", 8)));
	}
	
	
	//test di toString()
	@Test
	public void verificoToStringDiA() {
		assertEquals("a\nUscite:  nord\nAttrezzi nella stanza: ", this.a.toString());
	}
	
	@Test
	public void verificoToStringDiB() {
		assertEquals("b\nUscite: \nAttrezzi nella stanza: ", this.b.toString());
	}
	
	@Test
	public void verificoToStringDiAModificato() {
		a.impostaStanzaAdiacente("sud", new Stanza("c"));
		a.addAttrezzo(new Attrezzo("spada", 7));
		assertEquals("a\nUscite:  nord sud\nAttrezzi nella stanza: spada (7kg) ", this.a.toString());
	}

}
