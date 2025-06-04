package it.uniroma3.diadia.comandi;
import java.util.Scanner;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {
	
	public Comando costruisciComando(String istruzione) {
		
		Scanner scannerDiParole = new Scanner(istruzione);
		String nomeComando = null;
		String parametro = null;
		Comando comando = null;
		
		if (scannerDiParole.hasNext())
			nomeComando = scannerDiParole.next();	//prima parola: nome del comando
		if (scannerDiParole.hasNext())
			parametro = scannerDiParole.next();		//seconda parola: eventuale parametro
		
		try {
			StringBuilder nomeClasse = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
			
			nomeClasse.append( Character.toUpperCase(nomeComando.charAt(0)) );
			nomeClasse.append( nomeComando.substring(1) );
			comando = (Comando)Class.forName(nomeClasse.toString()).newInstance();
			comando.setParametro(parametro);
		} catch(IllegalAccessException e) {
			throw new RuntimeException("possibile causa: lo sviluppatore si è dimenticato "
					+ "di rendere pubblico il costruttore no-args di un sottoclasse di Comando");
		} catch(InstantiationException e) {
			throw new RuntimeException("possibile causa: lo sviluppatore si è dimenticato "
					+ "di aggiungere un costruttore no-args in una sottoclasse di Comando");
		} catch(ClassNotFoundException | NullPointerException e) {
			comando = new ComandoNonValido();
		}
		finally {
			scannerDiParole.close();
		}
		
		return comando;
	}
}
