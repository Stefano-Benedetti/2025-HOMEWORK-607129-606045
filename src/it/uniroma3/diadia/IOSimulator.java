package it.uniroma3.diadia;


public class IOSimulator implements IO{
	private String[] istruzioni;
	private int indiceIstrDaEseguire;					//indice dell'istruzione che deve eseguire
	private int indiceIstrDaInserire;
	
	public IOSimulator(int istrTotali) {
		this.istruzioni=new String[istrTotali];
		indiceIstrDaEseguire=0;
		indiceIstrDaInserire=0;
	}
	
	public void setIstruzione(String istruzione) {
		istruzioni[indiceIstrDaInserire]=istruzione;
		indiceIstrDaInserire++;
	}
	
	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	@Override
	public void mostraMessaggioNoInvio(String msg) {
		System.out.print(msg);
	}
	
	@Override
	public String leggiRiga() {
		String temp= istruzioni[indiceIstrDaEseguire];
		indiceIstrDaEseguire++;
		this.mostraMessaggio(temp);
		return temp;
	}
	

}
