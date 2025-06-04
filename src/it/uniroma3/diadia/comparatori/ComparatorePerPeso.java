package it.uniroma3.diadia.comparatori;

import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.util.Comparator;

public class ComparatorePerPeso implements Comparator<Attrezzo>{
	
	/*
	 * Due attrezzi con lo stesso nome sono considerati uguali
	 * a prescindere dal peso, perché non devono esistere
	 * due attrezzi con lo stesso nome in tutto il labirinto.
	 * Se il peso è uguale (quindi non entra negli ultimi due if)
	 * allora gli attrezzi vengono comparati per nome.
	 */
	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {
		if(a1.getNome().equals(a2.getNome()))
			return 0;
		if(a1.getPeso()>a2.getPeso())
			return 1;
		if(a1.getPeso()<a2.getPeso())
			return -1;
		return a1.getNome().compareTo(a2.getNome());
	}
	
}
