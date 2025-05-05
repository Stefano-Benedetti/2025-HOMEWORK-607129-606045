package simulazionePartite;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import it.uniroma3.diadia.*;


class simulazionePartiteIntere {

	IOSimulator io;
	DiaDia simulazione;
	
	@AfterEach
	void giocaSimulazione() {
		simulazione=new DiaDia(io);
		assertDoesNotThrow(()->{simulazione.gioca();});
		io.mostraMessaggio("\n//////////////////////////////////////////////\n");
	}
	
	@Test
	void testPartitaBibliotecaTrovataSubito() {
		io=new IOSimulator(1);
		io.mostraMessaggio("\ntestPartitaBibliotecaTrovataSubito\n");
		io.setIstruzione("vai nord");
	}
	
	
	@Test
	void testUtenteTerminaPartitaSubito() {
		io=new IOSimulator(1);
		io.mostraMessaggio("\ntestUtenteTerminaPartitaSubito\n");
		io.setIstruzione("fine");
	}
	
	@Test
	void testUtenteTerminaCfu() {
		io=new IOSimulator(20);
		io.mostraMessaggio("\ntestUtenteTerminaCfu\n");
		for(int i=0; i<20; i++) 
			io.setIstruzione("vai est");
	}
	
	@Test
	void testUtenteDaComandoInesistentePoiTermina() {
		io=new IOSimulator(2);
		io.mostraMessaggio("\ntestUtenteDaComandoInesistentePoiTermina\n");
		io.setIstruzione("amogus");
		io.setIstruzione("fine");
	}
	
	@Test
	void testUtenteChiedeAiutoEGuardaPoiTermina() {
		io=new IOSimulator(3);
		io.mostraMessaggio("\ntestUtenteChiedeAiutoEGuardaPoiTermina\n");
		io.setIstruzione("aiuto");
		io.setIstruzione("guarda");
		io.setIstruzione("fine");
	}
	
	@Test
	void testUtentePrendeEPosaOggettoPoiTermina() {
		io=new IOSimulator(3);
		io.mostraMessaggio("\ntestUtentePrendeEPosaOggettoPoiTermina\n");
		io.setIstruzione("prendi osso");
		io.setIstruzione("posa osso");
		io.setIstruzione("fine");
	}	
	
	@Test
	void testProvaAPrendereOggettoInesistentePoiProvaALasciareOggettoInesistentePoiTermina() {
		io=new IOSimulator(3);
		io.mostraMessaggio("\ntestProvaAPrendereOggettoInesistentePoiProvaALasciareOggettoInesistentePoiTermina\n");
		io.setIstruzione("prendi pietra");
		io.setIstruzione("posa pietra");
		io.setIstruzione("fine");
	}
	
	@Test
	void testProvaAdAndareInDirezioneInesistentePoiTermina() {
		io=new IOSimulator(3);
		io.mostraMessaggio("\ntestProvaAdAndareInDirezioneInesistentePoiTermina\n");
		io.setIstruzione("vai est");
		io.setIstruzione("vai nord");
		io.setIstruzione("fine");
	}
	
	@Test
	void testPrendeOssoVaAOvestPosaOssoVaAEstVaASudPrendeLanternaVaInBiblioteca() {
		io=new IOSimulator(8);
		io.mostraMessaggio("\ntestPrendeOssoVaAOvestPosaOssoVaASudPrendeLanternaVaInBiblioteca\n");
		io.setIstruzione("prendi osso");
		io.setIstruzione("vai ovest");
		io.setIstruzione("posa osso");
		io.setIstruzione("vai est");
		io.setIstruzione("vai sud");
		io.setIstruzione("prendi lanterna");
		io.setIstruzione("vai nord");
		io.setIstruzione("vai nord");
	}
}
