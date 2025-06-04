package simulazionePartite;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.personaggi.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import static it.uniroma3.diadia.ambienti.Direzione.*;


class simulazionePartiteIntere {

	IOSimulator io;
	DiaDia simulazione;
	LabirintoBuilder labirinto;
	
	@AfterEach
	void giocaSimulazione() {
		try {
		simulazione=new DiaDia(labirinto, io);
		assertDoesNotThrow(()->{simulazione.gioca();});
		io.mostraMessaggio("\n//////////////////////////////////////////////\n");
		} catch(IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Test
	void testPartitaBibliotecaTrovataSubito() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestPartitaBibliotecaTrovataSubito\n");
		io.setIstruzione("vai nord");
		labirinto = Labirinto.newBuilder();
		labirinto.creaStanze();
	}
	
	
	@Test
	void testUtenteTerminaPartitaSubito() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestUtenteTerminaPartitaSubito\n");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.creaStanze();
	}
	
	@Test
	void testUtenteTerminaCfu() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestUtenteTerminaCfu\n");
		for(int i=0; i<20; i++) 
			io.setIstruzione("vai est");
		labirinto = Labirinto.newBuilder();
		labirinto.creaStanze();
	}
	
	@Test
	void testUtenteDaComandoInesistentePoiTermina() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestUtenteDaComandoInesistentePoiTermina\n");
		io.setIstruzione("amogus");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.creaStanze();
	}
	
	@Test
	void testUtenteChiedeAiutoEGuardaPoiTermina() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestUtenteChiedeAiutoEGuardaPoiTermina\n");
		io.setIstruzione("aiuto");
		io.setIstruzione("guarda");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.creaStanze();
	}
	
	@Test
	void testUtentePrendeOggettoGuardaEPosaOggettoPoiTermina() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestUtentePrendeEPosaOggettoPoiTermina\n");
		io.setIstruzione("prendi osso");
		io.setIstruzione("guarda");
		io.setIstruzione("posa osso");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.creaStanze();
	}	
	
	@Test
	void testProvaAPrendereOggettoInesistentePoiProvaALasciareOggettoInesistentePoiTermina() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestProvaAPrendereOggettoInesistentePoiProvaALasciareOggettoInesistentePoiTermina\n");
		io.setIstruzione("prendi pietra");
		io.setIstruzione("posa pietra");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.creaStanze();
	}
	
	@Test
	void testProvaAdAndareInDirezioneInesistentePoiTermina() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestProvaAdAndareInDirezioneInesistentePoiTermina\n");
		io.setIstruzione("vai est");
		io.setIstruzione("vai nord");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.creaStanze();
	}
	
	@Test
	void testPrendeOssoVaAOvestPosaOssoGuardaVaAEstVaASudPrendeLanternaVaInBiblioteca() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestPrendeOssoVaAOvestPosaOssoVaASudPrendeLanternaVaInBiblioteca\n");
		io.setIstruzione("prendi osso");
		io.setIstruzione("vai ovest");
		io.setIstruzione("posa osso");
		io.setIstruzione("guarda");
		io.setIstruzione("vai est");
		io.setIstruzione("vai sud");
		io.setIstruzione("prendi lanterna");
		io.setIstruzione("vai nord");
		io.setIstruzione("vai nord");
		labirinto = Labirinto.newBuilder();
		labirinto.creaStanze();
	}
	
	@Test
	void testVadoInStanzaBloccataSenzaChiavePoiTermina() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestVadoInStanzaBloccataSenzaChiave\n");
		io.setIstruzione("vai nord");
		io.setIstruzione("vai nord");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("Atrio")
			.addStanzaBloccata("Bloccata", nord, "piedeDiPorco")
			.addStanzaFinale("Uscita")
			.addAdiacenza("Atrio", "Bloccata", nord)
			.addAdiacenza("Bloccata", "Uscita", nord);
		
	}
	
	@Test
	void testVadoInStanzaBloccataConChiavePoiVinco() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestVadoInStanzaBloccataConSChiave\n");
		io.setIstruzione("vai nord");
		io.setIstruzione("guarda");
		io.setIstruzione("vai nord");
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("Atrio")
			.addStanzaBloccata("Bloccata", nord, "piedeDiPorco").addAttrezzo("piedeDiPorco", 3)
			.addStanzaFinale("Uscita")
			.addAdiacenza("Atrio", "Bloccata", nord)
			.addAdiacenza("Bloccata", "Uscita", nord);
	}
	
	@Test
	void testSonoInStanzaConCaneEInteragiscoPoiGuardoETermino() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestSonoInStanzaConCaneEInteragiscoPoiGuardoETermino\n");
		io.setIstruzione("interagisci");
		io.setIstruzione("guarda");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("Stanza")
			.setPersonaggio(new Cane("Cane","Bau bau!",null,null));
	}
	
	@Test
	void testSonoInStanzaConStregaEMiSpostaSenzaSalutoPoiTermino() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestSonoInStanzaConStregaEMiSpostaSenzaSalutoPoiTermino\n");
		io.setIstruzione("interagisci");
		io.setIstruzione("guarda");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("Iniziale")
			.setPersonaggio(new Strega("Sbirulina","Mi girano le sfere di cristallo, quindi non mi disturbare."))
			.addStanza("MaxAttr")
			.addAttrezzo("A", 1)
			.addAttrezzo("B", 1)
			.addStanza("MinAttr")
			.addAttrezzo("C", 1)
			.addAdiacenza("Iniziale", "MaxAttr", nord)
			.addAdiacenza("Iniziale", "MinAttr", sud);
	}
	
	@Test
	void testSonoInStanzaConStregaEMiSpostaConSalutoPoiTermino() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestSonoInStanzaConStregaEMiSpostaConSalutoPoiTermino\n");
		io.setIstruzione("saluta");
		io.setIstruzione("interagisci");
		io.setIstruzione("guarda");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("Iniziale")
			.setPersonaggio(new Strega("Sbirulina","Mi girano le sfere di cristallo, quindi non mi disturbare."))
			.addStanza("MaxAttr")
			.addAttrezzo("A", 1)
			.addAttrezzo("B", 1)
			.addStanza("MinAttr")
			.addAttrezzo("C", 1)
			.addAdiacenza("Iniziale", "MaxAttr", nord)
			.addAdiacenza("Iniziale", "MinAttr", sud);
	}
	
	@Test
	void testSonoInStanzaConStregaEGliDoUnRegaloCheNonHo() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestSonoInStanzaConStregaEGliDoUnRegaloCheNonHo\n");
		io.setIstruzione("guarda");
		io.setIstruzione("prendi osso");
		io.setIstruzione("regala pietra");
		io.setIstruzione("guarda");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("Monolocale")
			.setPersonaggio(new Strega("Gina","Mi girano le sfere di cristallo"))
			.addAttrezzo("osso", 5);
	}
	
	@Test
	void testSonoInStanzaConMagoEMiDaUnAttrezzoGuardoPoiTermino() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestSonoInStanzaConMagoEMiDaUnAttrezzoGuardoPoiTermino\n");
		io.setIstruzione("saluta");
		io.setIstruzione("interagisci");
		io.setIstruzione("guarda");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("Monolocale")
			.setPersonaggio(new Mago("Merlonio","Quello che viene da Ionio",new Attrezzo("Biglietto ATAC",1)));
	}
	
	@Test
	void testSonoInStanzaConMagoEGliDoUnRegaloGuardo() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestSonoInStanzaConMagoEGliDoUnRegaloGuardo\n");
		io.setIstruzione("guarda");
		io.setIstruzione("prendi osso");
		io.setIstruzione("regala osso");
		io.setIstruzione("guarda");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("Monolocale")
			.setPersonaggio(new Mago("Merlonio","Quello che viene da Ionio",null))
			.addAttrezzo("osso", 5);
	}
	
	@Test
	void testSonoInStanzaConStregaEGliDoUnRegaloGuardo() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestSonoInStanzaConStregaEGliDoUnRegaloGuardo\n");
		io.setIstruzione("guarda");
		io.setIstruzione("prendi osso");
		io.setIstruzione("regala osso");
		io.setIstruzione("guarda");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("Monolocale")
			.setPersonaggio(new Strega("Gina","Mi girano le sfere di cristallo"))
			.addAttrezzo("osso", 5);
	}
	
	@Test
	void testSonoInStanzaConCaneEGliDoUnRegaloCheAccettaELasciaPallinaGuardo() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestSonoInStanzaConCaneEGliDoUnRegaloCheAccettaELasciaPallinaGuardo\n");
		io.setIstruzione("guarda");
		io.setIstruzione("prendi osso");
		io.setIstruzione("regala osso");
		io.setIstruzione("guarda");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("Monolocale")
			.setPersonaggio(new Cane("Doggo","woof woof", new Attrezzo("pallina", 1), "osso"))
			.addAttrezzo("osso", 5);
	}
	
	@Test
	void testSonoInStanzaConCaneEGliDoUnRegaloCheAccettaENonLasciaNienteGuardo() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestSonoInStanzaConCaneEGliDoUnRegaloCheAccettaENonLasciaNienteGuardo\n");
		io.setIstruzione("guarda");
		io.setIstruzione("prendi osso");
		io.setIstruzione("regala osso");
		io.setIstruzione("guarda");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("Monolocale")
			.setPersonaggio(new Cane("Doggo","woof woof", null, "osso"))
			.addAttrezzo("osso", 5);
	}
	
	@Test
	void testSonoInStanzaConCaneEGliDoUnRegaloCheNonAccettaGuardo() {
		io=new IOSimulator();
		io.mostraMessaggio("\ntestSonoInStanzaConCaneEGliDoUnRegaloCheNonAccettaGuardo\n");
		io.setIstruzione("guarda");
		io.setIstruzione("prendi pietra");
		io.setIstruzione("regala pietra");
		io.setIstruzione("guarda");
		io.setIstruzione("fine");
		labirinto = Labirinto.newBuilder();
		labirinto.addStanzaIniziale("Monolocale")
			.setPersonaggio(new Cane("Doggo","woof woof", null, "osso"))
			.addAttrezzo("pietra", 5);
	}
}