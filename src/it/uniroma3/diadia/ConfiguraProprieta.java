package it.uniroma3.diadia;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfiguraProprieta {
	
	private static Properties prop = null;
	
	private static void carica() {
		prop = new Properties();
		try {
			prop.load(new FileReader("diadia.properties"));
		} catch(IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static String getCFUIniziali() {
		if(prop==null)
			carica();
		return prop.getProperty("CFU_INIZIALI");
	}
	
	public static String getPesoMaxBorsa() {
		if(prop==null)
			carica();
		return prop.getProperty("DEFAULT_PESO_MAX_BORSA");
	}
	
	public static String getMessaggioBenvenuto() {
		if(prop==null)
			carica();
		return prop.getProperty("MESSAGGIO_BENVENUTO");
	}
	
	public static String getNumMaxAttrezzi() {
		if(prop==null)
			carica();
		return prop.getProperty("NUMERO_MASSIMO_ATTREZZI");
	}
	
	public static String getSogliaMagica() {
		if(prop==null)
			carica();
		return prop.getProperty("SOGLIA_MAGICA_DEFAULT");
	}
}
