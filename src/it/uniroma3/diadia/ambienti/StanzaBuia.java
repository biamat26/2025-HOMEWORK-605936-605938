package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{

	private final static String ATTREZZO_LUMINOSO_DEFAULT = "lanterna";
	private String attrezzoLuminoso;

	public StanzaBuia(String nome) {
		this(nome, ATTREZZO_LUMINOSO_DEFAULT);
	}

	public StanzaBuia(String nome, String attrezzoLuminoso) {
		super(nome);
		this.attrezzoLuminoso = attrezzoLuminoso;
	}

	@Override
	public String getDescrizione(){
		
		if (!hasAttrezzo(this.attrezzoLuminoso)) {
			return super.getDescrizione() + "\nQui c'è buio pesto...";
		}
		
		return super.getDescrizione();
	
		
	}
}
